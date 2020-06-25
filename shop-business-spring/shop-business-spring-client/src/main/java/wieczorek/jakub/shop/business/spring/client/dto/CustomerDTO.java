package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDTO
{
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
