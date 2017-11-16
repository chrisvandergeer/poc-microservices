package nl.cge.toerekenen.vordering;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VorderingDao extends CrudRepository<Vordering, Long> {
}
