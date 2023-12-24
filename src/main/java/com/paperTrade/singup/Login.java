package com.paperTrade.singup;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail= request.getParameter("username");
		String upwd= request.getParameter("password");
		RequestDispatcher dispatcher = null;
		int id = 0;
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/papertrade?useSSL=false", "root", "55555das");
			PreparedStatement pst=con.prepareStatement("select * from users where umail=? and upwd=?");
			pst.setString(1, uemail);
			pst.setString(2, upwd);
			//SELECT * FROM users WHERE LOWER(umail) = LOWER("abc@255");
			ResultSet rs= pst.executeQuery();
			HttpSession session =request.getSession();
			if(rs.next()) {
				session.setAttribute("name", rs.getString("uname"));
				session.setAttribute("mailID", uemail);
				dispatcher =request.getRequestDispatcher("index.jsp");
				float money =rs.getFloat("money");
				session.setAttribute("money", money);
				 id = rs.getInt("id");
		        session.setAttribute("userid", rs.getInt("id"));
			}
			else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
				
			}
			dispatcher.forward(request, response);
			getHoldings(id,con,session);
		}
		catch(Exception e) {e.printStackTrace();}

		
	}

private void getHoldings(int id,Connection con,HttpSession session){
	//String usernameToRetrieve =toString(id); // Replace with the actual username to retrieve
	System.out.println("working from package");
    try (con) {
        String selectQuery = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(selectQuery)) {
            pst.setInt(1, id);
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                	System.out.print(resultSet);
                    String serializedMap = resultSet.getString("stockHolded");
                    Map<String, Integer> userHashMap = convertStringToMap(serializedMap);
                    System.out.print(userHashMap);
                    session.setAttribute("holdings", convertMapToString(userHashMap));
                    // Now userHashMap contains the deserialized HashMap
                    System.out.println(userHashMap);
                } else {
                    System.out.println("User not found");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    finally {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

private static Map<String, Integer> convertStringToMap(String serializedString) {
    Map<String, Integer> map = new HashMap<>();
    String[] keyValuePairs = serializedString.split(",");
    for (String pair : keyValuePairs) {
        String[] entry = pair.split("=");
        if (entry.length == 2) {
            // Parse the string value to Integer
            try {
                int value = Integer.parseInt(entry[1]);
                map.put(entry[0], value);
            } catch (NumberFormatException e) {
                // Handle the case where the value is not a valid integer
                e.printStackTrace();
            }
        }
    }
    return map;
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
}