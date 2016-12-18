package ru.techcoll.swords.repository;

import ru.techcoll.swords.domain.Player;

public interface PlayerRepository {

    public Player findById(Long id);

    public Player findByEmail(String email);

    public void save(Player player);

    public void remove(Player player);

}
