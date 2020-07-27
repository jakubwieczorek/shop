package wieczorek.jakub.shop.business.spring.model.domain.v1;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import wieczorek.jakub.shop.business.spring.client.BusinessConfig;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;
import wieczorek.jakub.shop.business.spring.client.dto.OrderDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BusinessConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql("init.sql")
@Sql(scripts = "clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EntityScan(basePackages = "wieczorek.jakub.shop.business.spring.model.domain.v1")
public class CustomerTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void selectAllCustomers()
    {
        List<Customer> customers = entityManager.getEntityManager().createQuery("select c from Customer c", Customer.class).getResultList();
        Assert.assertNotNull(customers);
    }

    @Test
    public void insertNewCustomer()
    {
        // Given
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

        // When
        entityManager.persist(customer);
        entityManager.flush();

        // Then
        Customer expectedCustomer = entityManager.getEntityManager().createQuery("select c from Customer c where c.email = :email", Customer.class)
                .setParameter("email", "MAIL").getSingleResult();

        Assert.assertNotNull(expectedCustomer);
    }

    @Test
    public void selectOrdersForCustomer()
    {
        // Given
        Customer customer = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId( Customer.class).load("malgorzata1234@op.pl");

        // when
        List<Order> orders = customer.getOrders();

        //then
        Assert.assertFalse(orders.isEmpty());
    }

    @Test
    public void customerToDTO()
    {
        // Given
        Customer customer = new Customer();
        List<Order>orders = new ArrayList<>();
        Order order1 = new Order();
        Order order2 = new Order();
        order1.setCostOfDelivery(BigDecimal.valueOf(10));
        order2.setCostOfDelivery(BigDecimal.valueOf(15));
        orders.add(order1);
        orders.add(order2);
        customer.setOrders(orders);

        // when
        CustomerDTO customerDTO = customer.toDTO();

        // then
        Assert.assertEquals(order1.getCostOfDelivery(), customerDTO.getOrders().get(0).getCostOfDelivery());
        Assert.assertEquals(order2.getCostOfDelivery(), customerDTO.getOrders().get(1).getCostOfDelivery());
    }

    @Test
    public void customerFromDTO()
    {
        CustomerDTO customerDTO = new CustomerDTO();
        List<OrderDTO>orders = new ArrayList<>();
        OrderDTO order1 = new OrderDTO();
        OrderDTO order2 = new OrderDTO();
        order1.setCostOfDelivery(BigDecimal.valueOf(10));
        order2.setCostOfDelivery(BigDecimal.valueOf(15));
        orders.add(order1);
        orders.add(order2);
        customerDTO.setOrders(orders);

        // when
        Customer customer = new Customer(customerDTO);

        // then
        Assert.assertEquals(order1.getCostOfDelivery(), customer.getOrders().get(0).getCostOfDelivery());
        Assert.assertEquals(order2.getCostOfDelivery(), customer.getOrders().get(1).getCostOfDelivery());
    }
}