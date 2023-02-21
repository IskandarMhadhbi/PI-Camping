package esprit.tunisiacamp.entities.shopping;

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

public class Invoice implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long idInvoice;
    float total;
    String payment_method;
    @Temporal(TemporalType.DATE)
    Date bill_date;
    Boolean payment;
    Boolean shipment;
    @OneToMany
    List<Transaction> transactions;
    @OneToOne(mappedBy = "invoice")
    Delivery delivery;
}
