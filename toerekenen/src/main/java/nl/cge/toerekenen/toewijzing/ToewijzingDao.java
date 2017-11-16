package nl.cge.toerekenen.toewijzing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToewijzingDao extends CrudRepository<Toewijzing, Long> {
}
