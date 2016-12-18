package ru.techcoll.swords.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.techcoll.swords.domain.Player;
import ru.techcoll.swords.repository.PlayerRepository;

import java.util.Arrays;

@Service
public class PlayerService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Player registerPlayer(String name, String nickname, String email, String password) {
        Player player = new Player();
        player.setName(name);
        player.setNickname(nickname);
        player.setEmail(email);
        player.setPassword(passwordEncoder.encode(password));

        repository.save(player);
        return player;
    }

    public Player getPlayerByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Player player = repository.findByEmail(s);
        if (player == null)
            throw new UsernameNotFoundException("User not found");

        return new User(
            player.getEmail(),
            player.getPassword(),
            Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
