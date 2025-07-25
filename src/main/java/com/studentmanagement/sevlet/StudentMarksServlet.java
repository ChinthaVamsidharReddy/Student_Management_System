package com.studentmanagement.sevlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/StudentMarksServlet")
public class StudentMarksServlet extends HttpServlet {
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
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp"); // if not logged in
            return;
        }

        String username = (String) session.getAttribute("username");
        List<Map<String, Object>> marksList = new ArrayList<>();

        try {
            init(request,response);
            PreparedStatement ps = con.prepareStatement(
                    "SELECT subject, marks FROM marks WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("subject", rs.getString("subject"));
                row.put("marks", rs.getInt("marks"));
                marksList.add(row);
            }

            rs.close();
            ps.close();
            con.close();

            request.setAttribute("marksList", marksList);
            RequestDispatcher rd = request.getRequestDispatcher("view-marks.jsp");
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
