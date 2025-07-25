package com.studentmanagement.sevlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/MarksEntryServlet")
public class MarksEntryServlet extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sqlname=null;
	String sqlpassword=null;
	String url=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String section = request.getParameter("section");
        String subject = request.getParameter("subject");

        List<Map<String, String>> students = new ArrayList<>();

        try {
            init(request,response);
            PreparedStatement ps = con.prepareStatement(
                "SELECT username, Name FROM users WHERE Section = ?");
            ps.setString(1, section);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> student = new HashMap<>();
                student.put("username", rs.getString("username"));
                student.put("name", rs.getString("Name"));
                students.add(student);
            }

            rs.close();
            ps.close();
            con.close();

            request.setAttribute("students", students);
            request.setAttribute("section", section);
            request.setAttribute("subject", subject);
            RequestDispatcher rd = request.getRequestDispatcher("enter-marks.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
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
	

}
