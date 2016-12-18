package ru.techcoll.swords.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.repository.PlayerRepository;

@Service
public class PlayerService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlayerRepository repository;

    public Player registerPlayer(String name, String nickname, String email, String password) {
        Player player = new Player();
        player.setName(name);
        player.setNickname(nickname);
        player.setEmail(email);
        player.setPassword(password);

        repository.save(player);
        return player;
    }

}
