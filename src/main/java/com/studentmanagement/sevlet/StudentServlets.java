package com.studentmanagement.sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/StudentServlets")
public class StudentServlets extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sqlname=null;
	String sqlpassword=null;
	String url=null;
	public void init(HttpServletRequest req, HttpServletResponse resp){
		HttpSession session = req.getSession(false);
		try {
			
			
//	        System.out.println((String) session.getAttribute("username"));
			
			
			sqlname = (String) session.getAttribute("sqlname");
			sqlpassword = (String) session.getAttribute("sqlpassword");
			url = (String) session.getAttribute("url");
			System.out.println(url+"\n"+sqlname+"\n"+sqlpassword+"\n");
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
		init(req,resp);
		HttpSession session = req.getSession(false);
		// TODO Auto-generated method stub
			resp.setContentType("text/html");
			PrintWriter pw = resp.getWriter();
			
			try {
				String name = (String) session.getAttribute("username");
			    String password = (String) session.getAttribute("password");
			    System.out.println("1 "+name+" "+password);
			    String query = "SELECT * FROM users WHERE username=? AND password=?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				System.out.println("2"+rs);
			    if (rs.next()) {
			        // Fetch logged-in user details
			        int id = rs.getInt(1);
			        String Name = rs.getString(2);
			        String username = rs.getString(3);
			        String pass = rs.getString(4);
			        String Contact_Number = rs.getString(5);
			        String DOB = rs.getString(6);
			        String Gender = rs.getString(7);
			        String Department = rs.getString(8);
			        String Section = rs.getString(9);
			        int Batch = rs.getInt(10);
			        
			        System.out.println(Name+" "+username+" "+pass+" "+Contact_Number+" "+DOB+" "+Gender+" "+Department+" "+Section+" "+Batch);
			        
			        // Set attributes for logged-in user
			        session.setAttribute("id", id);
			        session.setAttribute("name", Name);
			        session.setAttribute("username", username);
			        session.setAttribute("Contact_Number", Contact_Number);
			        session.setAttribute("DOB", DOB);
			        session.setAttribute("Gender", Gender);
			        session.setAttribute("Department", Department);
			        session.setAttribute("Section", Section);
			        session.setAttribute("Batch", Batch);

			        // You can also send a list of all users (optional)
			        String query2 = "SELECT * FROM users";
			        stmt = con.createStatement();
			        ResultSet rs2 = stmt.executeQuery(query2);

			        List<Map<String, String>> userList = new ArrayList<>();
			        while (rs2.next()) {
			            Map<String, String> user = new HashMap<>();
			            user.put("id", String.valueOf(rs2.getInt(1)));
			            user.put("name", rs2.getString(2));
			            user.put("username", rs2.getString(3));
			            user.put("Contact_Number", rs2.getString(5));
			            user.put("DOB", rs2.getString(6));
			            user.put("Gender", rs2.getString(7));
			            user.put("Department", rs2.getString(8));
			            user.put("Section", rs2.getString(9));
			            user.put("Batch", String.valueOf(rs2.getInt(10)));
			            userList.add(user);
			        }
			        req.setAttribute("userList", userList);

			        // Forward to JSP
			        RequestDispatcher rd = req.getRequestDispatcher("Student_Dashboard.jsp");
			        rd.forward(req, resp);
			    } else {
			        req.setAttribute("error", "Invalid Details, please try again!");
			        RequestDispatcher rd = req.getRequestDispatcher("index.html");
			        rd.forward(req, resp);
			    }

			} catch (Exception e) {
			    e.printStackTrace();
			}
		}
		@Override
		public void destroy() {
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
		}
	}

