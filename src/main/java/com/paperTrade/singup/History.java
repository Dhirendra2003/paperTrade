package com.paperTrade.singup;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



@WebServlet("/History")
public class History extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
     
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out=response.getWriter();
       //out.print("<h1>working</h1>");
        int userId = (int) session.getAttribute("userid"); // Replace with the actual user ID.
         //retrieveTradesFromDatabase(userId);

        // Set the trade data as a request attribute
        //request.setAttribute("trades", trades);

        // Forward to a JSP page for displaying the trade data
        //RequestDispatcher dispatcher = request.getRequestDispatcher("history.jsp");
        //dispatcher.forward(request, response);
    //}

    //HttpSession session = request.getSession();
        //HttpServletResponse response=null ;
        //
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/papertrade?useSSL=false", "root",
                    "55555das");
        	 // Replace with the actual user ID
            int userIdToRetrieve =userId;
        	String query = "SELECT * FROM trades WHERE userid = ?";
        	try (PreparedStatement stmt = con.prepareStatement(query)) {
        	    stmt.setInt(1, userIdToRetrieve);
        	    ResultSet result = stmt.executeQuery();
				out.write("<head><style>* {\r\n"
						+ "  font-family: \"Ubuntu\", sans-serif;\r\n"
						+ "}\r\n"
						+ "body {\r\n"
						+ "  background-color: black;\r\n"
						+ "  color: white;\r\n"
						+ "  font-family: \"Ubuntu\", sans-serif;\r\n"
						+ "  margin: 0;\r\n"
						+ "  padding: 0;\r\n"
						+ "}\r\n"
						+ "nav {\r\n"
						+ "  display: flex;\r\n"
						+ "  background-color: rgb(50, 50, 50);\r\n"
						+ "  padding-bottom: 10px;\r\n"
						+ "  padding-top: 10px;\r\n"
						+ "}\r\n"
						+ "ul {\r\n"
						+ "  display: flex;\r\n"
						+ "  margin: 0;\r\n"
						+ "  position: relative;\r\n"
						+ "  right: 20px;\r\n"
						+ "  margin-left: auto;\r\n"
						+ "  list-style: none;\r\n"
						+ "}\r\n"
						+ "li {\r\n"
						+ "  margin: 10px;\r\n"
						+ "  background-color: rgb(44, 44, 44);\r\n"
						+ "  border-radius: 100px;\r\n"
						+ "  padding: 10px;\r\n"
						+ "  padding: 0 20px;\r\n"
						+ "  padding-top: 15px;\r\n"
						+ "  transition: 0.5s;\r\n"
						+ "}\r\n"
						+ "li:hover {\r\n"
						+ "  background-color: blue;\r\n"
						+ "  transition: 0.5s;\r\n"
						+ "}\r\n"
						+ "ul a {\r\n"
						+ "  color: white;\r\n"
						+ "  text-decoration: none;\r\n"
						+ "}\r\n"
						+ "h1 {\r\n"
						+ "  margin: 20px;\r\n"
						+ "  padding-left: 5vw;\r\n"
						+ "  font-size: 2em;\r\n"
						+ "  color: rgb(0, 255, 255);\r\n"
						+ "}\r\n"
						+ ".tradesection {\r\n"
						+ "  display: flex;\r\n"
						+ "}\r\n"
						+ "#stockInfo {\r\n"
						+ "  background-color: rgb(59, 59, 59);\r\n"
						+ "  margin: 0 20px;\r\n"
						+ "  border-radius: 50px;\r\n"
						+ "  padding: 50px;\r\n"
						+ "  align-items: center;\r\n"
						+ "  align-content: center;\r\n"
						+ "  text-align: center;\r\n"
						+ "  padding-top: 100px;\r\n"
						+ "  width:20vw;\r\n"
						+ "}\r\n"
						+ ".graph {\r\n"
						+ "  width: 100vw;\r\n"
						+ "  margin: 0;\r\n"
						+ "  overflow: hidden;\r\n"
						+ "  border-radius: 50px;\r\n"
						+ "  position: relative;\r\n"
						+ "  border-radius: 100px;\r\n"
						+ "}\r\n"
						+ "#chart_div {\r\n"
						+ "  width: 100vw;\r\n"
						+ "  margin: 30px;\r\n"
						+ "  overflow: hidden;\r\n"
						+ "  border-radius: 50px;\r\n"
						+ "  position: relative;\r\n"
						+ "}\r\n"
						+ ".searchBar {\r\n"
						+ "  padding: 20px;\r\n"
						+ "  text-align: center;\r\n"
						+ "  position: relative;\r\n"
						+ "}\r\n"
						+ "input {\r\n"
						+ "  border-radius: 20px;\r\n"
						+ "  background-color: rgb(71, 71, 71);\r\n"
						+ "  border: none;\r\n"
						+ "  padding: 10px;\r\n"
						+ "  color: white;\r\n"
						+ "  font-size: 1.1em;\r\n"
						+ "}\r\n"
						+ ".stockSearch {\r\n"
						+ "  border-radius: 20px;\r\n"
						+ "  padding: 10px;\r\n"
						+ "  font-size: 1.2em;\r\n"
						+ "  color: white;\r\n"
						+ "  background-color: rgb(0, 23, 201);\r\n"
						+ "  border: none;\r\n"
						+ "  cursor: pointer;\r\n"
						+ "}\r\n"
						+ "#searchResults {\r\n"
						+ "  display: grid;\r\n"
						+ "  max-width: fit-content;\r\n"
						+ "  margin: auto;\r\n"
						+ "  background-color: rgb(90, 90, 90);\r\n"
						+ "  padding: 20px;\r\n"
						+ "  border-radius: 0 0 30px 30px;\r\n"
						+ "  box-shadow: 0px 5px 20px 10px rgba(0, 0, 0, 0.468);\r\n"
						+ "  position: absolute;\r\n"
						+ "  transform: 0.1s;\r\n"
						+ "  left: 50%;\r\n"
						+ "  transform: translate(-50%, 0);\r\n"
						+ "  z-index: 4;\r\n"
						+ "}\r\n"
						+ "#searchResults button {\r\n"
						+ "  margin: 2px;\r\n"
						+ "  padding: 05px 50px;\r\n"
						+ "  background-color: rgb(42, 42, 42);\r\n"
						+ "  color: white;\r\n"
						+ "  border: none;\r\n"
						+ "  cursor: pointer;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "  border-radius: 10px;\r\n"
						+ "}\r\n"
						+ "#searchResults button:hover {\r\n"
						+ "  background-color: blue;\r\n"
						+ "  padding: 10px 20px;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ ".sell {\r\n"
						+ "  background-color: rgb(198, 0, 0);\r\n"
						+ "}\r\n"
						+ ".buy {\r\n"
						+ "  background-color: rgb(9, 184, 0);\r\n"
						+ "}\r\n"
						+ ".volume{\r\n"
						+ "  background-color:orange;\r\n"
						+ "}\r\n"
						+ ".buy,\r\n"
						+ ".sell ,.volume{\r\n"
						+ "  color: white;\r\n"
						+ "  text-shadow: 0px 0px 3px black;\r\n"
						+ "  border-radius: 10px;\r\n"
						+ "  padding: 5px;\r\n"
						+ "  margin: 5px;\r\n"
						+ "}\r\n"
						+ ".quantity {\r\n"
						+ "  text-align: center;\r\n"
						+ "  max-width: 5vw;\r\n"
						+ "}\r\n"
						+ "div.main {\r\n"
						+ "  position: relative;\r\n"
						+ "  background-color: rgb(255, 255, 255);\r\n"
						+ "  width: fit-content;\r\n"
						+ "  text-align: center;\r\n"
						+ "  left: 50%;\r\n"
						+ "  top: 50vh;\r\n"
						+ "  transform: translate(-50%, -50%);\r\n"
						+ "  display: flex;\r\n"
						+ "  border-radius: 50px;\r\n"
						+ "}\r\n"
						+ "div.main div {\r\n"
						+ "  margin: 20px;\r\n"
						+ "}\r\n"
						+ ".img {\r\n"
						+ "  height: 20vh;\r\n"
						+ "  margin: auto;\r\n"
						+ "  margin-top: 10vh;\r\n"
						+ "}\r\n"
						+ "div.signup-content {\r\n"
						+ "  display: flex;\r\n"
						+ "}\r\n"
						+ "h2 {\r\n"
						+ "  color: black;\r\n"
						+ "}\r\n"
						+ "\r\n"
						+ ".news-item img {\r\n"
						+ "  width: 15vw;\r\n"
						+ "  height: 10vw;\r\n"
						+ "  border: 5px solid cyan;\r\n"
						+ "  border-radius: 10px;\r\n"
						+ "  position: relative;\r\n"
						+ "  \r\n"
						+ "}\r\n"
						+ "\r\n"
						+ "td {\r\n"
						+ "  padding: 15px;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ "th {\r\n"
						+ "  background-color: rgb(255, 255, 255);\r\n"
						+ "  padding: 10px;\r\n"
						+ "  color: rgb(0, 0, 0);\r\n"
						+ "  border-radius: 0px;\r\n"
						+ "}\r\n"
						+ "tr {\r\n"
						+ "  border-radius: 20px;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ "table {\r\n"
						+ "  background-color: rgb(59, 59, 59);\r\n"
						+ "  border-radius: 20px;\r\n"
						+ "  margin: auto;\r\n"
						+ "  margin-top: 100px;\r\n"
						+ "  font-family: \"Ubuntu\", sans-serif;\r\n"
						+ "}\r\n"
						+ "tbody {\r\n"
						+ "  border-radius: 20px;\r\n"
						+ "}\r\n"
						+ "\r\n"
						+ "tr:hover {\r\n"
						+ "  background-color: rgb(0, 247, 255);\r\n"
						+ "  color: black;\r\n"
						+ "  border-radius: 20px;\r\n"
						+ "  padding: 20px 15px;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ "tr:hover td {\r\n"
						+ "  font-family: \"Ubuntu\", sans-serif;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ "#table tr:hover td {\r\n"
						+ "  background-color: #b7b7b7;\r\n"
						+ "  font-size: 1.5em;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ "#table td:hover button{\r\n"
						+ "  border:black solid 5px;\r\n"
						+ "  border-radius:10px;\r\n"
						+ "  padding:5px;\r\n"
						+ "  color:black;\r\n"
						+ "  font-weight:900;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ "#table {\r\n"
						+ "  margin-bottom: 50px;\r\n"
						+ "  margin-top:30px;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ "#table th{\r\n"
						+ "  text-transform:uppercase;\r\n"
						+ "  font-size:1.2em;\r\n"
						+ "  padding:10px;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "  \r\n"
						+ "}\r\n"
						+ "table button {\r\n"
						+ "  background-color: transparent;\r\n"
						+ "  border: none;\r\n"
						+ "  color: white;\r\n"
						+ "  font-family: \"Ubuntu\", sans-serif;\r\n"
						+ "}\r\n"
						+ ".news-item {\r\n"
						+ "  margin: 50px auto;\r\n"
						+ "  border-radius: 20px;\r\n"
						+ "  border: 5px solid white;\r\n"
						+ "  display: flex;\r\n"
						+ "  padding: 20px;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "  width: 70vw;\r\n"
						+ "}\r\n"
						+ ".news-item:hover {\r\n"
						+ "  background-color: #333333;\r\n"
						+ "  transition: 0.2s;\r\n"
						+ "}\r\n"
						+ ".twoInone{\r\n"
						+ "  display:flex;\r\n"
						+ "  margin:0 20vw;\r\n"
						+ "  \r\n"
						+ "}\r\n"
						+ "#container div{\r\n"
						+ "  background-color:#454545;\r\n"
						+ "  width:30vw;\r\n"
						+ "  margin:50px auto;\r\n"
						+ "  padding:20px;\r\n"
						+ "  border:white 5px solid;\r\n"
						+ "  border-radius:20px;\r\n"
						+ "  font-family:\"Ubuntu\", sans-serif;\r\n"
						+ "  display:inline-block\r\n"
						+ "}\r\n"
						+ "p{\r\n"
						+ "  font-size:1.5em;\r\n"
						+ "  padding:20px;\r\n"
						+ "  margin:auto\r\n"
						+ "}\r\n"
						+ "#container form input{\r\n"
						+ "  background-color:red;\r\n"
						+ "  padding:15px 30px;\r\n"
						+ "  font-size:1.2em;\r\n"
						+ "  text-transform:uppercase;\r\n"
						+ "  position:relative;\r\n"
						+ "  float:right;\r\n"
						+ "  cursor: pointer;\r\n"
						+ "}\r\n"
						+ "#container{\r\n"
						+ "  display:grid;\r\n"
						+ "}</style></head");
				out.write("<h1>History Trades</h1>");
        	    out.write("<table border=\"1\">");  // Opening table tag

        	    out.write("<tr>");  // Table header row
        	    out.write("<th>Trade ID</th>");
        	    out.write("<th>User ID</th>");
        	    out.write("<th>Trade Type</th>");
        	    out.write("<th>Symbol</th>");
        	    out.write("<th>Price</th>");
        	    out.write("<th>Quantity</th>");
        	    out.write("<th>Total value</th>");
        	    out.write("</tr>");
        	    
        	    while (result.next()) {
        	    	int tradeId = result.getInt("id");
        	         userId = result.getInt("userid");
        	        String tradeType = result.getString("Ttype");
        	        String symbol = result.getString("symbol");
        	        String price = String.valueOf(result.getFloat("price"));
        	        int quantity = result.getInt("quantity");
        	        //out.print( tradeType);
        	        //out.print( symbol);
        	        
        	        
        	        // Display or process the trade record as needed
        	        //System.out.println("Trade ID: " + tradeId + ", User ID: " + userId + ", Trade Type: " + tradeType + ", Symbol: " + symbol + ", Price: " + price + ", Quantity: " + quantity);
        	        //out.write("Trade ID: " + tradeId + ", User ID: " + userId + ", Trade Type: " + tradeType + ", Symbol: " + symbol + ", Price: " + price + ", Quantity: " + quantity+ "<br>");
        	        out.write("<tr>");
        	        out.write("<td> " + tradeId + "</td>");
        	        out.write("<td> " + userId + "</td>");
        	        out.write("<td> " + tradeType + "</td>");
        	        out.write("<td> " + symbol + "</td>");
        	        out.write("<td> " + price + "</td>");
        	        out.write("<td> " + quantity + "</td>");
        	        out.write("<td> " + (quantity*Float.parseFloat(price))   + "</td>");
        	        out.write("</tr>");
        	        //out.write("<br>");  // If you still want a line break

        	        //response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        	        //response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        	        //response.getWriter().write("Trade ID: " + tradeId + ", User ID: " + userId + ", Trade Type: " + tradeType + ", Symbol: " + symbol + ", Price: " + price + ", Quantity: " + quantity);  
        	    }
        	    out.print("</table>");
        	}

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in your application
        }}
            //     finally {
//            // Close database resources
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (con != null) con.close();
//            } catch (Exception e) {
//                e.printStackTrace(); // Handle exceptions properly in your application
//            }
//        }

        // Convert the list to an array
        //storeTrade[] tradesArray = tradesList.toArray(new storeTrade[0]);
        //return tradesArray;
    }

