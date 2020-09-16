package wieczorek.jakub.shop.business.spring.client.v1.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class CustomerDTO
{
    private Long customerId;

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

    @JsonManagedReference
    private List<OrderDTO> orders = new LinkedList<>();

    public void addOrder(OrderDTO order)
    {
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(OrderDTO order)
    {
        order.setCustomer(null);
        orders.remove(order);
    }
}
