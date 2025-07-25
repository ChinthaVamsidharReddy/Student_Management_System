
# Table, Create Table
users, CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(75) NOT NULL,
  `username` varchar(75) NOT NULL,
  `password` varchar(75) NOT NULL DEFAULT '123',
  `Contact_Number` varchar(10) NOT NULL,
  `DOB` varchar(12) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Department` varchar(10) NOT NULL,
  `Section` varchar(10) NOT NULL,
  `Batch` int NOT NULL,
  `total_periods` int DEFAULT '0',
  `present_periods` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



# Table, Create Table
staff, CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(75) NOT NULL,
  `username` varchar(75) NOT NULL,
  `password` varchar(75) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



-- For marks table 
CREATE TABLE marks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    subject VARCHAR(50),
    marks INT,
    section VARCHAR(10)
);
