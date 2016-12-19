package ru.techcoll.swords.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Юнит игрока.
 */
@Entity
public class Unit implements Serializable {

    public final static String PIKEMAN = "PIKEMAN";

    public final static String ARCHER  = "ARCHER";

    public final static String GRIFFIN = "GRIFFIN";

    public final static String KNIGHT  = "KNIGHT";

    public final static String MAGE    = "MAGE";


    @Id
    @GeneratedValue(generator ="unit_seq")
    @GenericGenerator(
            name = "unit_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "unit_seq"),
                    @Parameter(name = "initial_value", value = "1000"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @Column(nullable = false, length = 12)
    private String type;

    protected Unit() { }

    public Unit(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Unit #%d (type=%s)", this.id, this.type);
    }

}
