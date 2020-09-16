package wieczorek.jakub.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import wieczorek.jakub.shop.business.spring.client.v1.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.v1.dto.CategoryDTO;
import wieczorek.jakub.shop.business.spring.client.v1.dto.OrderDTO;

import java.util.List;

@RestController
public class CategoryController
{
    private ShopClientManager shopClientManager;

    @Autowired
    public CategoryController(ShopClientManager shopClientManager)
    {
        this.shopClientManager = shopClientManager;
    }

    @GetMapping(path = "categories")
    public ResponseEntity<List<CategoryDTO>> fetchCategories()
    {
        return new ResponseEntity<>(shopClientManager.fetchCategories(), HttpStatus.OK);
    }

    @PostMapping(path = "order")
    public ResponseEntity<Void> createOrder(OrderDTO orderDTO)
    {
//        shopClientManager.createNewOrder(orderDTO);

        // 1) order contains customer then       shopClientManager.createNewOrder(orderDTO);
        // 2) order and customer separately then shopClientManager.createNewOrder(orderDTO, customerDTO);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
