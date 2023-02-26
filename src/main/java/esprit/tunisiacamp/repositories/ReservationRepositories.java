package esprit.tunisiacamp.repositories;

import esprit.tunisiacamp.entities.camping.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepositories extends CrudRepository<Reservation,Long> {
    @Query(value = "select * from reservation r where r.user_reservation_id_user=?1",nativeQuery=true)
    List<Reservation> getReservation(Integer userId);
}
