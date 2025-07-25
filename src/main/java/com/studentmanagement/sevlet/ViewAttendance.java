package com.studentmanagement.sevlet;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;


@WebServlet("/ViewAttendance")
public class ViewAttendance extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sqlname=null;
	String sqlpassword=null;
	String url=null;
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        
	        HttpSession session = request.getSession(false);
	        System.out.println((String) session.getAttribute("username"));
	        
	        
	        if (session == null || session.getAttribute("username") == null) {
	            // Not logged in, redirect to login page
	            response.sendRedirect("index.html");
	            return;
	        }
	        String username = (String) session.getAttribute("username");
	        System.out.println(username);
	        int totalPeriods = 0, presentPeriods = 0;
	        double percentage = 0.0;
	        boolean found = false;
	        init(request,response);
	        
	        try {
	        	PreparedStatement ps = con.prepareStatement(
		                "SELECT total_periods, present_periods FROM users WHERE username = ?");
		            ps.setString(1, username);
		            ResultSet rs = ps.executeQuery();

		            if (rs.next()) {
		                found = true;
		                totalPeriods = rs.getInt("total_periods");
		                presentPeriods = rs.getInt("present_periods");
		                percentage = (presentPeriods * 100.0) / totalPeriods;
		            }

		            request.setAttribute("found", found);
		            request.setAttribute("username", username);
		            request.setAttribute("total", totalPeriods);
		            request.setAttribute("present", presentPeriods);
		            request.setAttribute("percentage", String.format("%.2f", percentage));

		            rs.close();
		            ps.close();
		            con.close();

		            // Forward to JSP to display attendance
		            RequestDispatcher rd = request.getRequestDispatcher("ViewAttendance.jsp");
		            rd.forward(request, response);

		        } catch (Exception e) {
		            e.printStackTrace();
		            response.getWriter().println("Error: " + e.getMessage());
		        }
	    }
	    
	    
	    
	    
	    public void init(HttpServletRequest req, HttpServletResponse resp){
			HttpSession session = req.getSession(false);
			try {
				
				
//		        System.out.println((String) session.getAttribute("username"));
				
				
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
}
