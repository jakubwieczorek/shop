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
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Customer;

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
}