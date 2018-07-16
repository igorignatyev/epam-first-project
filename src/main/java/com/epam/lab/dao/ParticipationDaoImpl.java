package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Participation;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

public class ParticipationDaoImpl implements ParticipationDao {
    private Statement statement = null;
    private Connection connection;

    public ParticipationDaoImpl() {
        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.createStatement();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Participation> findAll() {
        List<Participation> participationList = new ArrayList<>();
        String query = "SELECT id, student_id, course_id FROM PARTICIPATIONS";

        try (ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int studentId = rs.getInt("student_id");
                int courseId = rs.getInt("course_id");

                participationList.add(new Participation(id, studentId, courseId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participationList;
    }

    @Override
    public Participation find(int id) {
        Participation participation = null;
        String query = "SELECT id, student_id, course_id FROM PARTICIPATIONS WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    int courseId = rs.getInt("course_id");

                    participation = new Participation(id, studentId, courseId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return participation;
    }

    @Override
    public void create(Participation participation) {
        int id = participation.getId();
        int studentId = participation.getStudentId();
        int courseId = participation.getCourseId();

        String query = "INSERT INTO PARTICIPATIONS (id, student_id, course_id) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, courseId);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Participation participation) {
        int studentId = participation.getStudentId();
        int courseId = participation.getCourseId();

        String query = "UPDATE PARTICIPATIONS SET student_id=?, course_id=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.setInt(3, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM PARTICIPATIONS WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
