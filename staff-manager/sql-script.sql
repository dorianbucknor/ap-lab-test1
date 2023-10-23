CREATE DATABASE IFNOT EXISTS StaffManagement;
USE StaffManagement;

CREATE TABLE IFNOT EXISTS Employee(
    id VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    salary DOUBLE(10, 2),
    gender VARCHAR(10) NOT NULL 
);
