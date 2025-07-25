package com.studentmanagement.sevlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/SaveMarksServlet")
public class SaveMarksServlet extends HttpServlet {
	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sqlname=null;
	String sqlpassword=null;
	String url=null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String subject = request.getParameter("subject");
        String section = request.getParameter("section");
        String[] usernames = request.getParameterValues("usernames");

        try {
            init(request,response);
            String sql = "INSERT INTO marks (username, subject, marks, section) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            for (String username : usernames) {
                int mark = Integer.parseInt(request.getParameter("mark_" + username));
                ps.setString(1, username);
                ps.setString(2, subject);
                ps.setInt(3, mark);
                ps.setString(4, section);
                ps.addBatch();
            }

            ps.executeBatch();
            ps.close();
            con.close();

            response.sendRedirect("Staff_Dashboard.jsp");
//            response.getWriter().println("<h3>✅ Marks saved successfully!</h3>");
//            response.getWriter().println("<a href='marks-entry.jsp'>Enter More</a>");

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
