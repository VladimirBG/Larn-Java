package ru.techcoll.swords.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.techcoll.swords.domain.Roster;

import java.util.Set;

/**
 * Репозиторий для хранения отрядов
 */
@Repository
public interface RosterRepository extends CrudRepository<Roster, Long> {

    public Set<Roster> findAll();

    public Roster findById(Long id);

    public Roster findByName(String name);

    @Query("select r from Roster r where r.units is not empty")
    public Set<Roster> findAllNonEmptyRosters();

}
