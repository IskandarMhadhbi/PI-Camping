package esprit.tunisiacamp.entities.shopping;

import esprit.tunisiacamp.entities.enums.Season;
import esprit.tunisiacamp.entities.enums.Variety;
import esprit.tunisiacamp.entities.enums.Season;
import esprit.tunisiacamp.entities.enums.Variety;
import esprit.tunisiacamp.entities.shopping.Critique;
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

public class Tool implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idTool;
    String name;
    @Enumerated(EnumType.STRING)
    Variety variety;
    @Enumerated(EnumType.STRING)
    Season season;
    float price;
    boolean availibility;
    @OneToMany(mappedBy = "tool" )
    List<Critique> critiques;

}
