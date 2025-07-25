package com.studentmanagement.sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String url="jdbc:mysql://localhost:3306/studentmanagement";
	String sqlname="sql-username";
	String sqlpassword="sql-password";
	
	
	public void init(){
			
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,sqlname,sqlpassword);
			if (con != null) {
	            System.out.println("Database connection established.");
	        } else {
	            System.out.println("Failed to establish database connection.");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("IN ADMIN LOGIN SERVELT");
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		System.out.println("INTO of doget()");
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			String query = "SELECT * FROM staff WHERE username=? AND password=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("inside the rs.next() : "+rs);
				req.setAttribute("username", name);
				req.setAttribute("password", password);
				req.setAttribute("sqlname", sqlname);
				req.setAttribute("sqlpassword", sqlpassword);
				req.setAttribute("url", url);
				System.out.println("in destroy method");
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;
				RequestDispatcher dispatcher = req.getRequestDispatcher("/AdminServlet");

				// Forward the request and response objects
				dispatcher.forward(req, resp);
			
			}
			else {
				req.setAttribute("error", "Invalid Details, please try again!");
		        RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
		        rd.forward(req, resp); 	
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
			
		
		@Override
		public void destroy() {
			System.out.println("in destroy method");
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
		}
	}
