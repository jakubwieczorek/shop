package wieczorek.jakub.shop.business.spring.beans.boundry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import wieczorek.jakub.shop.business.spring.beans.ds.CustomerRepository;
import wieczorek.jakub.shop.business.spring.beans.ds.ShopDAO;
import wieczorek.jakub.shop.business.spring.client.BusinessConfig;
import wieczorek.jakub.shop.business.spring.client.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.dto.*;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Customer;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BusinessConfig.class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@Sql("classpath:init.sql")
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ShopClientManagerBeanTest
{
    @Autowired
    private ShopClientManager shopClientManager;

    @Autowired
    private ShopDAO shopDAO;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void given_no_existed_customer_when_createNewUser_then_customerCreated()
    {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCity("CITY");
        customerDTO.setEmail("MAIL");
        customerDTO.setFirstName("FIRSTNAME");
        customerDTO.setFlatNumber(2L);
        customerDTO.setHouseNumber(2L);
        customerDTO.setPassword("PASSWORD");
        customerDTO.setPostalCode("POSTAL");
        customerDTO.setPhoneNumber("PHONENUMBER");
        customerDTO.setSurname("SURNAME");
        customerDTO.setStreet("STREET");

        // When
        shopClientManager.createNewCustomer(customerDTO);

        // Then
        Assert.assertNotNull(shopDAO.findCustomerByEmail("MAIL"));
    }

    @Test
    public void given_existed_customer_when_createNewCustomer_then_exception()
    {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCity("CITY");
        customerDTO.setEmail("MAIL");
        customerDTO.setFirstName("FIRSTNAME");
        customerDTO.setFlatNumber(2L);
        customerDTO.setHouseNumber(2L);
        customerDTO.setPassword("PASSWORD");
        customerDTO.setPostalCode("POSTAL");
        customerDTO.setPhoneNumber("PHONENUMBER");
        customerDTO.setSurname("SURNAME");
        customerDTO.setStreet("STREET");

        Customer customer = new Customer();
        customer.setCity("CITY");
        customer.setEmail("MAIL");
        customer.setFirstName("FIRSTNAME");
        customer.setFlatNumber(2L);
        customer.setHouseNumber(2L);
        customer.setPassword("PASSWORD");
        customer.setPostalCode("POSTAL");
        customer.setPhoneNumber("PHONENUMBER");
        customer.setSurname("SURNAME");
        customer.setStreet("STREET");

        customerRepository.save(customer);

        // When, Then
        Assert.assertThrows(RuntimeException.class, () -> shopClientManager.createNewCustomer(customerDTO));
    }

    @Test
    public void given_Customer_when_addOrder_then_order_added_to_customer()
    {
        // new category
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName("category_name");

        // new product
        ProductDTO product1DTO = new ProductDTO();
        product1DTO.setProductName("product1");
        product1DTO.setProductPrice(BigDecimal.ONE);
        product1DTO.setAmountInStock(2L);
        product1DTO.setDescription("description");
        product1DTO.setSoldAmount(4L);
        product1DTO.setWeight(BigDecimal.TEN);
        product1DTO.setVendor("vendor");
        product1DTO.setProductionYear(2020L);
        product1DTO.setSize("Size");

        // new product
        ProductDTO product2DTO = new ProductDTO();
        product2DTO.setProductName("product2");
        product2DTO.setProductPrice(BigDecimal.ONE);
        product2DTO.setAmountInStock(2L);
        product2DTO.setDescription("description");
        product2DTO.setSoldAmount(4L);
        product2DTO.setWeight(BigDecimal.TEN);
        product2DTO.setVendor("vendor");
        product2DTO.setProductionYear(2020L);
        product2DTO.setSize("Size");

        // add product to category
        categoryDTO.addProduct(product1DTO);
        categoryDTO.addProduct(product2DTO);

        // new deliveryCompany
        DeliveryCompanyDTO deliveryCompanyDTO = new DeliveryCompanyDTO();
        deliveryCompanyDTO.setDeliveryCompanyName("aa");

        // new Customer
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCity("CITY");
        customerDTO.setEmail("MAIL");
        customerDTO.setFirstName("FIRSTNAME");
        customerDTO.setFlatNumber(2L);
        customerDTO.setHouseNumber(2L);
        customerDTO.setPassword("PASSWORD");
        customerDTO.setPostalCode("POSTAL");
        customerDTO.setPhoneNumber("PHONENUMBER");
        customerDTO.setSurname("SURNAME");
        customerDTO.setStreet("STREET");

        // new order
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCostOfProducts(BigDecimal.valueOf(4));
        orderDTO.setCostOfDelivery(BigDecimal.valueOf(3));
        orderDTO.setFinalCost(BigDecimal.valueOf(7));
        customerDTO.addOrder(orderDTO);

        // new delivery
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setDeliveryTime(new Date());
        deliveryDTO.setPrice(BigDecimal.valueOf(5));
        deliveryDTO.addOrder(orderDTO);
        deliveryCompanyDTO.addDelivery(deliveryDTO);

        // add products to order
        ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setAmountOfOrderedProducts(5L);
        ProductOrderDTO productOrder2DTO = new ProductOrderDTO();
        productOrder2DTO.setAmountOfOrderedProducts(2L);
        productOrderDTO.addProductOrder(orderDTO, product1DTO);
        productOrder2DTO.addProductOrder(orderDTO, product2DTO);

        // When
        //shopClientManager.addOrderToCustomer(customerDTO, orderDTO);
    }
}