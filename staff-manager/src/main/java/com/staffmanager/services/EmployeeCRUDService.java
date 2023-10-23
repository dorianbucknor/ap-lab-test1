package com.staffmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.staffmanager.models.Employee;

public class EmployeeCRUDService {
    private Connection connection;
    private PreparedStatement statement;
    private Logger logger = LogManager.getLogger(EmployeeCRUDService.class);

    public EmployeeCRUDService() throws SQLException {
        connection = DBConnectionService.getConnection("jdbc:mysql://localhost:3306/staffmanagement", "root", "");
    }

    public void insert(Employee employee) throws SQLException {
        try {
            statement = connection.prepareStatement(
                    "INSERT INTO employee(id, name, salary, gender) VALUES (id=?, name=?, salary=?, gender=?);");
            statement.setString(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setDouble(3, employee.getSalary());
            statement.setString(4, employee.getGender());
            statement.execute();
        } catch (Exception e) {
            throw e;
        }
    }

    public Employee read(String id) throws SQLException {

        try {
            statement = connection.prepareStatement("SELECT ALL FROM employee WHERE id=?");
            statement.setString(1, id);
            statement.execute();

            ResultSet rs = statement.getResultSet();
            return new Employee(rs.getString("id"), rs.getString("name"), rs.getDouble("salary"),
                    rs.getString("gender"));

        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Employee> readAll() throws SQLException {

        try {
            statement = connection.prepareStatement("SELECT ALL FROM employee");
            statement.execute();

            
            ResultSet rs = statement.getResultSet();
            ArrayList<Employee> emps = new ArrayList<>();

            while (true) {
                emps.add(new Employee(rs.getString("id"), rs.getString("name"), rs.getDouble("salary"),
                        rs.getString("gender")));

                boolean hasnext = rs.next();

                if(!hasnext){
                    break;
                }
            }

            return emps;     
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Employee> readAllExec() throws SQLException {

        try {
            statement = connection
                    .prepareStatement("SELECT ALL (id, name, salary, gender) FROM Employee WHERE salary>?");
            statement.setDouble(1, 100000);
            statement.execute();

            ResultSet rs = statement.getResultSet();
            ArrayList<Employee> emps = new ArrayList<>();

            while (true) {
                emps.add(new Employee(rs.getString("id"), rs.getString("name"), rs.getDouble("salary"),
                        rs.getString("gender")));

                boolean hasnext = rs.next();

                if(!hasnext){
                    break;
                }
            }

            return emps;            

        } catch (Exception e) {
            throw e;
        }
    }

    // public void update(String id) throws SQLException {
    // try {
    // statement = connection.prepareStatement("UPDATE employee");
    // statement.setString(1, id);
    // statement.execute();

    // ResultSet rs = statement.getResultSet();
    // } catch (Exception e) {
    // throw e;
    // }
    // }

    public void delete(String id) throws SQLException {
        try {
            statement = connection.prepareStatement("DELETE FROM employee WHERE id=?");
            statement.setString(1, id);
            statement.execute();
        } catch (Exception e) {
            throw e;
        }
    }
}