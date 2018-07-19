package com.epam.lab.config;

import java.sql.*;

import org.h2.tools.DeleteDbFiles;

public class DatabaseConfig {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/electives";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    static {
        try {
            DeleteDbFiles.execute("~", "electives", true);
            createTables();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables() throws SQLException {
        Connection connection = getDBConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE COURSES(id INT PRIMARY KEY, name VARCHAR(255), description VARCHAR(255), teacher_id INT)");
            stmt.execute("INSERT INTO COURSES(id, name, description, teacher_id) VALUES(1, 'Java', 'This is Java course', 1)");
            stmt.execute("INSERT INTO COURSES(id, name, description, teacher_id) VALUES(2, 'C', 'This is C course', 2)");

            stmt.execute("CREATE TABLE PARTICIPATIONS(id INT PRIMARY KEY, student_id INT, course_id INT)");

            stmt.execute("CREATE TABLE REVIEWS(id INT PRIMARY KEY, feedback VARCHAR(255), mark INT, participation_id INT)");

            stmt.execute("CREATE TABLE STUDENTS(id INT PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), login VARCHAR(255), password VARCHAR(255))");
            stmt.execute("INSERT INTO STUDENTS(id, first_name, last_name, login, password) VALUES (1, 'Andrew', 'Ivanov', 'ivanov@login.com', '$2a$12$Jc8iGMcdypf7gu9d4FW7uuZawX/mtYY/91bG1qHMUf7vua7ValDZ.')");//11111
            stmt.execute("INSERT INTO STUDENTS(id, first_name, last_name, login, password) VALUES (2, 'Max', 'White', 'white@login.com', '$2a$12$.frhgGZkCNr3zMZ56KQYuuOpGH.76oQLHpa4oHvXOHoMjTcUR/qnG')");//22222

            stmt.execute("CREATE TABLE TEACHERS(id INT PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), login VARCHAR(255), password VARCHAR(255))");
            stmt.execute("INSERT INTO TEACHERS(id, first_name, last_name, login, password) VALUES (1, 'Roman', 'Fyodorov', 'fyodorov@login.com', '$2a$12$Jc8iGMcdypf7gu9d4FW7uuZawX/mtYY/91bG1qHMUf7vua7ValDZ.')");
            stmt.execute("INSERT INTO TEACHERS(id, first_name, last_name, login, password) VALUES (2, 'Alex', 'White', 'white@login.com', '$2a$12$Jc8iGMcdypf7gu9d4FW7uuZawX/mtYY/91bG1qHMUf7vua7ValDZ.')");
            
            stmt.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) stmt.close();
            if (rs != null) rs.close();

            connection.close();
        }
    }

    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
