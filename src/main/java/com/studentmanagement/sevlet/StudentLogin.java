package com.studentmanagement.sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/StudentLogin")
public class StudentLogin extends HttpServlet {
	
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String url="jdbc:mysql://localhost:3306/studentmanagement";
	String sqlname="SQL - password";
	String sqlpassword="SQL - password";
	
	
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
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			String query = "SELECT * FROM users WHERE username=? AND password=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("inside the rs.next() : "+rs);
				HttpSession session = req.getSession();
				session.setAttribute("username", name);  // Store once after login
				session.setAttribute("username", name);
				session.setAttribute("password", password);
				session.setAttribute("sqlname", sqlname);
				session.setAttribute("sqlpassword", sqlpassword);
				session.setAttribute("url", url);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/StudentServlets");

				// Forward the request and response objects
				dispatcher.forward(req, resp);
			
			}
			else {
				req.setAttribute("error", "Invalid Details, please try again!");
		        RequestDispatcher rd = req.getRequestDispatcher("index.html");
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


