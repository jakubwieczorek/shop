package wieczorek.jakub.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wieczorek.jakub.shop.business.spring.client.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.dto.CategoryDTO;

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
}
