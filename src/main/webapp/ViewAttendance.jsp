<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Attendance</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow p-4">
        <h3 class="text-center mb-4">Attendance Details</h3>
        <%
            boolean found = (boolean) request.getAttribute("found");
            if (found) {
        %>
        <table class="table table-bordered text-center">
            <thead class="table-primary">
                <tr>
                    <th>Username</th>
                    <th>Total Periods</th>
                    <th>Present Periods</th>
                    <th>Attendance %</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= request.getAttribute("username") %></td>
                    <td><%= request.getAttribute("total") %></td>
                    <td><%= request.getAttribute("present") %></td>
                    <td><%= request.getAttribute("percentage") %> %</td>
                </tr>
            </tbody>
        </table>
        <%
            } else {
        %>
        <div class="alert alert-danger text-center">
            No attendance record found for <strong><%= request.getAttribute("username") %></strong>
        </div>
        <%
            }
        %>
        <div class="text-center mt-3">
            <a href="Student_Dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
        </div>
    </div>
</div>
</body>
</html>
