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
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;
import wieczorek.jakub.shop.business.spring.client.dto.OrderDTO;

import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {BusinessConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("init.sql")
@Sql(scripts = "clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DomainTest
{
    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void selectProducts()
    {
        List<Product>products = entityManager.getEntityManager().createQuery("select p from Product p", Product.class).getResultList();
        Assert.assertNotNull(products);
        Assert.assertFalse(products.isEmpty());
    }

    @Test
    public void selectOrder()
    {
        Order order = entityManager.getEntityManager().createQuery("select p from Order p where p.orderId = 1", Order.class).getSingleResult();
        Assert.assertEquals(2, order.getProductOrders().size());
    }

    @Test
    public void newClientNewOrderPersist()
    {
        // new customer
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

        // new order
        Order order = new Order();
        order.setCostOfProducts(BigDecimal.valueOf(4));
        order.setCostOfDelivery(BigDecimal.valueOf(3));
        order.setFinalCost(BigDecimal.valueOf(7));

        // new delivery
        Delivery delivery = new Delivery();
        delivery.setDeliveryTime(new Date());
        delivery.setPrice(BigDecimal.valueOf(5));
        delivery.setOrders(new ArrayList<>());
        delivery.getOrders().add(order);

        // new deliveryCompany
        DeliveryCompany deliveryCompany = new DeliveryCompany();
        deliveryCompany.setDeliveryCompanyName("aa");

        // new product
        Product product1 = new Product();
        product1.setProductName("product1");
        product1.setProductPrice(BigDecimal.ONE);
        product1.setAmountInStock(2L);
        product1.setDescription("description");
        product1.setSoldAmount(4L);
        product1.setWeight(BigDecimal.TEN);
        product1.setVendor("vendor");
        product1.setProductionYear(2020L);
        product1.setSize("Size");

        // new product
        Product product2 = new Product();
        product2.setProductName("product2");
        product2.setProductPrice(BigDecimal.ONE);
        product2.setAmountInStock(2L);
        product2.setDescription("description");
        product2.setSoldAmount(4L);
        product2.setWeight(BigDecimal.TEN);
        product2.setVendor("vendor");
        product2.setProductionYear(2020L);
        product2.setSize("Size");

        // new productOrder
        ProductOrder productOrder = new ProductOrder();
        productOrder.setAmountOfOrderedProducts(5L);

        // new productOrder
        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.setAmountOfOrderedProducts(2L);

        // new category
        Category category = new Category();
        category.setCategoryName("category_name");

        // relationships
        customer.addOrder(order);
        delivery.addOrder(order);
        deliveryCompany.addDelivery(delivery);
        productOrder.addProductOrder(order, product1);
        productOrder2.addProductOrder(order, product2);
        category.addProduct(product1);
        category.addProduct(product2);

//        //entityManager.persist(deliveryCompany);
//        //entityManager.persist(delivery);
//        entityManager.persist(customer);
//        //entityManager.persist(order);
//        entityManager.persist(category);
//        //entityManager.persist(product1);
//        //entityManager.persist(product2);
//        entityManager.persist(productOrder);
//        entityManager.persist(productOrder2);

        entityManager.persist(deliveryCompany);
        //entityManager.persist(delivery);
        entityManager.persist(customer);
        //entityManager.persist(order);
        entityManager.persist(category);
        //entityManager.persist(product1);
        //entityManager.persist(product2);
        entityManager.persist(productOrder);
        entityManager.persist(productOrder2);

        entityManager.flush();
    }
}