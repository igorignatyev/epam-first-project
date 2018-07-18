package com.epam.lab.service;

import com.epam.lab.dao.ReviewDao;
import com.epam.lab.dao.ReviewDaoImpl;
import com.epam.lab.entity.Review;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private static final ReviewDao reviewDao = new ReviewDaoImpl();

    @Override
    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    @Override
    public Review find(int id) {
        return reviewDao.find(id);
    }

    @Override
    public void create(Review review) {
        reviewDao.create(review);
    }

    @Override
    public void update(int id, Review review) {
        reviewDao.update(id, review);
    }

    @Override
    public void delete(int id) {
        reviewDao.delete(id);
    }

    @Override
    public Review findByStudentIdAndCourseId(int studentId, int courseId) {
        return reviewDao.findByStudentIdAndCourseId(studentId, courseId);
    }
}
