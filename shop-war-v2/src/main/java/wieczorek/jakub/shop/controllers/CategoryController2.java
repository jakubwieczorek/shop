//package wieczorek.jakub.shop.controllers;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import wieczorek.jakub.shop.business.spring.client.v1.dto.Category2DTO;
//import wieczorek.jakub.shop.business.spring.client.v1.dto.CategoryDTO;
//import wieczorek.jakub.shop.business.spring.client.v1.dto.Product2DTO;
//import wieczorek.jakub.shop.business.spring.client.v1.dto.ProductDTO;
//
//import java.math.BigDecimal;
//
//@RestController
//public class CategoryController2
//{
//    @GetMapping(path = "/caaategories")
//    public ResponseEntity<Category2DTO> fetchCategories3()
//    {
//        Category2DTO category = new Category2DTO();
//        category.setCategoryId(1L);
//        category.setCategoryName("category_name");
//
//        // new product
//        Product2DTO product1 = new Product2DTO();
//        product1.setProductId(1L); // check why when id is null, then infinite recursion
//        product1.setProductName("product1");
//
//        // add product to category
//        category.addProduct(product1);
//
////        categories.add(category);
//        return new ResponseEntity<>(category, HttpStatus.OK);
//    }
//
//    @GetMapping(path = "/caaaategories2")
//    public ResponseEntity<Category2DTO> fetchCategories2()
//    {
//        Category2DTO category = new Category2DTO();
//        category.setCategoryName("category_name");
//
//        // new product
//        Product2DTO product1 = new Product2DTO();
//        product1.setProductName("product1");
//
//        // add product to category
//        category.addProduct(product1);
//
////        categories.add(category);
//        return new ResponseEntity<>(category, HttpStatus.OK);
//    }
//}
