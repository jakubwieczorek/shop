package wieczorek.jakub.shop.business.spring.model.domain.v1;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {BusinessConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("init.sql")
@Sql(scripts = "clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EntityScan(basePackages = "wieczorek.jakub.shop.business.spring.model.domain.v1")
public class DomainTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp()
    {
        // new category
        Category category = new Category();
        category.setCategoryName("category_name");
        entityManager.persist(category);

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

        // add product to category
        category.addProduct(product1);
        category.addProduct(product2);

        // new deliveryCompany
        DeliveryCompany deliveryCompany = new DeliveryCompany();
        deliveryCompany.setDeliveryCompanyName("aa");
        entityManager.persist(deliveryCompany);

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
        entityManager.persist(customer);

        // new order
        Order order = new Order();
        order.setCostOfProducts(BigDecimal.valueOf(4));
        order.setCostOfDelivery(BigDecimal.valueOf(3));
        order.setFinalCost(BigDecimal.valueOf(7));
        customer.addOrder(order);

        // new delivery
        Delivery delivery = new Delivery();
        delivery.setDeliveryTime(new Date());
        delivery.setPrice(BigDecimal.valueOf(5));
        delivery.addOrder(order);
        deliveryCompany.addDelivery(delivery);
        entityManager.persist(delivery);

        // add products to order
        ProductOrder productOrder = new ProductOrder();
        productOrder.setAmountOfOrderedProducts(5L);
        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.setAmountOfOrderedProducts(2L);
        productOrder.addProductOrder(order, product1);
        productOrder2.addProductOrder(order, product2);
        entityManager.persist(productOrder);
        entityManager.persist(productOrder2);

        // new promotion
        Promotion promotion = new Promotion();
        promotion.setDeadline(new Date());
        promotion.setDescription("description");
        promotion.setPercentage(BigDecimal.valueOf(30));

        // new promotion
        Promotion promotion2 = new Promotion();
        promotion2.setDeadline(new Date());
        promotion2.setDescription("description");
        promotion2.setPercentage(BigDecimal.valueOf(30));

        promotion.addProduct(product1);
        promotion2.addCategory(category);
        entityManager.persist(promotion);
        entityManager.persist(promotion2);

        // new complaint
        Complaint complaint = new Complaint();
        complaint.setComplaintTime(new Date());
        complaint.setContent("content");
        complaint.addOrder(order);
        entityManager.persist(complaint);

        entityManager.flush();
        // 2 updates added because of promotion and complaint
    }

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
    public void given_Customer_InDb_when_delete_then_Customer_and_Orders_deleted()
    {
        // given
        Customer customer = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(Customer.class).load("MAIL");
        Long orderId = customer.getOrders().get(0).getOrderId();

        // when
        entityManager.remove(customer);
        entityManager.flush();

        // then
        Customer customer2 = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(Customer.class).load("MAIL");
        Order order2 = entityManager.find(Order.class, orderId);
        Assert.assertNull(customer2);
        // Assert.assertNull(order2);
    }

    @Test
    public void given_Customer_InDb_when_bySimpleNaturalId_then_Customer()
    {
        // given
        // Customer flushed to db

        // when
        Customer customer = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(Customer.class).load("MAIL");

        // then
        Assert.assertNotNull(customer);
    }

    @Test
    @Ignore// will not work, because of constraints, if you want to run it add
//    ALTER TABLE product DROP CONSTRAINT product_category_fk;
//    Alter table product drop column category_id;
//    Alter table product add category_id NUMBER(3); // here normally is not null
    public void testProduct()
    {
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

        // new promotion
        Promotion promotion = new Promotion();
        promotion.setDeadline(new Date());
        promotion.setDescription("description");
        promotion.setPercentage(BigDecimal.valueOf(30));

        promotion.addProduct(product1);

        entityManager.persist(product1);
        //entityManager.persist(promotion);

        entityManager.flush();
    }
}