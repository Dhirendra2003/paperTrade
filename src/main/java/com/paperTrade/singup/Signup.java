package com.paperTrade.singup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;


/**
 * Servlet implementation class signup
 */
@WebServlet("/Signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		
		
	String uname= request.getParameter("name");
	String uemail= request.getParameter("email");
	String upwd= request.getParameter("pass");
	String umobile= request.getParameter("contact");
	Connection con =null;
	
	
	RequestDispatcher dispatcher =null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con =DriverManager.getConnection("jdbc:mysql://localhost:3306/papertrade?useSSL=false","root", "55555das");
		PreparedStatement pst = con.prepareStatement("insert into users(uname,upwd, umail, umobile,money)values(?,?,?,?,?)");
		pst.setString(1, uname);
		pst.setString(2, upwd);
		pst.setString(3, uemail);
		pst.setString(4, umobile);
		pst.setFloat( 5,(float) 1000000.00 );
		
		int rowCount = pst.executeUpdate();
		dispatcher = request.getRequestDispatcher("registration.jsp");
		if(rowCount>0) {
		request.setAttribute("status", "success");
		}else {
			request.setAttribute("status", "failed");
		}
		
				dispatcher.forward(request, response);
				out.print(uname+ "\n");
				out.print(uemail+ "\n");
				out.print(upwd+ "\n");
				out.print(umobile+ "\n");
				
	}catch(Exception e){
		e.printStackTrace();
	}
	finally {
		try {
		con.close();}
		catch(SQLException e) {
			e.printStackTrace();	
			}
	}
}
	}
