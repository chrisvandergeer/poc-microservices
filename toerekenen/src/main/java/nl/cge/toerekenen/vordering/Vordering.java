package nl.cge.toerekenen.vordering;

import nl.cge.toerekenen.base.BaseEntity;
import nl.cge.toerekenen.base.EventEntity;

import javax.persistence.Entity;

@Entity
public class Vordering extends EventEntity {

    private String heffingkenmerk;
    private String betalingskenmerk;
    private Integer belastingjaar;
    private String middel;
    private Integer belasting;

    public String getHeffingkenmerk() {
        return heffingkenmerk;
    }

    public void setHeffingkenmerk(String heffingkenmerk) {
        this.heffingkenmerk = heffingkenmerk;
    }

    public String getBetalingskenmerk() {
        return betalingskenmerk;
    }

    public void setBetalingskenmerk(String betalingskenmerk) {
        this.betalingskenmerk = betalingskenmerk;
    }

    public Integer getBelastingjaar() {
        return belastingjaar;
    }

    public void setBelastingjaar(Integer belastingjaar) {
        this.belastingjaar = belastingjaar;
    }

    public String getMiddel() {
        return middel;
    }

    public void setMiddel(String middel) {
        this.middel = middel;
    }

    public Integer getBelasting() {
        return belasting;
    }

    public void setBelasting(Integer belasting) {
        this.belasting = belasting;
    }

    @Override
    public String toString() {
        return "Vordering{" +
                "heffingkenmerk='" + heffingkenmerk + '\'' +
                ", betalingskenmerk='" + betalingskenmerk + '\'' +
                ", belastingjaar=" + belastingjaar +
                ", middel='" + middel + '\'' +
                ", belasting=" + belasting +
                '}';
    }
}
