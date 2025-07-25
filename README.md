# Student Management System (Java + JSP + JDBC + MySQL)

## 📌 Overview

The **Student Management System** is a web-based application built using **Java**, **JSP**, **Servlets**, **JDBC**, **HTML/CSS**, and **MySQL**. It allows students and staff to manage academic records efficiently, including attendance tracking, marks entry, and student detail management.

---

## 🎯 Key Features

### 🧑‍🎓 Student Dashboard
- View personal details after login (auto-fetched using session)
- View attendance percentage (calculated from present/total periods)
- View marks in each subject
- Logout securely

### 👩‍🏫 Staff Dashboard
- Add new student details
- Update, delete, or view all students (CRUD)
- Take attendance for a section
- Enter and save marks for students based on section & subject
- Redirects and form validations for smooth user experience

---

## 🛠️ Tech Stack


 
 - **Front-End** - HTML, CSS, JSP
 - **Back-End** - Java Servlets, JSP
 - **Database** - MySQL
 - **Connectivity** - JDBC (Java Database Connectivity)
 - **Tools/Server** - Apache Tomcat (v9+), MySQL Workbench





---

## 🔧 Database Schema Overview

- **users** table  
  `id`, `name`, `username`, `password`, `contact_number`, `gender`, `department`, `section`, `batch`, `dob`

- **attendance** table  is merged with **users**  table  
  `id`, `username`, `total_periods`, `present_periods`

- **marks** table  
  `id`, `username`, `subject`, `marks`, `section`

⚠️ *All queries are handled using prepared statements for security.*

---

## 🗂️ Folder Structure

Place the following files in this structure inside your Java Web Project:
<pre lang="text"> ```
Student_Management_System/
│
├── src/
│ └── com/studentmanagement/servlet/
│ ├── StudentLogin.java
│ ├── AddStudentDetails.java
│ ├── AttendanceServlet.java
│ ├── MarksEntryServlet.java
│ └── StudentMarksServlet.java
│
├── WebContent/
│ ├── login.jsp
│ ├── StudentDashboard.jsp
│ ├── StaffDashboard.jsp
│ ├── add-student.jsp
│ ├── view-marks.jsp
│ ├── view-attendance.jsp
│ ├── marks-entry.jsp
│ └── css/ ← All your CSS files
│
├── README.md  Save this file here
└── web.xml ← Servlet mappings
``` </pre>

---

## 🚀 How to Run

1. Import the project into **Eclipse** or **IntelliJ IDEA**
2. Set up your MySQL database using the provided schema
3. Update DB credentials in Java files
4. Add your MYSQL username and password in (ADMINLOGIN and STUDENTLOGIN)
5. Deploy the project on **Apache Tomcat 9+**
6. Access via: `http://localhost:8080/Student_Management_System`
                    or
   Access via: `http://localhost:8080/Student_Management_System/index.html`                 

---


---

## 🤝 Contributing
Feel free to **clone** this repository and explore the project.  
If you would like to **improve or add new features**, you're welcome to:
1. **Fork** the repository
2. **Clone** your fork
3. git clone https://github.com/your-username/Student_Management_System.git 
4. git push origin main 
5. git push -u origin <new_branch_name>
6. git push
   

          

---




## 👨‍💻 Author

Developed by **Vamsidhar Reddy**

---

#
