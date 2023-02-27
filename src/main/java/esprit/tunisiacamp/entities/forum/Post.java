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
    String ContentPost;
    @Temporal(TemporalType.TIME)
            @Column(nullable = false)
    Date creation;
@PrePersist
private void onCreate (){
    creation = new Date() ;
}
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> comments;
    @ManyToOne
    User user;
    @OneToMany(mappedBy = "post_rate", cascade = CascadeType.REMOVE)
    List<Rating> ratings;
}
