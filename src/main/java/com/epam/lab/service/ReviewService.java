package com.epam.lab.service;

import com.epam.lab.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();

    Review find(int id);

    void create(Review review);

    void update(int id, Review review);

    void delete(int id);

    Review findByStudentIdAndCourseId(int studentId, int courseId);
}
