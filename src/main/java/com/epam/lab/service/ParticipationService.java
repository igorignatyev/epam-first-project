package com.epam.lab.service;

import com.epam.lab.entity.Participation;

import java.util.List;

public interface ParticipationService {
    List<Participation> findAll();

    Participation find(int id);

    void create(Participation participation);

    void update(int id, Participation participation);

    void delete(int id);

    List<Participation> findAllByStudentId(int id);

    Participation findByStudentIdAndCourseId(int studentId, int courseId);
}
