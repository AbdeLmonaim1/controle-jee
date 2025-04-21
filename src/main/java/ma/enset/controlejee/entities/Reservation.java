package ma.enset.controlejee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;
@Entity
@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateReservation;
    private TypeReservation type;
    private EtatReservation etat;
    private String dureeReservation;
    private Double cout;

}
