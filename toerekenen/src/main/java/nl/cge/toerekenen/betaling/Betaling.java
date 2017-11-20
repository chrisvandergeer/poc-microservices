package nl.cge.toerekenen.betaling;

import nl.cge.toerekenen.base.BaseEntity;
import nl.cge.toerekenen.base.EventEntity;

import javax.persistence.Entity;

@Entity
public class Betaling extends EventEntity {

    private String betalingskenmerk;
    private Integer bedrag;

    public String getBetalingskenmerk() {
        return betalingskenmerk;
    }

    public void setBetalingskenmerk(String betalingskenmerk) {
        this.betalingskenmerk = betalingskenmerk;
    }

    public Integer getBedrag() {
        return bedrag;
    }

    public void setBedrag(Integer bedrag) {
        this.bedrag = bedrag;
    }

    @Override
    public String toString() {
        return "Betaling{" +
                "betalingskenmerk='" + betalingskenmerk + '\'' +
                ", bedrag=" + bedrag +
                '}';
    }
}
