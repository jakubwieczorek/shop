package wieczorek.jakub.shop.business.spring.client.boundry;

import wieczorek.jakub.shop.business.spring.client.dto.CategoryDTO;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;

import java.util.List;

public interface ShopClientManager {
    void createNewCustomer(CustomerDTO customerDTO);
    void findCustomerByEmail(String email);
    List<CategoryDTO> fetchCategories();
}
