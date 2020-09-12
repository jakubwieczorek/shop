//package wieczorek.jakub.shop.controllers;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import wieczorek.jakub.shop.business.spring.client.dto.*;
//
//import java.math.BigDecimal;
//import java.util.Date;
//
//@RestController
//public class CategoryController3
//{
//    CategoryDTO category;
//    ProductDTO product1;
//    ProductDTO product2;
//    DeliveryCompanyDTO deliveryCompany;
//    CustomerDTO customer;
//    OrderDTO order;
//    DeliveryDTO delivery;
//    ProductOrderDTO productOrder;
//    ProductOrderDTO productOrder2;
//    ProductOrderIdDTO productOrderId;
//    ProductOrderIdDTO productOrderId2;
//    PromotionDTO promotion;
//    PromotionDTO promotion2;
//    ComplaintDTO complaint;
//
//    public void setUp()
//    {
//         category = new CategoryDTO();
//         product1 = new ProductDTO();
//         product2 = new ProductDTO();
//         deliveryCompany = new DeliveryCompanyDTO();
//         customer = new CustomerDTO();
//         order = new OrderDTO();
//         delivery = new DeliveryDTO();
//         productOrder = new ProductOrderDTO();
//         productOrder2 = new ProductOrderDTO();
//         productOrderId = new ProductOrderIdDTO();
//         productOrderId2 = new ProductOrderIdDTO();
//         promotion = new PromotionDTO();
//         promotion2 = new PromotionDTO();
//         complaint = new ComplaintDTO();
//
//        // new category
//        category.setCategoryName("category_name");
//
//        // new product
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
//        // new product
//        product2.setProductName("product2");
//        product2.setProductPrice(BigDecimal.ONE);
//        product2.setAmountInStock(2L);
//        product2.setDescription("description");
//        product2.setSoldAmount(4L);
//        product2.setWeight(BigDecimal.TEN);
//        product2.setVendor("vendor");
//        product2.setProductionYear(2020L);
//        product2.setSize("Size");
//
//        // add product to category
//        category.addProduct(product1);
//        category.addProduct(product2);
//
//        // new deliveryCompany
//        deliveryCompany.setDeliveryCompanyName("aa");
//
//        // new customer
//        customer.setCity("CITY");
//        customer.setEmail("MAIL");
//        customer.setFirstName("FIRSTNAME");
//        customer.setFlatNumber(2L);
//        customer.setHouseNumber(2L);
//        customer.setPassword("PASSWORD");
//        customer.setPostalCode("POSTAL");
//        customer.setPhoneNumber("PHONENUMBER");
//        customer.setSurname("SURNAME");
//        customer.setStreet("STREET");
//
//        // new order
//        order.setCostOfProducts(BigDecimal.valueOf(4));
//        order.setCostOfDelivery(BigDecimal.valueOf(3));
//        order.setFinalCost(BigDecimal.valueOf(7));
//        customer.addOrder(order);
//
//        // new delivery
//        delivery.setDeliveryTime(new Date());
//        delivery.setPrice(BigDecimal.valueOf(5));
//        delivery.addOrder(order);
//        deliveryCompany.addDelivery(delivery);
//
//        // add products to order
//        productOrder.setAmountOfOrderedProducts(5L);
//        productOrder2.setAmountOfOrderedProducts(2L);
//        productOrder.addProductOrder(order, product1);
//        productOrder2.addProductOrder(order, product2);
//
//
//        productOrder.setProductOrderId(productOrderId);
//
//
//        productOrder2.setProductOrderId(productOrderId2);
//
//        // new promotion
//        promotion.setDeadline(new Date());
//        promotion.setDescription("description");
//        promotion.setPercentage(BigDecimal.valueOf(30));
//
//        // new promotion
//        promotion2.setDeadline(new Date());
//        promotion2.setDescription("description");
//        promotion2.setPercentage(BigDecimal.valueOf(30));
//
//        promotion.addProduct(product1);
//        promotion2.addCategory(category);
//
//        // new complaint
//        complaint.setComplaintTime(new Date());
//        complaint.setContent("content");
//        complaint.addOrder(order);
//    }
//
//    @GetMapping(path = "categories")
//    public ResponseEntity<CategoryDTO> newCategory()
//    {
//        setUp();
//        // complaint.setComplaintId(1L);
//        // promotion2.setPromotionId(2L);
//        // promotion.setPromotionId(1L);
//        // productOrderId2.setOrderId(1L);
//        // productOrderId2.setProductId(2L);
//        // productOrderId.setOrderId(1L);
//        // productOrderId.setProductId(1L);
//        // delivery.setDeliveryId(1L);
//        // order.setOrderId(1L);
//        // customer.setCustomerId(1L);
//        // deliveryCompany.setDeliveryCompanyId(1L);
//        // product2.setProductId(2L);
//        // product1.setProductId(1L);
//        // category.setCategoryId(1L);
//
//        product1.setProductOrders(null);
//        product2.setProductOrders(null);
//        product1.setPromotion(null);
//        product2.setPromotion(null);
//        return new ResponseEntity<>(category, HttpStatus.OK);
//    }
//
//    @GetMapping(path = "categories2")
//    public ResponseEntity<CategoryDTO> updateCategory()
//    {
//        setUp();
//
//        complaint.setComplaintId(1L);
//        promotion2.setPromotionId(2L);
//        promotion.setPromotionId(1L);
//        productOrderId2.setOrderId(1L);
//        productOrderId2.setProductId(2L);
//        productOrderId.setOrderId(1L);
//        productOrderId.setProductId(1L);
//        delivery.setDeliveryId(1L);
//        order.setOrderId(1L);
//        customer.setCustomerId(1L);
//        deliveryCompany.setDeliveryCompanyId(1L);
//        product2.setProductId(2L);
//        product1.setProductId(1L);
//        category.setCategoryId(1L);
//
//        return new ResponseEntity<>(category, HttpStatus.OK);
//    }
//
//
//    @GetMapping(path = "order")
//    public ResponseEntity<OrderDTO> addOrder()
//    {
//        setUp();
//
//       // complaint.setComplaintId(1L);
//        promotion2.setPromotionId(2L);
//        promotion.setPromotionId(1L);
////        productOrderId2.setOrderId(1L);
////        productOrderId2.setProductId(2L);
////        productOrderId.setOrderId(1L);
////        productOrderId.setProductId(1L);
////        delivery.setDeliveryId(1L);
//        order.setOrderId(1L);
//        customer.setCustomerId(1L);
//        deliveryCompany.setDeliveryCompanyId(1L);
//        product2.setProductId(2L);
//        product1.setProductId(1L);
//        category.setCategoryId(1L);
//
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }
//
//
//    @GetMapping(path = "/order2")
//    public ResponseEntity<OrderDTO> updateOrder()
//    {
//        setUp();
//
//        complaint.setComplaintId(1L);
//        promotion2.setPromotionId(2L);
//        promotion.setPromotionId(1L);
//        productOrderId2.setOrderId(1L);
//        productOrderId2.setProductId(2L);
//        productOrderId.setOrderId(1L);
//        productOrderId.setProductId(1L);
//        delivery.setDeliveryId(1L);
//        order.setOrderId(1L);
//        customer.setCustomerId(1L);
//        deliveryCompany.setDeliveryCompanyId(1L);
//        product2.setProductId(2L);
//        product1.setProductId(1L);
//        category.setCategoryId(1L);
//
//        return new ResponseEntity<>(order, HttpStatus.OK);
//    }
//}
