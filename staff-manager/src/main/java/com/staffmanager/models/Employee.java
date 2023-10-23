package com.staffmanager.models;

public class Employee {
    private String id;
    private double salary;
    private String name;
    private String gender;

    public Employee(){
        this.gender = null;
        this.name = null;
        this.id=null;
        this.salary = 0.0;
    }

    public Employee(String id, String name, double salary, String gender){
        this.gender = gender;
        this.name = name;
        this.id=id;
        this.salary = salary;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGender() {
        return this.gender;
    }

    public double getSalary() {
        return this.salary;
    }

    @Override
    public String toString(){
        return "ID: " + id + " Name: " + name + " Gender: " + gender + " Salary: " + salary;
    }

}