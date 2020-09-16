package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerU
{
    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "customer_id_sequence", strategy = GenerationType.SEQUENCE)
//    @GenericGenerator(name = "order_id_sequence", strategy = "sequence", parameters = {
//            @Parameter(name="sequence", value="order_id_sequence")
//    })
    private Long customerId;

    @NaturalId
    @Column(name = "email")
    private String email;

    private String surname;

    private String firstName;

    private String city;

    private String postalCode;

    private Long houseNumber;

    private String phoneNumber;

    private String street;

    private Long flatNumber;

    private String password;
}
