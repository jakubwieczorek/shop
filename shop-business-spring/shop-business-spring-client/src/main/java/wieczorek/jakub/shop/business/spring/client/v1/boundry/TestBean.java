package wieczorek.jakub.shop.business.spring.client.v1.boundry;

import wieczorek.jakub.shop.business.spring.client.v1.dto.CustomerDTO;

import java.util.List;

public interface TestBean
{
    List<CustomerDTO> readCustomers();
}
