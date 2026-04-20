package org.example;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import util.ConnectionProvider;
import util.DbUtil;
import util.HikariCpUtil;

import java.sql.SQLException;

public class Main {
    static void main() throws SQLException {
        int iterations = 1000;

        ConnectionProvider[] providers = {
                new DbUtil(),        // DriverManager
                new HikariCpUtil()  // HikariCP
        };

        String[] labels = {"DriverManager", "HikariCP"};

        for (int j = 0; j < providers.length; j++) {
            ConnectionProvider cp = providers[j];
            EmployeeDao employeeDao = new EmployeeDaoImpl(cp);
            System.out.println("Testing: " + labels[j]);
            long start = System.currentTimeMillis();
            for (int i = 0; i < iterations; i++) {
                try (java.sql.Connection c = cp.getConnection()) {
                    employeeDao.findAll();
                }catch (SQLException ex){

                }
            }
            long end = System.currentTimeMillis();
            System.out.println(labels[j] + " took: " + (end - start) + " ms for " + iterations + " connections\n");
        }
    }
}
