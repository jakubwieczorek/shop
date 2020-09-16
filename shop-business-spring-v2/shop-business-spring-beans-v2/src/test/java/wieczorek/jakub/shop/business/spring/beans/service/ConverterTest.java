package wieczorek.jakub.shop.business.spring.beans.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import wieczorek.jakub.shop.business.spring.client.v2.dto.*;
import wieczorek.jakub.shop.business.spring.model.domain.v2.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;

public class ConverterTest
{
    private Converter converter;

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
    public void setUp() {
        converter = new Converter(new ModelMapper());

        // new category
        category = new CategoryU();
        category.setCategoryName("category_name");

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

        // new deliveryCompany
        deliveryCompany = new DeliveryCompanyU();
        deliveryCompany.setDeliveryCompanyName("aa");

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

        // new delivery
        delivery = new DeliveryU();
        delivery.setDeliveryTime(new Date());
        delivery.setPrice(BigDecimal.valueOf(5));
        delivery.setDeliveryCompany(deliveryCompany);

        // new order
        order = new OrderU();
        order.setCostOfProducts(BigDecimal.valueOf(4));
        order.setCostOfDelivery(BigDecimal.valueOf(3));
        order.setFinalCost(BigDecimal.valueOf(7));
        order.setCustomer(customer);
        order.setDelivery(delivery);

        // add products to order
        productOrder = new ProductOrderU();
        productOrder.setAmountOfOrderedProducts(5L);
        productOrder.setOrder(order);
        productOrder.setProduct(product1);

        productOrder2 = new ProductOrderU();
        productOrder2.setAmountOfOrderedProducts(2L);
        productOrder2.setOrder(order);
        productOrder2.setProduct(product2);

        // new complaint
        complaint = new ComplaintU();
        complaint.setComplaintTime(new Date());
        complaint.setContent("content");
        order.getComplaints().add(complaint);
    }

    @Test
    public void given_Customer_when_covert_thenCustomerDTO()
    {
        // when
        CustomerUDTO customerDTO = converter.convert(customer, CustomerUDTO.class);
        OrderUDTO orderDTO = converter.convert(order, OrderUDTO.class);
        ProductOrderUDTO productOrderDTO = converter.convert(productOrder, ProductOrderUDTO.class);
        ProductUDTO productDTO = converter.convert(product1, ProductUDTO.class);

        DeliveryUDTO deliveryDTO = orderDTO.getDelivery();
        Iterator<ComplaintUDTO> iterator = orderDTO.getComplaints().iterator();
        ComplaintUDTO complaintDTO = iterator.next();
        DeliveryCompanyUDTO deliveryCompanyDTO = deliveryDTO.getDeliveryCompany();
        CategoryUDTO categoryDTO = productDTO.getCategory();

        // then
        Assert.assertEquals(customerDTO.getCity(), customer.getCity());
        Assert.assertEquals(customerDTO.getEmail(), customer.getEmail());
        Assert.assertEquals(customerDTO.getFirstName(), customer.getFirstName());
        Assert.assertEquals(customerDTO.getFlatNumber(), customer.getFlatNumber());
        Assert.assertEquals(customerDTO.getHouseNumber(), customer.getHouseNumber());
        Assert.assertEquals(customerDTO.getPassword(), customer.getPassword());
        Assert.assertEquals(customerDTO.getPostalCode(), customer.getPostalCode());
        Assert.assertEquals(customerDTO.getPhoneNumber(), customer.getPhoneNumber());
        Assert.assertEquals(customerDTO.getSurname(), customer.getSurname());

        Assert.assertEquals(customerDTO.getStreet(), customer.getStreet());
        Assert.assertEquals(orderDTO.getFinalCost(), order.getFinalCost());
        Assert.assertEquals(orderDTO.getCostOfProducts(), order.getCostOfProducts());
        Assert.assertEquals(orderDTO.getCostOfDelivery(), order.getCostOfDelivery());

        Assert.assertEquals(deliveryDTO.getPrice(), delivery.getPrice());
        Assert.assertEquals(deliveryDTO.getDeliveryTime(), delivery.getDeliveryTime());

        Assert.assertEquals(deliveryCompanyDTO.getDeliveryCompanyName(), deliveryCompany.getDeliveryCompanyName());

        Assert.assertEquals(productOrderDTO.getAmountOfOrderedProducts(), productOrder.getAmountOfOrderedProducts());

        Assert.assertEquals(productDTO.getAmountInStock(), product1.getAmountInStock());
        Assert.assertEquals(productDTO.getProductionYear(), product1.getProductionYear());
        Assert.assertEquals(productDTO.getSoldAmount(), product1.getSoldAmount());
        Assert.assertEquals(productDTO.getSize(), product1.getSize());
        Assert.assertEquals(productDTO.getDescription(), product1.getDescription());
        Assert.assertEquals(productDTO.getProductName(), product1.getProductName());
        Assert.assertEquals(productDTO.getProductPrice(), product1.getProductPrice());
        Assert.assertEquals(productDTO.getVendor(), product1.getVendor());
        Assert.assertEquals(productDTO.getWeight(), product1.getWeight());

        Assert.assertEquals(categoryDTO.getCategoryName(), category.getCategoryName());

        Assert.assertEquals(complaintDTO.getComplaintTime(), complaint.getComplaintTime());
        Assert.assertEquals(complaintDTO.getContent(), complaint.getContent());
        Assert.assertEquals(complaintDTO.getHandlingTime(), complaint.getHandlingTime());
    }
}