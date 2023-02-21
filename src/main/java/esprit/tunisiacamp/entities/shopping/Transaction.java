package esprit.tunisiacamp.entities.shopping;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.enums.Type;
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

public class Transaction implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idTransaction;
    @Enumerated(EnumType.STRING)
    Type type;
    @Temporal(TemporalType.DATE)
    Date rent_start_date;
    @Temporal(TemporalType.DATE)
    Date rent_end_date;
    float price;
    Boolean paid;
    Boolean shipment;
    String payment_method;
    @ManyToOne
    Tool tool;
    @OneToOne
    Promotion promotion;
    @ManyToOne
    User shopper;
    @ManyToOne
    Delivery delivery;
}
