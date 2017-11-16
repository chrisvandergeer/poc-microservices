package nl.cge.toerekenen.betaling;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.cge.toerekenen.base.ToerekenenEntity;

import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
public class Betaling extends ToerekenenEntity {

    private String betalingskenmerk;
    private Integer bedrag;

}
