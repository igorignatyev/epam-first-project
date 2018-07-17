package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Review;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ReviewDaoImpl implements ReviewDao {
    private static Connection connection = null;
    private Statement statement = null;

    public ReviewDaoImpl() {
        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviewList = new ArrayList<>();
        String query = "SELECT id, feedback, mark, participation_id FROM REVIEWS";
        try (ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String feedback = rs.getString("feedback");
                int mark = rs.getInt("mark");
                int participationId = rs.getInt("participation_id");

                reviewList.add(new Review(id, feedback, mark, participationId));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return reviewList;
    }

    @Override
    public Review find(int id) {
        Review review = null;
        String query = "SELECT id, feedback, mark, participation_id FROM REVIEWS WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String feedback = rs.getString("feedback");
                    int mark = rs.getInt("mark");
                    int participationId = rs.getInt("participation_id");

                    review = new Review(id, feedback, mark, participationId);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return review;
    }

    @Override
    public void create(Review review) {
        int id = review.getId();
        String feedback = review.getFeedback();
        int mark = review.getMark();
        int participationId = review.getParticipationId();

        String query = "INSERT INTO REVIEWS (id, feedback, mark, participation_id) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, feedback);
            preparedStatement.setInt(3, mark);
            preparedStatement.setInt(4, participationId);

            preparedStatement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void update(int id, Review obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Review findByStudentIdAndCourseId(int studentId, int courseId) {
        Review review = null;

        String query = "SELECT REVIEWS.id, REVIEWS.feedback, REVIEWS.mark, REVIEWS.participation_id FROM REVIEWS, PARTICIPATIONS WHERE " +
                "REVIEWS.participation_id = PARTICIPATIONS.id AND " +
                "student_id=? AND course_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String feedback = rs.getString("feedback");
                    int mark = rs.getInt("mark");
                    int participationId = rs.getInt("participation_id");

                    review = new Review(id, feedback, mark, participationId);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return review;
    }
}
