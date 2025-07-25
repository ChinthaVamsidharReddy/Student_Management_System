package com.studentmanagement.sevlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddStudentDetails")
public class AddStudentDetails extends HttpServlet {

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
	


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    
        throws ServletException, IOException {
    	init(request,response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String contact = request.getParameter("contact_number");
            String dob = request.getParameter("Date_of_birth");
            String gender = request.getParameter("gender");
            String department = request.getParameter("department");
            String section = request.getParameter("section");
            String batch = request.getParameter("batch");

            // Print received data (for debug)
            System.out.println(name + " " + username + " " + contact + " " + dob + " " +
                               gender + " " + department + " " + section + " " + batch);

            String sql = "INSERT INTO users (Name, username, Contact_Number, DOB, Gender, Department, Section, Batch) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, contact);
            ps.setString(4, dob);
            ps.setString(5, gender);
            ps.setString(6, department);
            ps.setString(7, section);
            ps.setString(8, batch);

            int result = ps.executeUpdate();
            if (result > 0) {
            	out.println("<script type='text/javascript'>");
                out.println("alert('Student details added successfully!');");
                out.println("location='AddStudentDetails.jsp';"); // redirect after alert
                out.println("</script>");
            	response.sendRedirect("AddStudentDetailes.jsp");


            } else {
            	out.println("<script type='text/javascript'>");
                out.println("alert('Failed to add student details.');");
                out.println("location='AddStudentDetails.jsp';");
                out.println("</script>");
                out.println("<h3>❌ Failed to add student details.</h3>");
            }

            ps.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    public void destroy() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
