package wieczorek.jakub.shop.business.spring.beans.boundry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wieczorek.jakub.shop.business.spring.beans.ds.CustomerRepository;
import wieczorek.jakub.shop.business.spring.beans.ds.ShopDAO;
import wieczorek.jakub.shop.business.spring.beans.service.Converter;
import wieczorek.jakub.shop.business.spring.client.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.dto.*;
import wieczorek.jakub.shop.business.spring.model.domain.v1.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopClientManagerBean implements ShopClientManager
{
    private CustomerRepository customerRepository;

    private Converter converter;

    private ShopDAO shopDAO;

    @Override
    public List<CategoryDTO> fetchCategories()
    {
        List<Category> categories = shopDAO.fetchCategories();
        List<CategoryDTO> categoriesDTO = new ArrayList<>();
        categories.forEach(category -> categoriesDTO.add(converter.convert(category, CategoryDTO.class)));
        return categoriesDTO;
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
    public void createNewCustomer(CustomerDTO customerDTO)
    {
        Customer customer = converter.convert(customerDTO, Customer.class);

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

    public void addOrderToCustomer(CustomerDTO customerDTO, OrderDTO orderDTO)
    {
        Customer customer = converter.convert(customerDTO, Customer.class);
        Order order = converter.convert(orderDTO, Order.class);

        //shopDAO.persistCategoryAndProducts();
        shopDAO.addOrder(customer, order, order.getProductOrders());
        shopDAO.addDelivery(order.getDelivery().getDeliveryCompany(), order.getDelivery());
    }

    public void addDelivery(DeliveryCompanyDTO deliveryCompanyDTO, DeliveryDTO deliveryDTO)
    {
        Delivery delivery = converter.convert(deliveryDTO, Delivery.class);
        DeliveryCompany deliveryCompany = converter.convert(deliveryCompanyDTO, DeliveryCompany.class);

        shopDAO.addDelivery(deliveryCompany, delivery);
    }
}
