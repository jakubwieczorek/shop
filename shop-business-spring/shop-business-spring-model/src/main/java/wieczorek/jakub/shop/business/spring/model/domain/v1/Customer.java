package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Customer")
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
    @Setter(AccessLevel.NONE)
    private Long customerId;

    @NaturalId
    @Column(name = "email", unique = true)
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

    public Customer()
    {

    }

    public CustomerDTO toDTO()
    {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setCity(getCity());
        customerDTO.setEmail(getEmail());
        customerDTO.setFirstName(getFirstName());
        customerDTO.setFlatNumber(getFlatNumber());
        customerDTO.setHouseNumber(getHouseNumber());
        customerDTO.setPassword(getPassword());
        customerDTO.setPostalCode(getPostalCode());
        customerDTO.setPhoneNumber(getPhoneNumber());
        customerDTO.setSurname(getSurname());
        customerDTO.setStreet(getStreet());
        customerDTO.setCustomerId(getCustomerId());

        customerDTO.setOrders(orders.stream().map(Order::toDTO).collect(Collectors.toList()));

        return customerDTO;
    }

    public Customer(CustomerDTO customerDTO)
    {
        setCity(customerDTO.getCity());
        setEmail(customerDTO.getEmail());
        setFirstName(customerDTO.getFirstName());
        setFlatNumber(customerDTO.getFlatNumber());
        setHouseNumber(customerDTO.getHouseNumber());
        setPassword(customerDTO.getPassword());
        setPostalCode(customerDTO.getPostalCode());
        setPhoneNumber(customerDTO.getPhoneNumber());
        setSurname(customerDTO.getSurname());
        setStreet(customerDTO.getStreet());

        setOrders(customerDTO.getOrders().stream().map(Order::new).collect(Collectors.toList()));
    }
}
