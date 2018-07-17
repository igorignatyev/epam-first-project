package com.epam.lab.dao;

import com.epam.lab.entity.Participation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ParticipationDaoImplTest {
    ParticipationDao participationDao;
    
    @BeforeEach
    public void setUp() {
        participationDao = new ParticipationDaoImpl();
    }

    @Test
    void findAll() {
        List<Participation> actualList = participationDao.findAll();

        List<Participation> expectedList = Collections.singletonList(new Participation(1, 1, 1));

        assertEquals(actualList, expectedList);
    }

    @Test
    void find() {
        Participation actualParticipation = participationDao.find(1);

        Participation expectedParticipation = new Participation(1, 1, 1);

        assertEquals(actualParticipation, expectedParticipation);
    }

    @Test
    void create() {
        Participation participation = new Participation(2, 2, 2);
        participationDao.create(participation);

        List<Participation> actualList = participationDao.findAll();
        List<Participation> expectedList = Arrays.asList(new Participation(1, 1, 1), new Participation(2, 2, 2));

        assertEquals(actualList, expectedList);
    }

    @Test
    void update() {
        participationDao.update(1, new Participation(3, 3));

        List<Participation> actualList = participationDao.findAll();

        List<Participation> expectedList = Collections.singletonList(new Participation(1, 3, 3));

        assertEquals(actualList, expectedList);
    }

    @Test
    void delete() {
        participationDao.delete(1);

        List<Participation> actualList = participationDao.findAll();

        assertEquals(actualList.isEmpty(), true);
    }

}