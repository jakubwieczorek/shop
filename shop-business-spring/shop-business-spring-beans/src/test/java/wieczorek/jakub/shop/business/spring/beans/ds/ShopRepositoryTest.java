package wieczorek.jakub.shop.business.spring.beans.ds;

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
import wieczorek.jakub.shop.business.spring.model.domain.v1.*;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BusinessConfig.class})
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Sql("classpath:init.sql")
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ShopRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShopDAO shopDAO;

    private Category category;
    private Product product1;
    private Product product2;
    private DeliveryCompany deliveryCompany;
    private Customer customer;
    private Delivery delivery;
    private Order order;
    private ProductOrder productOrder;
    private ProductOrder productOrder2;
    private Promotion promotion;
    private Promotion promotion2;
    private Complaint complaint;

    @Before
    public void setUp()
    {
        // new category
        category = new Category();
        category.setCategoryName("category_name");
        entityManager.persist(category);

        // new product
        product1 = new Product();
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
        product2 = new Product();
        product2.setProductName("product2");
        product2.setProductPrice(BigDecimal.ONE);
        product2.setAmountInStock(2L);
        product2.setDescription("description");
        product2.setSoldAmount(4L);
        product2.setWeight(BigDecimal.TEN);
        product2.setVendor("vendor");
        product2.setProductionYear(2020L);
        product2.setSize("Size");

        // add product to category
        category.addProduct(product1);
        category.addProduct(product2);

        // new deliveryCompany
        deliveryCompany = new DeliveryCompany();
        deliveryCompany.setDeliveryCompanyName("aa");
        entityManager.persist(deliveryCompany);

        // new customer
        customer = new Customer();
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
        entityManager.persist(customer);

        // new order
        order = new Order();
        order.setCostOfProducts(BigDecimal.valueOf(4));
        order.setCostOfDelivery(BigDecimal.valueOf(3));
        order.setFinalCost(BigDecimal.valueOf(7));
        customer.addOrder(order);

        // new delivery
        delivery = new Delivery();
        delivery.setDeliveryTime(new Date());
        delivery.setPrice(BigDecimal.valueOf(5));
        delivery.addOrder(order);
        deliveryCompany.addDelivery(delivery);
        entityManager.persist(delivery);

        // add products to order
        productOrder = new ProductOrder();
        productOrder.setAmountOfOrderedProducts(5L);
        productOrder2 = new ProductOrder();
        productOrder2.setAmountOfOrderedProducts(2L);
        productOrder.addProductOrder(order, product1);
        productOrder2.addProductOrder(order, product2);
        entityManager.persist(productOrder);
        entityManager.persist(productOrder2);

        // new promotion
        promotion = new Promotion();
        promotion.setDeadline(new Date());
        promotion.setDescription("description");
        promotion.setPercentage(BigDecimal.valueOf(30));

        // new promotion
        promotion2 = new Promotion();
        promotion2.setDeadline(new Date());
        promotion2.setDescription("description");
        promotion2.setPercentage(BigDecimal.valueOf(30));

        promotion.addProduct(product1);
        promotion2.addCategory(category);
        entityManager.persist(promotion);
        entityManager.persist(promotion2);

        // new complaint
        complaint = new Complaint();
        complaint.setComplaintTime(new Date());
        complaint.setContent("content");
        complaint.addOrder(order);
        entityManager.persist(complaint);

        entityManager.flush();
    }

    @Test
    public void given_Order_when_removeOrder_then_removed()
    {
        // when
        shopDAO.removeOrder(customer, order);
        entityManager.flush();

        // then
        Assert.assertFalse(customer.getOrders().contains(order));
        Assert.assertFalse(delivery.getOrders().contains(order));
        order.getComplaints().forEach(complaint1 ->
            Assert.assertFalse(complaint1.getOrders().contains(order)));
        Assert.assertFalse(deliveryCompany.getDeliveries().contains(delivery));

        // maybe synchronize also ProductOrder

        Assert.assertNull(entityManager.find(Order.class, order.getOrderId()));
        Assert.assertNull(entityManager.find(Complaint.class, complaint.getComplaintId()));
        Assert.assertNull(entityManager.find(Delivery.class, delivery.getDeliveryId()));
        Assert.assertNull(entityManager.find(ProductOrder.class, productOrder.getProductOrderId()));
        Assert.assertNull(entityManager.find(ProductOrder.class, productOrder2.getProductOrderId()));
    }

    @Test
    public void given_Complaint_when_remove_then_removed()
    {
        // Given
        Long complaintId = complaint.getComplaintId();

        // When
        shopDAO.removeComplaint(complaint);
        entityManager.flush();

        // then
        complaint.getOrders().forEach(order1 -> Assert.assertFalse(order1.getComplaints().contains(complaint)));
        Assert.assertNull(entityManager.find(Complaint.class, complaintId));
    }
}
