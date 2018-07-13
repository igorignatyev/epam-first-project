package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    private final Connection connection = DatabaseConfig.getDBConnection();
    private Statement statement = null;

    public TeacherDaoImpl() {
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> findAll() {
        List<Teacher> teacherList = new ArrayList<>();
        String query = "SELECT id, first_name, last_name FROM TEACHERS";
        try (ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                teacherList.add(new Teacher(id, firstName, lastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherList;
    }

    public Teacher find(int id) {
        Teacher teacher = null;
        String query = "SELECT id, first_name, last_name FROM TEACHERS WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    teacher = new Teacher(id, firstName, lastName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public void create(Teacher teacher) {
        int id = teacher.getId();
        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        String query = "INSERT INTO TEACHERS (id, first_name, last_name) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Teacher teacher) {
        String firstName = teacher.getFirstName();
        String lastName = teacher.getLastName();

        String query = "UPDATE TEACHERS SET first_name=?, last_name=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM TEACHERS WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
