package wieczorek.jakub.shop.business.spring.beans.ds;

import org.junit.Before;
import org.junit.Ignore;
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
import wieczorek.jakub.shop.business.spring.model.domain.v2.*;

import java.math.BigDecimal;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BusinessConfig.class})
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Sql("classpath:init.sql")
@Sql(scripts = "classpath:clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Ignore
public class ShopRepositoryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ShopDAO shopDAO;

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
    }

    @Test
    public void given_Order_when_removeOrder_then_removed()
    {
        // when
        shopDAO.removeOrder(order);
        entityManager.flush();
    }

    @Test
    public void given_Complaint_when_remove_then_removed()
    {
        // Given
        Long complaintId = complaint.getComplaintId();

        // When

        // then
    }
}
