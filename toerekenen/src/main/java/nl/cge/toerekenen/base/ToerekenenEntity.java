package nl.cge.toerekenen.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
public class ToerekenenEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long eventId;
    private String objectId;

}
