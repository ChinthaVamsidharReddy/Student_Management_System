<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Attendance Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 20px;
        }

        .container {
            background: white;
            padding: 25px;
            max-width: 900px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        h2 {
            text-align: center;
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        input[type="submit"] {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #3498db;
            border: none;
            color: white;
            border-radius: 6px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #2980b9;
        }

        .error {
            color: red;
            font-weight: bold;
        }
.switch {
  position: relative;
  display: inline-block;
  width: 50px;
  height: 25px;
}

.switch input {
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #e74c3c; /* Absent */
  transition: 0.4s;
  border-radius: 25px;
}

.slider:before {
  position: absolute;
  content: "";
  height: 19px;
  width: 19px;
  left: 3px;
  bottom: 3px;
  background-color: white;
  transition: 0.4s;
  border-radius: 50%;
}

input:checked + .slider {
  background-color: #2ecc71; /* Present */
}

input:checked + .slider:before {
  transform: translateX(24px);
}

    </style>
</head>
<body>
<div class="container">
    <h2>Mark Attendance</h2>

    <%
        String section = request.getParameter("section");
        String timeSlot = request.getParameter("time_slot");
        String date = request.getParameter("date");
        List<Map<String, String>> studentList = (List<Map<String, String>>) request.getAttribute("studentList");

        // Show input form if data not yet submitted or missing
        if (studentList == null || section == null || timeSlot == null || date == null || section.trim().isEmpty() || timeSlot.trim().isEmpty() || date.trim().isEmpty()) {
    %>
        <form action="SubmitAttendanceServlet" method="post">
            <label>Section: <input type="text" name="section" required></label><br/><br/>
            <label>Date: <input type="date" name="date" required></label><br/><br/>
            <label>Time Slot: <input type="text" name="time_slot" required></label><br/><br/>
            <input type="submit" value="Load Students">
            
            
    		
            <% if (request.getAttribute("error") != null) { %>
                <p class="error"><%= request.getAttribute("error") %></p>
            <% } %>
        </form>
        <form action="Staff_Dashboard.jsp" method="post">
        <input type="submit" value="back"></form>
    <%
        } else {
        	System.out.println("in attensece in side the else ");
    %>
    
    <form action="SubmitAttendanceServlet" method="post">
        <input type="hidden" name="section" value="<%= section %>" />
        <input type="hidden" name="time_slot" value="<%= timeSlot %>" />
        <input type="hidden" name="date" value="<%= date %>" />

        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Attendance</th>
            </tr>
            <%
    for (Map<String, String> student : studentList) {
        String id = student.get("id");  // Get real student ID from DB
        String name = student.get("username");
%>
<tr>
    <td><%= id %></td>
    <td><%= name %></td>
    <td>
    <label class="switch">
        <input type="checkbox" name="status_<%= id %>" checked>
        <span class="slider"></span>
    </label>
    <input type="hidden" name="student_ids" value="<%= id %>" />
</td>

</tr>
<% } %>
			</table>
        <input type="submit" value="Submit Attendance" />
    </form>
    <% } %>
</div>
</body>
</html>
