package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements StudentDao {
    Connection connection = DatabaseConfig.getDBConnection();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();

        try {
            rs = statement.executeQuery("SELECT * FROM STUDENTS");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                studentList.add(new Student(id, firstName, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return studentList;
    }

    @Override
    public Student find(int id) {
        Student student = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM STUDENTS WHERE id=?");
            preparedStatement.setInt(1, id);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");

                student = new Student(id, firstName, lastName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return student;
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
}
