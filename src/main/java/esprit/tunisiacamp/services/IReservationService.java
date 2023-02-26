package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.camping.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation saveReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    void deleteReservationById(long id);
    List<Reservation> getAllReservation(Integer userId);
    void affecterReservationToCamping (Integer reservationId , Integer campingId,Integer userId);
}
