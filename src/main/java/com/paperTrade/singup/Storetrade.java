package com.paperTrade.singup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@WebServlet("/Storetrade")
public class Storetrade extends HttpServlet {

	
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	final HttpSession session = request.getSession();
    	Float sellbuy = null;
        String tradeSymbol = request.getParameter("stockSymbol");
        String tradeQuantity = request.getParameter("qty");
        String stockName = request.getParameter("stockName");
        String currentPrice = request.getParameter("stockPriceCurrent");
        String tradeType = request.getParameter("tradeType");
        Float currMoney=0f;
        Float totalquantValue=totalValue( tradeType, sellbuy , tradeQuantity, currentPrice);
        Timer timer = new Timer();

        try {
        	
        	
            // Step 2: Load MySQL JDBC Driver and establish a database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/papertrade?useSSL=false", "root",
                    "55555das");

            // Step 3: Get the user ID based on the provided email
             int userid = (int) session.getAttribute("userid");//getuseridByEmail(c userEmail);

            // Step 4: Insert data into the "trades" table
            insertTradeData(con, userid, tradeSymbol, tradeQuantity, stockName, currentPrice, tradeType,totalquantValue);
            insertHoldingToUsertable(con, userid, tradeSymbol, tradeQuantity, stockName, currentPrice, tradeType,session);
            updateMoney(con, userid,totalquantValue,currMoney,session);
           // Additional steps or redirection can be added here as needed.
            session.setAttribute("statusTrade", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
        	timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    
                    session.setAttribute("statusTrade", "false");
                }
            }, 2000); // Delay in milliseconds

            
            timer.cancel();
            String newLocation = "portfolio.jsp"; 
         // Perform the redirect
         response.sendRedirect(newLocation);
        }
    }

private Float totalValue(String tradeType,Float sellbuy ,String tradeQuantity,String currentPrice){
    	
    	if(Objects.equals(tradeType, "B")) {
    		 sellbuy=-1.0f;
    	}
    	else if(Objects.equals(tradeType, "S")){
             sellbuy=1.0f;
        }
    	float parsedQuantity = Float.parseFloat(tradeQuantity);
        float parsedPrice = Float.parseFloat(currentPrice);

        return parsedQuantity * parsedPrice * sellbuy; 	
    }

    // Step 4: Helper method to insert trade data into the "trades" table
    private void insertTradeData(Connection con, int userid, String tradeSymbol, String tradeQuantity, String stockName,
            String currentPrice, String tradeType, Float totalquantValue) {
        String query = "INSERT INTO trades (userid, symbol, quantity, stockname, price, Ttype,totalvalue) VALUES (?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, userid);
            stmt.setString(2, tradeSymbol);
            stmt.setString(3, tradeQuantity);
            stmt.setString(4, stockName);
            stmt.setString(5, currentPrice);
            stmt.setString(6, tradeType);
            stmt.setFloat(7, totalquantValue);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateMoney(Connection con, int userid,  Float totalquantValue,Float currMoney,HttpSession session) {
        
        String queryForMoney = "SELECT money from users WHERE id=?";
        try (PreparedStatement stmt = con.prepareStatement(queryForMoney)) {
            stmt.setInt(1, userid);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
            currMoney=result.getFloat("money");
            System.out.println("got the prev money:"+currMoney);
            }
            else {System.out.printf("no money found")  ;        }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //after getting money current += total value
        String query = "UPDATE users Set money =? WHERE id=?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            totalquantValue=currMoney+totalquantValue;
            stmt.setFloat(1,totalquantValue );
            stmt.setInt(2, userid);
            System.out.println("decrements/increments made on account");
            stmt.executeUpdate();
            session.setAttribute("money", totalquantValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertHoldingToUsertable(Connection con, int userid, String tradeSymbol, String tradeQuantity, String stockName,
            String currentPrice, String tradeType,HttpSession session){
    	
    	String gethashmap=(String) session.getAttribute("holdings");
    	if (gethashmap != null) {
    	    // Initialize a new map to store the values
    	    Map<String, Integer> userHashMap = new HashMap<>();

    	    // Parse the existing serialized has-hmap string
    	    String[] keyValuePairs = gethashmap.split(",");
    	    for (String pair : keyValuePairs) {
    	        String[] entry = pair.split("=");
    	        if (entry.length == 2) {
    	            // Parse the string value to Integer
    	            try {
    	                int value = Integer.parseInt(entry[1]);
    	                userHashMap.put(entry[0], value);
    	            } catch (NumberFormatException e) {
    	                // Handle the case where the value is not a valid integer
    	                e.printStackTrace();
    	            }
    	        }
    	    }

    	    // Print the existing has-hmap
    	    System.out.println(userHashMap);

    	    // Update the has-hmap with the new trade information
    	    updateHashMap(userHashMap, tradeSymbol, Integer.parseInt(tradeQuantity), tradeType);

    	    // Print the updated hash-map
    	    System.out.println(userHashMap);

    	    // Update the database with the updated hash-map
    	    String updateQuery = "UPDATE users SET stockHolded = ? WHERE id = ?;";
    	    try (PreparedStatement stmt = con.prepareStatement(updateQuery)) {
    	        stmt.setString(1, convertMapToString(userHashMap));
    	        stmt.setInt(2, userid);
    	        stmt.executeUpdate();
    	        session.setAttribute("holdings", convertMapToString(userHashMap));
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}
    	//else hashma-p doe-snt exist then
    	else {
    	Map<String, Integer> userHashMap =new HashMap<>();
    	 updateHashMap(userHashMap,tradeSymbol,Integer.parseInt(tradeQuantity),tradeType);
         // Populate your userHashMap
                 System.out.println(userHashMap);
         
             String insertQuery = "UPDATE users SET stockHolded = ?  WHERE id = ?;";
             try (PreparedStatement stmt = con.prepareStatement(insertQuery)) {
                 stmt.setString(1, convertMapToString(userHashMap)); // Replace with the actual u
                 stmt.setInt(2,userid );
                 stmt.executeUpdate();
             }
          catch (Exception e) {
           e.printStackTrace();
         }
    		}
        
        
    }

    private static String convertMapToString(Map<String, Integer> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey())
                         .append("=")
                         .append(entry.getValue())
                         .append(",");
        }
        // Remove the trailing comma if there are entries
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
    
    public static void updateHashMap(Map<String, Integer> hashmap, String key, int value,String tradeType) {
    	if(tradeType.equals("S")) {value=-(int)value;}
        if (hashmap.containsKey(key)) {
            // Key already exists, update the value
            hashmap.put(key, hashmap.get(key) + value);

            // If the updated value is zero, remove the key
            if (hashmap.get(key) == 0) {
                hashmap.remove(key);
            }
        } else {
            // Key doesn't exist, add a new key-value pair
            hashmap.put(key, value);
        }
    }
    
    }


