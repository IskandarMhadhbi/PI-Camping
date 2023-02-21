package esprit.tunisiacamp.entities.forum;

import esprit.tunisiacamp.entities.User;
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

public class Post implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idPost;
    String Content;
    @Temporal(TemporalType.DATE)
    Date creation;
    @OneToMany(mappedBy = "post")
    List<Comment> comments;
    @ManyToOne
    User user;
    @OneToMany(mappedBy = "post_rate")
    List<Rating> ratings;
}
