# Java Employee CRUD App with JDBC and HikariCP

## Overview

This is a simple Java console application for managing employee records in a PostgreSQL database. The app demonstrates standard Create, Read, Update, and Delete (CRUD) operations using JDBC.  
A key feature is the ability to switch between classic `DriverManager`-based connections and [HikariCP](https://github.com/brettwooldridge/HikariCP), a high-performance JDBC connection pool.

## Features

- Console interface for CRUD operations on employees.
- Flexible database connection management:
  - Use standard JDBC via `DriverManager`
  - Use HikariCP connection pool for improved performance
- Clean code with `ConnectionProvider` interface for easy swapping and testing.
- Benchmark tool for quantifying the performance advantage of pooling.

## Architecture

- `model.Employee` - Employee data class.
- `dao.EmployeeDao` - DAO interface for CRUD.
- `dao.EmployeeDaoImpl` - JDBC implementation.
- `util.ConnectionProvider` - Interface for DB connections.
- `util.DbUtil` - Classic connection implementation.
- `util.HikariCPUtil` - HikariCP-based connection implementation.
- `Main` - Console application.
- `TestConnectionSpeed` - Benchmark tool.

## Setup

### Prerequisites

- Java 11 or higher.
- Maven.
- [PostgreSQL](https://www.postgresql.org/) running on `localhost:5432`, database `test`, user `postgres`, password `qweasd12`.
    - Create the database and user as needed.

### 1. Clone the repository

```sh
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
```

### 2. Build

```sh
mvn clean package
```

### 3. Run the App

- **With HikariCP (recommended):**  
  Change this line in `Main.java`:
  ```java
  ConnectionProvider cp = new HikariCPUtil();
  ```
- **With plain DriverManager:**  
  Use:
  ```java
  ConnectionProvider cp = new DbUtil();
  ```

Then simply run the `Main` class.

## Performance Benchmark

To demonstrate the advantage of using HikariCP, we conducted a simple test on a small PostgreSQL database (containing only 3 rows).

We timed how long it took to open and close 1000 connections with each approach:

```
Testing: DriverManager
DriverManager took: 190492 ms for 1000 connections

Testing: HikariCP
HikariCP took: 220 ms for 1000 connections
```

**Result:**  
HikariCP is **over 800 times faster** than the classic DriverManager approach, even on a tiny database!

> This huge speed difference is because DriverManager creates and destroys real connections each time, while HikariCP efficiently reuses a small pool of ready-to-use database connections.

---

**Bottom Line:**  
If you want fast, scalable, and reliable Java database access, always use a connection pool like HikariCP!

## Dependencies

- [HikariCP](https://github.com/brettwooldridge/HikariCP)
- PostgreSQL JDBC Driver
- SLF4J simple or Logback for logging (to prevent SLF4J warnings)

## Why HikariCP?

- Dramatically reduces the time to acquire and release DB connections.
- Efficient resource use under higher loads.
- Reduces risk of exhausting database connection limits.
- Easy integration—just implement `ConnectionProvider`.

## License

MIT (or add your preferred license here)

---

**Happy Coding!**
