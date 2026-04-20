package dao;

import model.Employee;
import util.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{

    private final ConnectionProvider cp;

    public EmployeeDaoImpl(ConnectionProvider cp){
        this.cp = cp;
    }

    @Override
    public Employee create(Employee e) {
        final String sql = "INSERT INTO employees (id, name, email, department, salary) VALUES (?,?,?,?,?)";

        try(Connection con = cp.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);){

            stmt.setInt(1, e.getId());
            stmt.setString(2, e.getName());
            stmt.setString(3, e.getEmail());
            stmt.setString(4, e.getDepartment());
            stmt.setBigDecimal(5, e.getSalary());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return e;
            } else {
                throw new RuntimeException("Insert failed, no rows affected.");
            }
        }catch (SQLException ex){
            throw new RuntimeException("Failed to fetch employees", ex);
        }
    }

    @Override
    public List<Employee> findAll() {
        final String sql = "SELECT id, name, email, department, salary FROM employees ORDER BY id";

        List<Employee> employees = new ArrayList<>();

        try(Connection c = cp.getConnection();
            PreparedStatement s = c.prepareStatement(sql);
            ResultSet res = s.executeQuery()){

            while (res.next()){
                Employee employee = new Employee(res.getInt("id"),
                        res.getString("name"),
                        res.getString("email"),
                        res.getString("department"),
                        res.getBigDecimal("salary"));

                employees.add(employee);
            }

            return employees;
        }catch (SQLException ex){
            throw new RuntimeException("Failed to fetch employees", ex);
        }
    }

    @Override
    public Employee findById(int id) {
        final String sql = "SELECT id, name, email, department, salary FROM employees WHERE id = ?" ;

        try(Connection c = cp.getConnection();
            PreparedStatement stmt = c.prepareStatement(sql)){;
            stmt.setInt(1, id);
            try(ResultSet res = stmt.executeQuery()) {

                if (res.next()) {
                    return new Employee(res.getInt("id"),
                            res.getString("name"),
                            res.getString("email"),
                            res.getString("department"),
                            res.getBigDecimal("salary"));
                }
                return null;
            }

        }catch (SQLException ex){
            throw new RuntimeException("Failed to fetch employees", ex);
        }
    }

    @Override
    public boolean update(Employee e) {
        final String sql = "UPDATE employees SET name = ?, email = ?, department = ?, salary = ? WHERE id = ?";
        try (Connection con = cp.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, e.getName());
            stmt.setString(2, e.getEmail());
            stmt.setString(3, e.getDepartment());
            stmt.setBigDecimal(4, e.getSalary());
            stmt.setInt(5, e.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            throw new RuntimeException("Failed to update employee", ex);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try(Connection con = cp.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);){
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0;
        }catch (SQLException ex){
            throw new RuntimeException("Failed to delete employee", ex);
        }
    }
}
