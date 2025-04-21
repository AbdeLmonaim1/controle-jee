package ma.enset.controlejee.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String activite;
    private String username;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
