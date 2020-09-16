package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order>orders = new LinkedList<>();

    public void addOrder(Order order)
    {
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order)
    {
        order.setCustomer(null);
        orders.remove(order);
    }
}
