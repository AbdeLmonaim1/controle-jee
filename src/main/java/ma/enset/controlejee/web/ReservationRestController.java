package ma.enset.controlejee.web;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import ma.enset.controlejee.entities.Reservation;
import ma.enset.controlejee.repository.ReservationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor

public class ReservationRestController {
    private ReservationRepository reservationRepository;
    @GetMapping
    @Operation(summary = "Lister toutes les réservations")
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    @GetMapping("/getReservation/{id}")
    @Operation(summary = "Obtenir une réservation par ID")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return reservationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @Operation(summary = "Créer une nouvelle réservation")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Modifier une réservation")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return reservationRepository.findById(id).map(existing -> {
            existing.setDateReservation(reservation.getDateReservation());
            existing.setCout(reservation.getCout());
            existing.setDureeReservation(reservation.getDureeReservation());
            existing.setEtat(reservation.getEtat());
            existing.setType(reservation.getType());
            reservationRepository.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une réservation")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (!reservationRepository.existsById(id)) return ResponseEntity.notFound().build();
        reservationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
