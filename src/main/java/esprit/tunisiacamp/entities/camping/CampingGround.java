package esprit.tunisiacamp.entities.camping;

import esprit.tunisiacamp.entities.Activity;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Genre;
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

public class CampingGround implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idCampingGround;
    String name;
    String image;
    String location;
    String description;
    int capacity;
    @Enumerated(EnumType.STRING)
    Genre genre;
    @OneToMany(mappedBy = "campingGround")
    List<Reservation> reservations;
    @OneToMany(mappedBy = "managed_ground")
    List<User> managers;
    @ManyToMany
    List<Activity> activities;
    @ManyToMany
    List<Advantage> advantages;
    @OneToMany(mappedBy = "review_Ground")
    List<Review> reviews;
    @OneToMany(mappedBy = "campingG_fav")
    List<Favorite> favorites;

}
