<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Marks - Step 1</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h3>Select Section and Subject</h3>
    <form action="MarksEntryServlet" method="post" class="shadow p-4 bg-white rounded">
        <div class="mb-3">
            <label>Section:</label>
            <input type="text" name="section" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Subject:</label>
            <input type="text" name="subject" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Load Students</button>
    </form>
</div>
</body>
</html>
