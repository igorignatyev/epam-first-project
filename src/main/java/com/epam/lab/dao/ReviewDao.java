package com.epam.lab.dao;

import com.epam.lab.entity.Review;

public interface ReviewDao extends GenericDao<Review>{
    Review findByStudentIdAndCourseId(int studentId, int courseId);
}