package ru.techcoll.swords.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.techcoll.swords.domain.Roster;
import ru.techcoll.swords.domain.Unit;
import ru.techcoll.swords.repository.RosterRepository;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RosterRepositoryTest {

    @Autowired
    private RosterRepository rosterRepository;

    @Test
    @Transactional
    public void createRoster() throws Exception {
        Roster roster = new Roster("test1");
        roster.getUnits().add(new Unit("unit1"));
        roster.getUnits().add(new Unit("unit2"));
        rosterRepository.save(roster);
        assertNotNull(roster.getId());

        Roster loaded = rosterRepository.findByName("test1");
        assertEquals(2, loaded.getUnits().size());
    }

    @Test
    @Transactional
    public void listRosters() throws Exception {
        Set<Roster> rosters = rosterRepository.findAll();

        assertThat(rosters.size(), greaterThan(0));
    }

    @Test
    @Transactional
    public void findByName() throws Exception {
        Roster roster1 = rosterRepository.findById(1L);
        assertEquals("roster1", roster1.getName());

        Roster roster2 = rosterRepository.findByName("roster2");
        assertEquals(2L, (long)roster2.getId());
    }

    @Test
    @Transactional
    public void findAllNonEmptyRosters() throws Exception {
        Set<Roster> rosters = rosterRepository.findAllNonEmptyRosters();

        assertThat(rosters.size(), is(2));
        assertThat(rosters, hasItem(hasProperty("name", is("roster2"))));
        assertThat(rosters, hasItem(hasProperty("name", is("roster3"))));
    }


}