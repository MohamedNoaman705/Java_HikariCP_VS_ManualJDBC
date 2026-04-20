package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee create(Employee e);
    List<Employee> findAll();
    Employee findById(int id);
    boolean update(Employee e);
    boolean deleteById(int id);
}
