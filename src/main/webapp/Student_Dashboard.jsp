<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f9;
        }
        .sidebar {
            height: 100vh;
            width: 220px;
            position: fixed;
            background-color: #1e293b;
            color: #fff;
            padding-top: 20px;
        }
        .sidebar h2 {
            text-align: center;
            color: #10b981;
            margin-bottom: 30px;
        }
        .sidebar a {
            padding: 15px 20px;
            text-decoration: none;
            display: block;
            color: #cbd5e1;
            transition: 0.3s;
        }
        .sidebar a:hover {
            background-color: #334155;
            color: #fff;
        }
        .topbar {
            height: 60px;
            background-color: #10b981;
            color: white;
            padding: 15px 20px;
            margin-left: 220px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .menu-toggle {
            cursor: pointer;
            font-size: 24px;
        }
        .main-content {
            margin-left: 220px;
            padding: 20px;
        }
        .cards {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }
        .card {
            flex: 1;
            background-color: white;
            padding: 20px;
            border-left: 5px solid;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .card.blue { border-color: #3b82f6; }
        .card.green { border-color: #10b981; }
        .card.orange { border-color: #f59e0b; }
        .card.red { border-color: #ef4444; }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            border-radius: 5px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px;
            border: 1px solid #e2e8f0;
            text-align: left;
        }
        th {
            background-color: #f1f5f9;
            color: #374151;
        }
        tr:hover {
            background-color: #f9fafb;
        }
        #bars.show {
            display: block;
        }
        #bars {
            display: none;
            position: absolute;
            right: 20px;
            top: 60px;
            background-color: white;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            border-radius: 5px;
            overflow: hidden;
            z-index: 10;
        }
        #bars a {
            display: block;
            padding: 10px 20px;
            text-decoration: none;
            color: #333;
            transition: 0.2s;
        }
        #bars a:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <h2>Student Management</h2>
        <a href="Student_Dashboard.jsp">Dashboard</a>
        <a href="ViewAttendance">Attendance</a>
        <a href="StudentMarksServlet">Marks</a>
        <a href="#">Schedule</a>
        <a href="index.html">Logout</a>
    </div>

    <div class="topbar">
        <div><strong>Dashboard</strong></div>
        <div>
            <div id="bars">
                <a href="#">Profile</a>
                <a href="#">Settings</a>
                <a href="index.html">Logout</a>
            </div>
        </div>
    </div>

    <div class="main-content">
        <div class="cards">
            <div class="card blue">
                <h3>ID</h3>
                <p><%= session.getAttribute("id") %></p>
            </div>
            <div class="card green">
                <h3>Name</h3>
                <p><%= session.getAttribute("name") %></p>
            </div>
            <div class="card orange">
                <h3>Department</h3>
                <p><%= session.getAttribute("Section") %></p>
            </div>
            <div class="card red">
                <h3>Section</h3>
                <p><%= session.getAttribute("Batch") %></p>
            </div>
        </div>

        <table>
            <tr>
                <th>ID</th>
                <td><%= session.getAttribute("id") %></td>
            </tr>
            <tr>
                <th>Name</th>
                <td><%= session.getAttribute("Name") %></td>
            </tr>
            <tr>
                <th>Username</th>
                <td><%= session.getAttribute("username") %></td>
            </tr>
            <tr>
                <th>Password</th>
                <td><%= session.getAttribute("Contact_Number") %></td>
            </tr>
            <tr>
                <th>Contact Number</th>
                <td><%= session.getAttribute("DOB") %></td>
            </tr>
            <tr>
                <th>Date of Birth</th>
                <td><%= session.getAttribute("Gender") %></td>
            </tr>
            <tr>
                <th>Gender</th>
                <td><%= session.getAttribute("Department") %></td>
            </tr>
            <tr>
                <th>Department</th>
                <td><%= session.getAttribute("Section") %></td>
            </tr>
            <tr>
                <th>Section</th>
                <td><%= session.getAttribute("Batch") %></td>
            </tr>
        </table>
    </div>
</body>
</html>