package com.studentmanagement.sevlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SubmitAttendanceServlet")
public class SubmitAttendanceServlet extends HttpServlet {
	Connection con=null;
	PreparedStatement ps=null;
	Statement stmt=null;
	ResultSet rs=null;
	PreparedStatement psUpdateTotal = null;
    PreparedStatement psUpdatePresent = null;
	String url="jdbc:mysql://localhost:3306/studentmanagement";
	String sqlname="root";
	String sqlpassword="Chintha@2127";
	
	
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

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("1");
		String section = request.getParameter("section");
		System.out.println(section);
        String timeSlot = request.getParameter("time_slot");
        System.out.println(timeSlot);
        String date = request.getParameter("date");
        System.out.println(date);
        System.out.println("sec :"+section);
        String query="select * from users where Section=?";
        ps = con.prepareStatement(query);
		ps.setString(1, section);
		rs = ps.executeQuery();
		System.out.println("exec");
		List<Map<String, String>> studentList = new ArrayList<>();
		System.out.println("2");
        while (rs.next()) {
        	System.out.println("3");
            Map<String, String> student = new HashMap<>();
            student.put("id", rs.getString("id"));
            System.out.println("4");
            student.put("username", rs.getString("username"));
            System.out.println("5");
            studentList.add(student);
            System.out.println("6");
        }
        for(Object x:studentList) {
        	System.out.println(x);
        }
        request.setAttribute("studentList", studentList);
        request.setAttribute("section", section);
        request.setAttribute("timeSlot", timeSlot);
        request.setAttribute("date", date);
        	 
	            // Forward to JSP
        request.getRequestDispatcher("Attendance-Form.jsp").forward(request, response);
 
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }


	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        String[] studentIds = request.getParameterValues("student_ids");
	        String section = request.getParameter("section");
	        String timeSlot = request.getParameter("time_slot");
	        String dateStr = request.getParameter("date");
	        java.sql.Date sqlDate = java.sql.Date.valueOf(dateStr);

	        for (String id : studentIds) {
	            String status = request.getParameter("status_" + id) != null ? "Present" : "Absent";

	            // Insert into attendance table
	            PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (student_id, section, date, time_slot, status) VALUES (?, ?, ?, ?, ?)");
	            ps.setInt(1, Integer.parseInt(id));
	            ps.setString(2, section);
	            ps.setDate(3, sqlDate);
	            ps.setString(4, timeSlot);
	            ps.setString(5, status);
	            ps.executeUpdate();

	            // Update user periods
	            if ("Present".equals(status)) {
	                ps = con.prepareStatement("UPDATE users SET total_periods = total_periods + 1, present_periods = present_periods + 1 WHERE id = ?");
	            } else {
	                ps = con.prepareStatement("UPDATE users SET total_periods = total_periods + 1 WHERE id = ?");
	            }
	            ps.setInt(1, Integer.parseInt(id));
	            ps.executeUpdate();
	        }

	        request.getRequestDispatcher("Attendance-Form.jsp").forward(request, response);

	    } catch (Exception e) {
	    	 e.printStackTrace();
	    	    // Optional: log or redirect to error page
	    	 response.getWriter().println("Error: " + e.getMessage());
	    }
		doGet(request, response);
	}

}
