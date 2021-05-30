package wieczorek.jakub.shop.business.spring.client.v2.boundry;

import wieczorek.jakub.shop.business.spring.client.v2.dto.CategoryUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.CustomerUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.ProductOrderUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.ProductUDTO;

import java.util.List;

public interface ShopClientManager {
    void createNewCustomer(CustomerUDTO customerDTO);
    void findCustomerByEmail(String email);
    List<CategoryUDTO> fetchCategories();
    List<ProductUDTO> fetchProducts(Long categoryId);
    ProductOrderUDTO fetchProductOrder();

    void createProductOrder(ProductOrderUDTO productOrderUDTOS);

    void createProductOrders(List<ProductOrderUDTO> productOrders);

    List<ProductOrderUDTO> fetchProductOrders(Long customerId);
}
