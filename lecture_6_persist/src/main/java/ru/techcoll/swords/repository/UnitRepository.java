package ru.techcoll.swords.repository;

import org.springframework.data.repository.CrudRepository;
import ru.techcoll.swords.domain.Unit;

/**
 * Репозиторий для хранения юнитов
 */
public interface UnitRepository extends CrudRepository<Unit, Long> {


}
