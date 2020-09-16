package wieczorek.jakub.shop.business.spring.client.v2.boundry;

import wieczorek.jakub.shop.business.spring.client.v2.dto.CustomerUDTO;

import java.util.List;

public interface TestBean
{
    List<CustomerUDTO> readCustomers();
}
