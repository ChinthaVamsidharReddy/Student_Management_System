<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter Marks - Step 2</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h3>Enter Marks for <%= request.getAttribute("subject") %> - Section <%= request.getAttribute("section") %></h3>
    <form action="SaveMarksServlet" method="post" class="shadow p-4 bg-white rounded">
        <input type="hidden" name="subject" value="<%= request.getAttribute("subject") %>">
        <input type="hidden" name="section" value="<%= request.getAttribute("section") %>">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Student Name</th>
                    <th>Marks (out of 100)</th>
                </tr>
            </thead>
            <tbody>
            <%
                List<Map<String, String>> students = (List<Map<String, String>>) request.getAttribute("students");
                for (Map<String, String> student : students) {
            %>
                <tr>
                    <td><%= student.get("username") %></td>
                    <td><%= student.get("name") %></td>
                    <td>
                        <input type="number" name="mark_<%= student.get("username") %>" min="0" max="100" required>
                        <input type="hidden" name="usernames" value="<%= student.get("username") %>">
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <button type="submit" class="btn btn-success">Save Marks</button>
    </form>
</div>
</body>
</html>
