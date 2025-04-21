package ma.enset.controlejee.repository;


import ma.enset.controlejee.entities.Entreprise;
import ma.enset.controlejee.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
