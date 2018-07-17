package com.epam.lab.dao;

import com.epam.lab.config.DatabaseConfig;
import com.epam.lab.entity.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private Connection connection;
    private Statement statement = null;

    public CourseDaoImpl() {
        try {
            connection = DatabaseConfig.getDBConnection();
            statement = connection.createStatement();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Course> findAll() {
        List<Course> courseList = new ArrayList<>();

        String query = "SELECT id, name, description, teacher_id FROM COURSES";

        try (ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int teacherId = rs.getInt("teacher_id");

                courseList.add(new Course(id, name, description, teacherId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseList;
    }

    @Override
    public Course find(int id) {
        Course course = null;

        String query = "SELECT id, name, description, teacher_id FROM COURSES WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int teacherId = rs.getInt("teacher_id");

                    course = new Course(id, name, description, teacherId);
                }
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public void create(Course course) {
        int id = course.getId();
        String name = course.getName();
        String description = course.getDescription();
        int teacherId = course.getTeacherId();

        String query = "INSERT INTO COURSES (id, name, description, teacher_id) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, teacherId);

            preparedStatement.execute();

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Course course) {
        String name = course.getName();
        String description = course.getDescription();
        int teacherId = course.getTeacherId();

        String query = "UPDATE COURSES SET name=?, description=?, teacher_id=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, teacherId);
            preparedStatement.setInt(4, id);

            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM COURSES WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> findAvailable(int studentId) {
        List<Course> courseList = new ArrayList<>();

        String query = "SELECT COURSES.id, COURSES.name, COURSES.description, COURSES.teacher_id " +
                "FROM COURSES " +
                "WHERE COURSES.id NOT IN (SELECT course_id FROM PARTICIPATIONS WHERE student_id=?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int teacherId = rs.getInt("teacher_id");

                    courseList.add(new Course(id, name, description, teacherId));
                }
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return courseList;
    }

    @Override
    public List<Course> findRegistered(int studentId) {
        List<Course> registeredCourses = new ArrayList<>();

        String query = "SELECT COURSES.id, COURSES.name, COURSES.description, COURSES.teacher_id " +
                "FROM COURSES, PARTICIPATIONS " +
                "WHERE student_id=? AND COURSES.id = PARTICIPATIONS.course_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int teacherId = rs.getInt("teacher_id");

                    registeredCourses.add(new Course(id, name, description, teacherId));
                }
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return registeredCourses;
    }
}
