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
public class Message implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idMessage;
    String content;
    @Temporal(TemporalType.DATE)
    Date creation;
    @ManyToOne
    ChatRoom chatRoom;
    @ManyToOne
    User user;
}
