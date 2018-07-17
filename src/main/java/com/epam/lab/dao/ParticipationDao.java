package com.epam.lab.dao;

import com.epam.lab.entity.Participation;

import java.util.List;

public interface ParticipationDao extends GenericDao<Participation> {
    List<Participation> findAllByStudentId(int id);
}
