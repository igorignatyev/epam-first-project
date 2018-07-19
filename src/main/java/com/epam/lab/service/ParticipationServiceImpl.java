package com.epam.lab.service;

import com.epam.lab.dao.ParticipationDao;
import com.epam.lab.dao.ParticipationDaoImpl;
import com.epam.lab.entity.Participation;

import java.util.List;

public class ParticipationServiceImpl implements ParticipationService {
    private static final ParticipationDao participationDao = new ParticipationDaoImpl();

    @Override
    public List<Participation> findAll() {
        return participationDao.findAll();
    }

    @Override
    public Participation find(int id) {
        return participationDao.find(id);
    }

    @Override
    public void create(Participation participation) {
        participationDao.create(participation);
    }

    @Override
    public void update(int id, Participation participation) {
        participationDao.update(id, participation);
    }

    @Override
    public void delete(int id) {
        participationDao.delete(id);
    }

    @Override
    public List<Participation> findAllByStudentId(int id) {
        return participationDao.findAllByStudentId(id);
    }

    @Override
    public Participation findByStudentIdAndCourseId(int studentId, int courseId) {
        return participationDao.findByStudentIdAndCourseId(studentId, courseId);
    }
}
