package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements StudentDao {
    Connection conn = DatabaseConfig.getDBConnection();
    Statement stmt = null;
    ResultSet rs = null;

    {
        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();

        try {
            rs = stmt.executeQuery("SELECT * FROM STUDENTS");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                studentList.add(new Student(id, firstName, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    @Override
    public Student find(int id) {
        return null;
    }

    @Override
    public void create(Student obj) {

    }

    @Override
    public void update(Student obj) {

    }

    @Override
    public void delete(Student obj) {

    }

    public static void main(String[] args) {
        Connection conn = DatabaseConfig.getDBConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM COURSES");
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("id") + " Name: " + rs.getString("name") + " Description: " + rs.getString("description") + " Teacher_id: " + rs.getString("teacher_id"));
            }

            rs.close();
            stmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
