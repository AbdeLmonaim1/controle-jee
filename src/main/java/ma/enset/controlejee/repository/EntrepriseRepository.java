package ma.enset.controlejee.repository;


import ma.enset.controlejee.entities.Entreprise;
import ma.enset.controlejee.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Page<Entreprise> findByUsernameContains(String keyword, Pageable pageable);
}
