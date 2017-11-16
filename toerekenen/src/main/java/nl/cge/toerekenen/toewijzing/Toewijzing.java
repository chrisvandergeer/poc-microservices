package nl.cge.toerekenen.toewijzing;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.cge.toerekenen.base.ToerekenenEntity;
import nl.cge.toerekenen.betaling.Betaling;
import nl.cge.toerekenen.vordering.Vordering;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
@Entity
public class Toewijzing extends ToerekenenEntity {

    @OneToOne
    private Vordering vordering;

    @OneToOne
    private Betaling betaling;

}
