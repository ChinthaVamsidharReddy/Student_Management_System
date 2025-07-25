<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Marks</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h3 class="mb-4">My Marks</h3>
    <%
        List<Map<String, Object>> marksList = (List<Map<String, Object>>) request.getAttribute("marksList");
        if (marksList == null || marksList.isEmpty()) {
    %>
        <div class="alert alert-warning">No marks found.</div>
    <%
        } else {
    %>
    <table class="table table-bordered text-center">
        <thead class="table-primary">
            <tr>
                <th>Subject</th>
                <th>Marks (out of 100)</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Map<String, Object> mark : marksList) {
        %>
            <tr>
                <td><%= mark.get("subject") %></td>
                <td><%= mark.get("marks") %></td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%
        }
    %>
    <a href="Student_Dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>
</body>
</html>
