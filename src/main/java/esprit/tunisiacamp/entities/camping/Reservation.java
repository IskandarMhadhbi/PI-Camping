package esprit.tunisiacamp.entities.camping;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Reservation implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idReservation;
    int participant_number;
    @Temporal(TemporalType.DATE)
    Date start_date;
    @Temporal(TemporalType.DATE)
    Date end_date;
    @ManyToOne
    CampingGround campingGround;
}
