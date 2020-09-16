package wieczorek.jakub.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wieczorek.jakub.shop.business.spring.client.v2.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.v2.dto.CategoryUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.OrderUDTO;
import wieczorek.jakub.shop.business.spring.client.v2.dto.ProductUDTO;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController
{
    private ShopClientManager shopClientManager;

    @Autowired
    public CategoryController(ShopClientManager shopClientManager)
    {
        this.shopClientManager = shopClientManager;
    }

    @GetMapping
    public ResponseEntity<List<CategoryUDTO>> fetchCategories()
    {
        return new ResponseEntity<>(shopClientManager.fetchCategories(), HttpStatus.OK);
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductUDTO>>fetchProducts(@RequestParam Long categoryId)
    {
        return new ResponseEntity<>(shopClientManager.fetchProducts(categoryId), HttpStatus.OK);
    }

    @PostMapping(path = "/order")
    public ResponseEntity<Void> createOrder(OrderUDTO orderDTO)
    {
//        shopClientManager.createNewOrder(orderDTO);

        // 1) order contains customer then       shopClientManager.createNewOrder(orderDTO);
        // 2) order and customer separately then shopClientManager.createNewOrder(orderDTO, customerDTO);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
