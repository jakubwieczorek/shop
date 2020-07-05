package wieczorek.jakub.shop.business.spring.model.domain;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import wieczorek.jakub.shop.business.spring.client.BusinessConfig;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {BusinessConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("init.sql")
@Sql(scripts = "clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
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
}