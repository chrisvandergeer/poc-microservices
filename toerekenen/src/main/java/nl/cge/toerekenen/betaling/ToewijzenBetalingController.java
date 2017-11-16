package nl.cge.toerekenen.betaling;

import nl.cge.toerekenen.toewijzing.Toewijzing;
import nl.cge.toerekenen.vordering.Vordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class ToewijzenBetalingController {

    private static final Logger log = LoggerFactory.getLogger(ToewijzenBetalingController.class);

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void execute(Betaling betaling) {
        TypedQuery<Vordering> query = entityManager.createQuery(
                "select v from Vordering v where betalingskenmerk = :hfk", Vordering.class);
        query.setParameter("hfk", betaling.getBetalingskenmerk());
        List<Vordering> vorderingen = query.getResultList();
        if (vorderingen.size() == 1) {
            Toewijzing toewijzing = new Toewijzing();
            toewijzing.setBetaling(betaling);
            toewijzing.setVordering(vorderingen.get(0));
            entityManager.persist(toewijzing);
            log.info("Toewijzing gemaakt " + toewijzing);
        } else {
            log.info("Geen toewijzing kunnen maken voor betaling " + betaling);
        }
    }
}
