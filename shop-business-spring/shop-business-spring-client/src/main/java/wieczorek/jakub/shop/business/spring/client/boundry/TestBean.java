package wieczorek.jakub.shop.business.spring.client.boundry;

import org.springframework.stereotype.Service;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;

import java.util.List;

public interface TestBean
{
    List<CustomerDTO> readCustomers();
}
