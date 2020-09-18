package wieczorek.jakub.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wieczorek.jakub.shop.business.spring.client.v2.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.v2.dto.ProductOrderUDTO;

@RestController
public class OrderController
{
    private ShopClientManager shopClientManager;

    @Autowired
    public OrderController(ShopClientManager shopClientManager)
    {
        this.shopClientManager = shopClientManager;
    }

    @PostMapping(path = "/order")
    public ResponseEntity<Void> addOrder(@RequestBody ProductOrderUDTO productOrderUDTOS)
    {
        shopClientManager.createProductOrder(productOrderUDTOS);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/order")
    public ResponseEntity<ProductOrderUDTO> fetchOrder()
    {
        return new ResponseEntity<ProductOrderUDTO>(shopClientManager.fetchProductOrder(), HttpStatus.OK);
    }
}
