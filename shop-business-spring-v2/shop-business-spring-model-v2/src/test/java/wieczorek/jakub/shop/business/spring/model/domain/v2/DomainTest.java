package wieczorek.jakub.shop.business.spring.model.domain.v2;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
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

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {BusinessConfig.class})
@RunWith(SpringRunner.class)
@Sql("classpath:init.sql")
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EntityScan(basePackages = {"wieczorek.jakub.shop.business.spring.model.domain.v2"})
public class DomainTest
{
    @Autowired
    private TestEntityManager entityManager;

    private CategoryU category;
    private ProductU product1;
    private ProductU product2;
    private DeliveryCompanyU deliveryCompany;
    private CustomerU customer;
    private DeliveryU delivery;
    private OrderU order;
    private ProductOrderU productOrder;
    private ProductOrderU productOrder2;
    private PromotionU promotion;
    private PromotionU promotion2;
    private ComplaintU complaint;

    @Before
    public void setUp()
    {
        // new category
        category = new CategoryU();
        category.setCategoryName("category_name");
        entityManager.persist(category);

        // new product
        product1 = new ProductU();
        product1.setProductName("product1");
        product1.setProductPrice(BigDecimal.ONE);
        product1.setAmountInStock(2L);
        product1.setDescription("description");
        product1.setSoldAmount(4L);
        product1.setWeight(BigDecimal.TEN);
        product1.setVendor("vendor");
        product1.setProductionYear(2020L);
        product1.setSize("Size");
        product1.setCategory(category);
        entityManager.persist(product1);

        // new product
        product2 = new ProductU();
        product2.setProductName("product2");
        product2.setProductPrice(BigDecimal.ONE);
        product2.setAmountInStock(2L);
        product2.setDescription("description");
        product2.setSoldAmount(4L);
        product2.setWeight(BigDecimal.TEN);
        product2.setVendor("vendor");
        product2.setProductionYear(2020L);
        product2.setSize("Size");
        product2.setCategory(category);
        entityManager.persist(product2);

        // new promotion
        promotion = new PromotionU();
        promotion.setDeadline(new Date());
        promotion.setDescription("description");
        promotion.setPercentage(BigDecimal.valueOf(30));

        // new promotion
        promotion2 = new PromotionU();
        promotion2.setDeadline(new Date());
        promotion2.setDescription("description");
        promotion2.setPercentage(BigDecimal.valueOf(30));

        product1.setPromotion(promotion);
        category.setPromotion(promotion2);
        entityManager.persist(promotion);
        entityManager.persist(promotion2);

        // new deliveryCompany
        deliveryCompany = new DeliveryCompanyU();
        deliveryCompany.setDeliveryCompanyName("aa");
        entityManager.persist(deliveryCompany);

        // new customer
        customer = new CustomerU();
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

        // new delivery
        delivery = new DeliveryU();
        delivery.setDeliveryTime(new Date());
        delivery.setPrice(BigDecimal.valueOf(5));
        delivery.setDeliveryCompany(deliveryCompany);
        entityManager.persist(delivery);

        // new order
        order = new OrderU();
        order.setCostOfProducts(BigDecimal.valueOf(4));
        order.setCostOfDelivery(BigDecimal.valueOf(3));
        order.setFinalCost(BigDecimal.valueOf(7));
        order.setCustomer(customer);
        order.setDelivery(delivery);
        entityManager.persist(order);

        // add products to order
        productOrder = new ProductOrderU();
        productOrder.setAmountOfOrderedProducts(5L);
        productOrder.setOrder(order);
        productOrder.setProduct(product1);
        entityManager.persist(productOrder);

        productOrder2 = new ProductOrderU();
        productOrder2.setAmountOfOrderedProducts(2L);
        productOrder2.setOrder(order);
        productOrder2.setProduct(product2);
        entityManager.persist(productOrder2);

        // new complaint
        complaint = new ComplaintU();
        complaint.setComplaintTime(new Date());
        complaint.setContent("content");
        order.getComplaints().add(complaint);
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
    public void test()
    {

    }

//    @Test
//    public void selectProducts()
//    {
//        List<ProductU>products = entityManager.getEntityManager().createQuery("select p from ProductU p", ProductU.class).getResultList();
//        Assert.assertNotNull(products);
//        Assert.assertFalse(products.isEmpty());
//    }
//
//    @Test
//    public void selectOrder()
//    {
//        OrderU order = entityManager.getEntityManager().createQuery("select p from OrderU p where p.orderId = 1", OrderU.class).getSingleResult();
//        Assert.assertEquals(2, order.getProductOrders().size());
//    }
//
////    @Test
////    public void given_order_in_customer_when_delete_then_Customer_and_Orders_deleted()
////    {
////        // given
////        CustomerU customer = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(CustomerU.class).load("MAIL");
////
////        Long orderId = customer.getOrders().get(0).getOrderId();
////
////        OrderU order = customer.getOrders().get(0);
////        DeliveryU delivery = order.getDelivery();
////        Set<ProductOrderU> productOrders = order.getProductOrders();
//////        productOrders.
////
////
////
////        entityManager.remove(delivery);
////        entityManager.remove(order);
////
////        entityManager.flush();
////
////        // when
//////        entityManager.remove(customer.getOrders().get(0).getDelivery());
//////        entityManager.remove(customer.getOrders().get(0).getProductOrders());
//////        entityManager.remove(customer.getOrders().get(0));
//////        entityManager.flush();
////
//////        customer.getOrders().forEach(order -> { customer.deleteOrder(order); entityManager.remove(order); });
//////        entityManager.flush();
//////
//////        entityManager.remove(customer);
//////
//////        entityManager.flush();
//////
//////        // then
//////        CustomerU customer2 = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(CustomerU.class).load("MAIL");
//////        OrderU order2 = entityManager.find(OrderU.class, orderId);
//////        Assert.assertNull(customer2);
//////        // Assert.assertNull(order2);
////    }
//
    @Test
    public void given_category_with_products_when_category_remove_then_category_and_products_removed()
    {
        // Given
        CategoryU category = new CategoryU();
        category.setCategoryName("category");
        entityManager.persist(category);

        ProductU product1 = new ProductU();
        product1.setProductName("product1");
        product1.setProductPrice(BigDecimal.ONE);
        product1.setAmountInStock(2L);
        product1.setDescription("description");
        product1.setSoldAmount(4L);
        product1.setWeight(BigDecimal.TEN);
        product1.setVendor("vendor");
        product1.setProductionYear(2020L);
        product1.setSize("Size");
        product1.setCategory(category);
        entityManager.persist(product1);

        ProductU product2 = new ProductU();
        product2.setProductName("product2");
        product2.setProductPrice(BigDecimal.ONE);
        product2.setAmountInStock(2L);
        product2.setDescription("description");
        product2.setSoldAmount(4L);
        product2.setWeight(BigDecimal.TEN);
        product2.setVendor("vendor");
        product2.setProductionYear(2020L);
        product2.setSize("Size");
        product2.setCategory(category);
        entityManager.persist(product2);

        entityManager.flush();

        Long categoryId = category.getCategoryId();
        Long product1Id = product1.getProductId();
        Long product2Id = product2.getProductId();

        // When
        entityManager.remove(product1);
        entityManager.remove(product2);
        entityManager.remove(category);

        entityManager.flush();

        // Then
        Assert.assertNull(entityManager.find(CategoryU.class, categoryId));
        Assert.assertNull(entityManager.find(ProductU.class, product1Id));
        Assert.assertNull(entityManager.find(ProductU.class, product2Id));
    }

    @Test
    public void given_Customer_when_bySimpleNaturalId_then_Customer()
    {
        // Given
        CustomerU customer = new CustomerU();
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
        CustomerU customer2 = entityManager.getEntityManager().unwrap(Session.class).bySimpleNaturalId(CustomerU.class).load("MAIL2");

        // then
        Assert.assertNotNull(customer2);
    }
//
//    @Test
//    @Ignore// will not work, because of constraints, if you want to run it add
////    ALTER TABLE product DROP CONSTRAINT product_category_fk;
////    Alter table product drop column category_id;
////    Alter table product add category_id NUMBER(3); // here normally is not null
//    public void testProduct()
//    {
//        // new product
//        ProductU product1 = new ProductU();
//        product1.setProductName("product1");
//        product1.setProductPrice(BigDecimal.ONE);
//        product1.setAmountInStock(2L);
//        product1.setDescription("description");
//        product1.setSoldAmount(4L);
//        product1.setWeight(BigDecimal.TEN);
//        product1.setVendor("vendor");
//        product1.setProductionYear(2020L);
//        product1.setSize("Size");
//
//        // new promotion
//        PromotionU promotion = new PromotionU();
//        promotion.setDeadline(new Date());
//        promotion.setDescription("description");
//        promotion.setPercentage(BigDecimal.valueOf(30));
//
//        promotion.addProduct(product1);
//
//        entityManager.persist(product1);
//        //entityManager.persist(promotion);
//
//        entityManager.flush();
//    }
}