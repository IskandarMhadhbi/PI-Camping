package esprit.tunisiacamp.entities;

import esprit.tunisiacamp.entities.camping.CampingGround;
import esprit.tunisiacamp.entities.camping.Favorite;
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
    @ManyToOne
    Role role;
    String username;
    String password;
    String firstname;
    String lastname;
    @Temporal(TemporalType.DATE)
    Date birthday;
    String email;
    int telephone;
    int cin;
    String  picture;
    //region shop_part
    String shopname;
    int shop_phone;
    int postal_code;
    String shop_address;
    String  shop_picture;
    @ElementCollection
    @CollectionTable(name="shop_profit")
    @MapKeyColumn(name="month")
    @Column(name="profit")
    Map<String, Double> monthlyProfit;
    //endregion
    @Enumerated(EnumType.STRING)
    Provider  prodiver;
    @Enumerated(EnumType.STRING)
    State state;
    boolean enable;
    @ManyToMany
    List<ChatRoom> chatRooms;
    @OneToMany
    List<Post> posts;
    @OneToMany(mappedBy = "user")
    List<Claim> my_claims;
    @OneToMany(mappedBy = "admin")
    List<Claim> admin_claims;
    @ManyToOne
    CampingGround managed_ground;
    @OneToMany(mappedBy = "shopper")
    List<Transaction> transactions;

}
