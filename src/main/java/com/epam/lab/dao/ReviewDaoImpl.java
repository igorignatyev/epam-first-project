package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {
    private static Connection connection = null;
    private Statement statement = null;

    public ReviewDaoImpl() {
        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
}
