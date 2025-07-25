<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="date"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        input[type="submit"] {
            margin-top: 20px;
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 6px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Student Registration</h2>
    <form action="AddStudentDetails" method="post">
        <label>Full Name:</label>
        <input type="text" name="name" required>

        <label>Username:</label>
        <input type="text" name="username" required>

        <label>Contact Number:</label>
        <input type="text" name="contact_number" required>

        <label>Date of Birth:</label>
        <input type="date" name="Date_of_birth" required>

        <label>Gender:</label>
        <select name="gender" required>
            <option value="">Select</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
            <option value="Other">Other</option>
        </select>

        <label>Department:</label>
        <input type="text" name="department" required>

        <label>Section:</label>
        <input type="text" name="section" required>

        <label>Batch:</label>
        <input type="text" name="batch" required>

        <input type="submit" value="Submit">
    </form>
    <form action="Staff_Dashboard.jsp" method="get">
    <input href="Staff_Dashboard.jsp" type="submit" value="Back">
    </a>
</form>

</div>
</body>
</html>
