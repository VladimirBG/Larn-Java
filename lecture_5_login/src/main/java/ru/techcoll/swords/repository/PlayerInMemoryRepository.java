package ru.techcoll.swords.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.techcoll.swords.domain.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PlayerInMemoryRepository implements PlayerRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Players by ID.
     */
    private final Map<Long, Player> playersById = new HashMap<>();

    /**
     * Players by E-mail.
     */
    private final Map<String, Player> playersByEmail = new HashMap<>();

    private AtomicLong lastPlayerId = new AtomicLong(0);

    @Override
    public Player findById(Long id) {
        return playersById.get(id);
    }

    @Override
    public Player findByEmail(String email) {
        return playersByEmail.get(email.toLowerCase());
    }

    @Override
    public void save(Player player) {
        if (player.getId() == null) {
            // новый игрок, надо сгенерировать ему ID
            player.setId(lastPlayerId.incrementAndGet());
        }

        playersById.put(player.getId(), player);
        playersByEmail.put(player.getEmail().toLowerCase(), player);
    }

    @Override
    public void remove(Player player) {
        playersById.remove(player.getId());
        playersByEmail.remove(player.getEmail());
    }

}
