package wieczorek.jakub.shop.business.spring.client.v2.boundry;

import wieczorek.jakub.shop.business.spring.client.v2.dto.CategoryUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.CustomerUDTO;

import java.util.List;

public interface ShopClientManager {
    void createNewCustomer(CustomerUDTO customerDTO);
    void findCustomerByEmail(String email);
    List<CategoryUDTO> fetchCategories();
}
