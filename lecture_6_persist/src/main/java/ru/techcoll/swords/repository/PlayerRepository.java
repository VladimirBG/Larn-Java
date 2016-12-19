package ru.techcoll.swords.repository;

import org.springframework.data.repository.CrudRepository;
import ru.techcoll.swords.domain.Player;

/**
 * Репозиторий для хранения игроков
 */
public interface PlayerRepository  extends CrudRepository<Player, Long> {

    public Player findByEmail(String email);

}
