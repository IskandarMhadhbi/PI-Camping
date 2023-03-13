package esprit.tunisiacamp.Camping;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Camping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int groupSize;
    private String preferredCampground;
    @Temporal(TemporalType.DATE)
    private Date checkInDate;
    @Temporal(TemporalType.DATE)
    private Date checkOutDate;
    private String specialRequests;
}
