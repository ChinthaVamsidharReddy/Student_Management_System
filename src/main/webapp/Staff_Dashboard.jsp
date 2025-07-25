<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Staff Dashboard</title>
    <style >
		    * {
		  margin: 0;
		  padding: 0;
		  box-sizing: border-box;
		}
		
		body {
		  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
		  background-color: #f5f6fa;
		  display: flex;
		  height: 100vh;
		}
		
		/* Sidebar */
		.sidebar {
		  width: 250px;
		  background: #2c3e50;
		  color: white;
		  height: 100vh;
		  padding: 30px 20px;
		  position: fixed;
		}
		
		.sidebar h2 {
		  text-align: center;
		  margin-bottom: 30px;
		  font-size: 24px;
		}
		
		.sidebar ul {
		  list-style: none;
		}
		
		.sidebar ul li {
		  margin: 20px 0;
		}
		
		.sidebar ul li a {
		  color: white;
		  text-decoration: none;
		  font-size: 16px;
		  display: block;
		  padding: 10px;
		  border-radius: 4px;
		  transition: background 0.3s;
		}
		
		.sidebar ul li a:hover {
		  background: #34495e;
		}
		
		/* Main Content */
		.main-content {
		  margin-left: 270px;
		  padding: 40px;
		  width: calc(100% - 270px);
		}
		
		.header h1 {
		  font-size: 32px;
		  color: #2c3e50;
		  margin-bottom: 20px;
		}
		
		.info h2 {
		  font-size: 24px;
		  margin-bottom: 10px;
		  color: #34495e;
		}
		
		.info p {
		  font-size: 16px;
		  color: #555;
		}
    
    </style>
</head>
<body>

    <!-- Sidebar -->
    <div class="sidebar">
        <h2>Staff Panel</h2>
        <ul>
            <li><a href="dashboard.jsp">Dashboard</a></li>
            <li><a href="Attendance-Form.jsp">Attendance</a></li>
            <li><a href="AddStudentDetailes.jsp">Add Student</a></li>
            <li><a href="marks-entry.jsp">Marks</a></li>
            <li><a href="AdminLogin.html">Logout</a></li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <div class="header">
            <h1>Welcome, Staff</h1>
        </div>
        <div class="info">
            <h2>Dashboard</h2>
            <p>This panel allows staff members to manage student data, mark attendance, enter results, and more.</p>
        </div>
    </div>

</body>
</html>
