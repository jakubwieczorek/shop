package wieczorek.jakub.shop.business.spring.client.v1.boundry;

import wieczorek.jakub.shop.business.spring.client.v1.dto.CategoryDTO;
import wieczorek.jakub.shop.business.spring.client.v1.dto.CustomerDTO;

import java.util.List;

public interface ShopClientManager {
    void createNewCustomer(CustomerDTO customerDTO);
    void findCustomerByEmail(String email);
    List<CategoryDTO> fetchCategories();
}
