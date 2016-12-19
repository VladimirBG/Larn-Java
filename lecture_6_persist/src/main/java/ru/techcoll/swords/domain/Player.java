package ru.techcoll.swords.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Игрок
 */
@Entity
public class Player {

    @Id
    @GeneratedValue(generator ="player_seq")
    @GenericGenerator(
            name = "player_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "player_seq"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @NotBlank
    @Length(min = 6, max = 128)
    @Email
    @NaturalId
    @Column(nullable = false, length = 128)
    private String email;

    @NotBlank
    @Length(min = 6, max = 30)
    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false)
    private int gold;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  orphanRemoval = true)
    private Roster roster1;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Roster roster2;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Roster roster3;


    protected Player() { }

    public Player(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Roster getRoster1() {
        return roster1;
    }

    public void setRoster1(Roster roster1) {
        this.roster1 = roster1;
    }

    public Roster getRoster2() {
        return roster2;
    }

    public void setRoster2(Roster roster2) {
        this.roster2 = roster2;
    }

    public Roster getRoster3() {
        return roster3;
    }

    public void setRoster3(Roster roster3) {
        this.roster3 = roster3;
    }

    public Roster getRoster(int slot) {
        switch (slot) {
            case 1: return this.roster1;
            case 2: return this.roster2;
            case 3: return this.roster3;
            default: throw new IllegalArgumentException(String.format("Invalid slot (%s)", slot));
        }
    }

    public void setRoster(int slot, Roster roster) {
        switch (slot) {
            case 1: this.roster1 = roster; break;
            case 2: this.roster2 = roster; break;
            case 3: this.roster3 = roster; break;
            default: throw new IllegalArgumentException(String.format("Invalid slot (%s)", slot));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        return email.equals(player.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Player #%d (email=%s, nickname=%s)", this.id, this.email, this.nickname);
    }

}
