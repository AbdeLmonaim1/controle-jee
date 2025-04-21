package ma.enset.controlejee;

import ma.enset.controlejee.entities.Entreprise;
import ma.enset.controlejee.entities.EtatReservation;
import ma.enset.controlejee.entities.Reservation;
import ma.enset.controlejee.entities.TypeReservation;
import ma.enset.controlejee.repository.EntrepriseRepository;
import ma.enset.controlejee.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ControleJeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControleJeeApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(EntrepriseRepository entrepriseRepository, ReservationRepository reservationRepository){
        return args -> {
            Entreprise entreprise1 = Entreprise.builder()
                    .nom("Enset Moahmmedia")
                    .username("Enset")
                    .activite("Eduation")
                    .email("enset.media@univh2c.ma")
                    .build();
           entrepriseRepository.save(entreprise1);
            Reservation reservation1 = Reservation.builder()
                    .type(TypeReservation.CENTRE_CALCUL)
                    .dateReservation(new Date())
                    .dureeReservation("2 month")
                    .cout(12500.00)
                    .etat(EtatReservation.PENDING)
                    .build();
            reservationRepository.save(reservation1);
        };
    }
}
