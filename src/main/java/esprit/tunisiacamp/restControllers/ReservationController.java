package esprit.tunisiacamp.restControllers;

import esprit.tunisiacamp.entities.camping.Reservation;
import esprit.tunisiacamp.services.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private IReservationService reservationService;
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);}
    @PutMapping("affecterReservationToCamping")
    public void affecterReservationToCamping(@RequestParam int idr, @RequestParam int idc, @RequestParam int idu){
        reservationService.affecterReservationToCamping(idr,idc,idu);
    }
    @GetMapping("getReservation")
    public List<Reservation> getAllReservation(Integer userId) {
        return reservationService.getAllReservation(userId);}
    @DeleteMapping("deletereservation/{id}")
    public void deleteReservationById(@RequestParam long id){
        reservationService.deleteReservationById(id);
    }
}
