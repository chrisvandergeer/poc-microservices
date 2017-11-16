package nl.cge.toerekenen.vordering;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.cge.toerekenen.base.ToerekenenEntity;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class Vordering extends ToerekenenEntity {

    private String heffingkenmerk;
    private String betalingskenmerk;
    private Integer belastingjaar;
    private String middel;
    private Integer belasting;


}
