package wieczorek.jakub.shop.business.spring.model.domain;

import lombok.Getter;
import lombok.Setter;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Customer")
@Getter
@Setter
public class Customer
{
    @Id
    private String email;

    private String surname;

    @Column(name = "first_name")
    private String firstName;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "house_number")
    private Long houseNumber;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    private String street;

    @Column(name = "flat_number")
    private Long flatNumber;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<Order>orders;

    public CustomerDTO toDTO()
    {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCity(this.getCity());
        customerDTO.setEmail(this.getEmail());
        customerDTO.setFirstName(this.getFirstName());
        customerDTO.setFlatNumber(this.getFlatNumber());
        customerDTO.setHouseNumber(this.getHouseNumber());
        customerDTO.setPassword(this.getPassword());
        customerDTO.setPostalCode(this.getPostalCode());
        customerDTO.setPhoneNumber(this.getPhoneNumber());
        customerDTO.setSurname(this.getSurname());
        customerDTO.setStreet(this.getStreet());
        return customerDTO;
    }
}
