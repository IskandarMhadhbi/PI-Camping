package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Reservation;
import esprit.tunisiacamp.repositories.CampingGroundRepositories;
import esprit.tunisiacamp.repositories.ReservationRepositories;
import esprit.tunisiacamp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationService implements IReservationService{
    @Autowired
    private ReservationRepositories reservationRepository;
    @Autowired
    private CampingGroundRepositories campingGroundRepositories;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return null;
    }

    @Override
    public void deleteReservationById(long id) {
        reservationRepository.deleteById(id);

    }

    @Override
    public List<Reservation> getAllReservation(Integer userId) {
        return reservationRepository.getReservation(userId);
    }
    //private CampingGroundRepositories campingGroundRepositories;


    @Override
    public void affecterReservationToCamping(Integer reservationId, Integer campingId,Integer userId) {
        Reservation rs = reservationRepository.findById(Long.valueOf(reservationId)).get();
        CampingGround cp = campingGroundRepositories.findById(Long.valueOf(campingId)).get();
        User us = userRepository.findById(Long.valueOf(userId)).get();
        rs.setUserReservation(us);
        rs.setCampingGround(cp);
        reservationRepository.save(rs);
    }
}
