package model;

import java.math.BigDecimal;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String department;
    private BigDecimal salary;

    public Employee() {
    }

    public Employee(int id, String name, String email, String department, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getSalary(){
        return salary;
    }

    public void setSalary(BigDecimal salary){
        this.salary = salary;
    }

    @Override
    public String toString(){
        return "Employee:\n" +
                "  ID: " + id + "\n" +
                "  Name: " + name + "\n" +
                "  Email: " + email + "\n" +
                "  Department: " + department + "\n" +
                "  Salary: " + salary;
    }
}
