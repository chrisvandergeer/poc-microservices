package nl.cge.toerekenen.toewijzing;

import nl.cge.toerekenen.base.BaseEntity;
import nl.cge.toerekenen.betaling.Betaling;
import nl.cge.toerekenen.vordering.Vordering;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Toewijzing extends BaseEntity {

    @OneToOne
    private Vordering vordering;

    @OneToOne
    private Betaling betaling;

    public Vordering getVordering() {
        return vordering;
    }

    public void setVordering(Vordering vordering) {
        this.vordering = vordering;
    }

    public Betaling getBetaling() {
        return betaling;
    }

    public void setBetaling(Betaling betaling) {
        this.betaling = betaling;
    }

    @Override
    public String toString() {
        return "Toewijzing{" +
                "vordering=" + vordering +
                ", betaling=" + betaling +
                '}';
    }
}
