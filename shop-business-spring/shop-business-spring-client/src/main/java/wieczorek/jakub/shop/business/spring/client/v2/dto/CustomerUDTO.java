package wieczorek.jakub.shop.business.spring.client.v2.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerUDTO
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
}
