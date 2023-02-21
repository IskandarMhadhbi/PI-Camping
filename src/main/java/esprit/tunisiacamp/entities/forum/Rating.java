package esprit.tunisiacamp.entities.forum;

import esprit.tunisiacamp.entities.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Rating implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idRating;
    int value;
    @ManyToOne
    Post post_rate;
    @ManyToOne
    User user;
}
