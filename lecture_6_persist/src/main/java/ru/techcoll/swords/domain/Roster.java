package ru.techcoll.swords.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Отряд игрока.
 */
@Entity
public class Roster implements Serializable {

    @Id
    @GeneratedValue(generator ="roster_seq")
    @GenericGenerator(
            name = "roster_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "roster_seq"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Unit> units = new HashSet<>();

    protected Roster() { }

    public Roster(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return String.format("Roster #%d (name=%s)", this.id, this.name);
    }

}
