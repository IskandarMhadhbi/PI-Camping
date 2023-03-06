package esprit.tunisiacamp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.forum.*;
import esprit.tunisiacamp.entities.forum.Post;
import esprit.tunisiacamp.entities.shopping.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax. persistence.*;
import java.io.Serializable;
import java.util.*;

import esprit.tunisiacamp.entities.enums.Provider;
import esprit.tunisiacamp.entities.enums.State;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idUser;
    @JsonIgnore
    @ManyToOne
    Role role ;
    String username;
    String password;
    String firstname;
    String lastname;
    @Temporal(TemporalType.DATE)
    Date birthday;
    String email;
    int telephone;
    int cin;
    String shopname;
    int shop_phone;
    int postal_code;
    String address;
    @Enumerated(EnumType.STRING)
    Provider  prodiver;
    @Enumerated(EnumType.STRING)
    State state;
    String verificationCode;
    boolean enable;
    @JsonIgnore
    @ManyToMany
    List<ChatRoom> chatRooms;
    @JsonIgnore
    @OneToMany
    List<Post> posts;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<Claim> my_claims;
    @JsonIgnore
    @OneToMany(mappedBy = "admin")
    List<Claim> admin_claims;
    @JsonIgnore
    @ManyToOne
    CampingGround managed_ground;
    @JsonIgnore
    @OneToMany(mappedBy = "shopper")
    List<Transaction> transactions;
    @OneToMany(mappedBy = "UserAuth" ,fetch = FetchType.EAGER)

    private Set<Autority> autority;



}
