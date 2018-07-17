package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Student;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements StudentDao {
    private Connection connection;
    private Statement statement = null;

    public StudentDaoImpl() {
        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT id, first_name, last_name FROM STUDENTS";

        try (ResultSet rs = statement.executeQuery(query)) {
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
        Student student = null;
        String query = "SELECT id, first_name, last_name FROM STUDENTS WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    student = new Student(id, firstName, lastName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void create(Student student) {
        int id = student.getId();
        String firstName = student.getFirstName();
        String lastName = student.getLastName();

        String query = "INSERT INTO STUDENTS (id, first_name, last_name) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Student student) {
        String firstName = student.getFirstName();
        String lastName = student.getLastName();

        String query = "UPDATE STUDENTS SET first_name=?, last_name=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM STUDENTS WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAllByCourseId(int courseId) {
        List<Student> studentList = new ArrayList<>();

        String query = "SELECT STUDENTS.id, STUDENTS.first_name, STUDENTS.last_name " +
                "FROM STUDENTS, PARTICIPATIONS " +
                "WHERE course_id=? AND STUDENTS.id = PARTICIPATIONS.student_id AND PARTICIPATIONS.id NOT IN (SELECT participation_id FROM REVIEWS)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, courseId);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    studentList.add(new Student(id, firstName, lastName));
                }
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return studentList;
    }
}