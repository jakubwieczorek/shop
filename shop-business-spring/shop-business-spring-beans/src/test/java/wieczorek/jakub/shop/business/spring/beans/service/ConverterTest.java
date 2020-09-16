package wieczorek.jakub.shop.business.spring.beans.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import wieczorek.jakub.shop.business.spring.client.v1.dto.*;
import wieczorek.jakub.shop.business.spring.model.domain.v1.*;

import java.math.BigDecimal;
import java.util.Date;

public class ConverterTest
{
    private Converter converter;

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
    public void setUp() {
        converter = new Converter(new ModelMapper());

        // new category
        category = new Category();
        category.setCategoryName("category_name");

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
        product1.setProductId(1L);

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
        product2.setProductId(2L);

        // add product to category
        category.addProduct(product1);
        category.addProduct(product2);

        // new deliveryCompany
        deliveryCompany = new DeliveryCompany();
        deliveryCompany.setDeliveryCompanyName("aa");

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

        // new order
        order = new Order();
        order.setCostOfProducts(BigDecimal.valueOf(4));
        order.setCostOfDelivery(BigDecimal.valueOf(3));
        order.setFinalCost(BigDecimal.valueOf(7));
        order.setOrderId(1L);
        customer.addOrder(order);

        // new delivery
        delivery = new Delivery();
        delivery.setDeliveryTime(new Date());
        delivery.setPrice(BigDecimal.valueOf(5));
        delivery.addOrder(order);
        deliveryCompany.addDelivery(delivery);

        // add products to order
        productOrder = new ProductOrder();
        productOrder.setAmountOfOrderedProducts(5L);
        productOrder2 = new ProductOrder();
        productOrder2.setAmountOfOrderedProducts(2L);
        productOrder.addProductOrder(order, product1);
        productOrder2.addProductOrder(order, product2);

        ProductOrderId productOrderId = new ProductOrderId();
        productOrderId.setOrderId(1L);
        productOrderId.setProductId(1L);
        productOrder.setProductOrderId(productOrderId);

        ProductOrderId productOrderId2 = new ProductOrderId();
        productOrderId2.setOrderId(1L);
        productOrderId2.setProductId(2L);
        productOrder2.setProductOrderId(productOrderId2);

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

        // new complaint
        complaint = new Complaint();
        complaint.setComplaintTime(new Date());
        complaint.setContent("content");
        complaint.addOrder(order);
    }

    @Test
    public void given_Customer_when_covert_thenCustomerDTO()
    {
        // when
        CustomerDTO customerDTO = converter.convert(customer, CustomerDTO.class);

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

        OrderDTO orderDTO = customerDTO.getOrders().get(0);
        Assert.assertEquals(orderDTO.getFinalCost(), order.getFinalCost());
        Assert.assertEquals(orderDTO.getCostOfProducts(), order.getCostOfProducts());
        Assert.assertEquals(orderDTO.getCostOfDelivery(), order.getCostOfDelivery());

        DeliveryDTO deliveryDTO = orderDTO.getDelivery();
        Assert.assertEquals(deliveryDTO.getPrice(), delivery.getPrice());
        Assert.assertEquals(deliveryDTO.getDeliveryTime(), delivery.getDeliveryTime());

        DeliveryCompanyDTO deliveryCompanyDTO = deliveryDTO.getDeliveryCompany();
        Assert.assertEquals(deliveryCompanyDTO.getDeliveryCompanyName(), deliveryCompany.getDeliveryCompanyName());

        Assert.assertEquals(orderDTO.getProductOrders().size(), order.getProductOrders().size());
        ProductDTO productDTO1 = new ProductDTO();
        for (ProductOrderDTO productOrderDTO : orderDTO.getProductOrders()) {
            ProductOrderIdDTO productOrderIdDTO = productOrderDTO.getProductOrderId();
            if (productOrderIdDTO.getOrderId().equals(1L) && productOrderIdDTO.getProductId().equals(1L)) {
                Assert.assertEquals(productOrderDTO.getAmountOfOrderedProducts(), productOrder.getAmountOfOrderedProducts());
                productDTO1 = productOrderDTO.getProduct();
            }
        }
        Assert.assertEquals(productDTO1.getAmountInStock(), product1.getAmountInStock());
        Assert.assertEquals(productDTO1.getProductionYear(), product1.getProductionYear());
        Assert.assertEquals(productDTO1.getSoldAmount(), product1.getSoldAmount());
        Assert.assertEquals(productDTO1.getSize(), product1.getSize());
        Assert.assertEquals(productDTO1.getDescription(), product1.getDescription());
        Assert.assertEquals(productDTO1.getProductName(), product1.getProductName());
        Assert.assertEquals(productDTO1.getProductPrice(), product1.getProductPrice());
        Assert.assertEquals(productDTO1.getVendor(), product1.getVendor());
        Assert.assertEquals(productDTO1.getWeight(), product1.getWeight());

        CategoryDTO categoryDTO = productDTO1.getCategory();
        Assert.assertEquals(categoryDTO.getCategoryName(), category.getCategoryName());
    }
}