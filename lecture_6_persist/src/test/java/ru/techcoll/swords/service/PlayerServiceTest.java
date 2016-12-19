package ru.techcoll.swords.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.domain.Roster;
import ru.techcoll.swords.repository.PlayerRepository;
import ru.techcoll.swords.repository.RosterRepository;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RosterRepository rosterRepository;


    @Test
    @Transactional
    // тест выполняется в рамках транзакции, которая после его прохождения автоматически отменяется
    //- если хотим запомнить модификации, сделанные в БД тестом @Commit
    public void registerPlayer() throws Exception {

        Player saved = playerService.registerPlayer("player@game.org", "Newbie");

        Player found = playerRepository.findByEmail("player@game.org");

        assertEquals(saved, found);

    }

    @Test
    @Transactional
    public void createEmptyRoster() throws Exception {

        Player saved = playerService.registerPlayer("player@game.org", "Newbie");
        Player found = playerService.createEmptyRoster(saved.getId(), 2, "Black Alpha");

        assertEquals(saved, found);
        assertNotNull(found.getRoster(2));
        assertNull(found.getRoster(2).getId());
        assertEquals("Black Alpha", found.getRoster(2).getName());

    }

    @Test
    public void deleteRoster() throws Exception {

        Player saved = playerService.registerPlayer("player@game.org", "Newbie");
        Player found = playerService.createEmptyRoster(saved.getId(), 2, "Black Alpha");

        assertEquals(saved, found);
        assertNotNull(found.getRoster(2));
        assertNotNull(found.getRoster(2).getId());
        assertEquals("Black Alpha", found.getRoster(2).getName());

        Long rosterId = found.getRoster(2).getId();

        found = playerService.deleteRoster(saved.getId(), 2);
        assertEquals(saved, found);
        assertNull(found.getRoster(2));

        Roster roster = rosterRepository.findOne(rosterId);
        assertNull(roster);

        // этот тест не транзакционный, поэтому надо выполнить очистку вручную
        playerRepository.delete(saved);

    }

}