package ru.techcoll.swords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.domain.PlayerProto;
import ru.techcoll.swords.domain.Roster;
import ru.techcoll.swords.domain.Unit;
import ru.techcoll.swords.repository.PlayerRepository;

/**
 * Сервис управления игроками
 */
@Service
@Transactional
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;


    /**
     * Регистрация нового игрока.
     *
     * @param email Адрес электронной почты.
     * @param nickname Ник игрока.
     * @return Объект игрока.
     */
    public Player registerPlayer(String email, String nickname) {
        PlayerProto proto = PlayerProto.getInstance();
        Player player = new Player(email, nickname);

        // инициализация игрока при регистрации
        // - выдаем ему золото
        player.setGold(proto.getInitialGold());

        // - начальные юниты и отряд
        Roster roster = new Roster(proto.getDefaultRosterName());
        for (String type : proto.getDefaultRoster()) {
            // создаем юнит, добавляем его в отряд игрока
            Unit unit = new Unit(type);
            roster.getUnits().add(unit);
        }
        player.setRoster1(roster);

        // сохраняем игрока в БД
        return playerRepository.save(player);
    }

    public Player createEmptyRoster(Long playerId, int slot, String name) {
        Player player = playerRepository.findOne(playerId);
        if (player == null)
            throw new IllegalArgumentException(String.format("Player (%d) not found", playerId));
        if (player.getRoster(slot) != null)
            throw new IllegalStateException(String.format("Slot (%d) is already occupied", slot));

        Roster roster = new Roster(name);
        player.setRoster(slot, roster);
        return player;
    }

    public Player deleteRoster(Long playerId, int slot) {
        Player player = playerRepository.findOne(playerId);
        if (player == null)
            throw new IllegalArgumentException(String.format("Player (%d) not found", playerId));

        player.setRoster(slot, null);
        return player;
    }

}
