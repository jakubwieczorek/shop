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
@Sql("classpath:init.sql")
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EntityScan(basePackages = "wieczorek.jakub.shop.business.spring.model.domain.v1")
public class DomainTest
{
    @Autowired
    private TestEntityManager entityManager;

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
        // 2 updates added because of promotion and complaint

        // remove order references
        //delivery.removeOrder(order);
        //customer.removeOrder(order);

        //entityManager.remove(complaint); // because of no cascade
        //entityManager.remove(delivery);
        //entityManager.remove(order);

        //entityManager.flush();
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

//    @Test
//    public void given_order_in_customer_when_delete_then_Customer_and_Orders_deleted()
//    {
//        // given
//        Customer customer = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(Customer.class).load("MAIL");
//
//        Long orderId = customer.getOrders().get(0).getOrderId();
//
//        Order order = customer.getOrders().get(0);
//        Delivery delivery = order.getDelivery();
//        Set<ProductOrder> productOrders = order.getProductOrders();
////        productOrders.
//
//
//
//        entityManager.remove(delivery);
//        entityManager.remove(order);
//
//        entityManager.flush();
//
//        // when
////        entityManager.remove(customer.getOrders().get(0).getDelivery());
////        entityManager.remove(customer.getOrders().get(0).getProductOrders());
////        entityManager.remove(customer.getOrders().get(0));
////        entityManager.flush();
//
////        customer.getOrders().forEach(order -> { customer.deleteOrder(order); entityManager.remove(order); });
////        entityManager.flush();
////
////        entityManager.remove(customer);
////
////        entityManager.flush();
////
////        // then
////        Customer customer2 = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(Customer.class).load("MAIL");
////        Order order2 = entityManager.find(Order.class, orderId);
////        Assert.assertNull(customer2);
////        // Assert.assertNull(order2);
//    }

    @Test
    public void given_category_with_products_when_category_remove_then_category_and_products_removed()
    {
        // Given
        Category category = new Category();
        category.setCategoryName("category");
        entityManager.persist(category);

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

        category.addProduct(product1);
        category.addProduct(product2);

        entityManager.flush();

        Long categoryId = category.getCategoryId();
        Long product1Id = product1.getProductId();
        Long product2Id = product2.getProductId();

        // When
        entityManager.remove(category);
        entityManager.flush();

        // Then
        Assert.assertNull(entityManager.find(Category.class, categoryId));
        Assert.assertNull(entityManager.find(Product.class, product1Id));
        Assert.assertNull(entityManager.find(Product.class, product2Id));
    }

    @Test
    public void given_Customer_when_bySimpleNaturalId_then_Customer()
    {
        // Given
        Customer customer = new Customer();
        customer.setCity("CITY");
        customer.setEmail("MAIL2");
        customer.setFirstName("FIRSTNAME");
        customer.setFlatNumber(2L);
        customer.setHouseNumber(2L);
        customer.setPassword("PASSWORD");
        customer.setPostalCode("POSTAL");
        customer.setPhoneNumber("PHONENUMBER");
        customer.setSurname("SURNAME");
        customer.setStreet("STREET");
        entityManager.persist(customer);
        entityManager.flush();

        // when
        Customer customer2 = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(Customer.class).load("MAIL2");

        // then
        Assert.assertNotNull(customer2);
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