package wieczorek.jakub.shop.business.spring.beans.boundry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wieczorek.jakub.shop.business.spring.beans.ds.CustomerRepository;
import wieczorek.jakub.shop.business.spring.beans.ds.ShopDAO;
import wieczorek.jakub.shop.business.spring.beans.service.Converter;
import wieczorek.jakub.shop.business.spring.client.v2.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.v2.dto.CategoryUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.CustomerUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.ProductUDTO;
import wieczorek.jakub.shop.business.spring.model.domain.v2.CategoryU;
import wieczorek.jakub.shop.business.spring.model.domain.v2.CustomerU;
import wieczorek.jakub.shop.business.spring.model.domain.v2.ProductU;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopClientManagerBean implements ShopClientManager
{
    private CustomerRepository customerRepository;

    private Converter converter;

    private ShopDAO shopDAO;

    @Override
    public List<CategoryUDTO> fetchCategories()
    {
        List<CategoryU> categories = shopDAO.fetchCategories();
        List<CategoryUDTO> categoriesDTO = new ArrayList<>();
        categories.forEach(category -> categoriesDTO.add(converter.convert(category, CategoryUDTO.class)));
        return categoriesDTO;
    }

    @Override
    public List<ProductUDTO> fetchProducts(Long categoryId)
    {
        List<ProductU> products = shopDAO.fetchProducts(categoryId);
        List<ProductUDTO> productUDTOS = new ArrayList<>();
        products.forEach(productU -> productUDTOS.add(converter.convert(productU, ProductUDTO.class)));
        return productUDTOS;
    }

    @Autowired
    public ShopClientManagerBean(ShopDAO shopDAO,
                             Converter converter,
                             CustomerRepository customerRepository)
    {
        this.shopDAO = shopDAO;
        this.converter = converter;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createNewCustomer(CustomerUDTO customerDTO)
    {
        CustomerU customer = converter.convert(customerDTO, CustomerU.class);

        if(shopDAO.findCustomerByEmail(customer.getEmail()) != null)
        {
            throw new RuntimeException();
        }

        customerRepository.save(customer);
    }

    @Override
    public void findCustomerByEmail(String email)
    {
        shopDAO.findCustomerByEmail(email);
    }




}
