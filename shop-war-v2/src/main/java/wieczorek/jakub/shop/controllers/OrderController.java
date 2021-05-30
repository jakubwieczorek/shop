package wieczorek.jakub.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wieczorek.jakub.shop.business.spring.client.v2.boundry.ShopClientManager;
import wieczorek.jakub.shop.business.spring.client.v2.dto.ProductOrderUDTO;
import wieczorek.jakub.shop.business.spring.model.domain.v2.ProductOrderU;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class OrderController
{
    private ShopClientManager shopClientManager;

    @Autowired
    public OrderController(ShopClientManager shopClientManager)
    {
        this.shopClientManager = shopClientManager;
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<Void> addOrder(@RequestBody Map<String, List<ProductOrderUDTO>> productOrderUDTOSMap)
    {
        shopClientManager.createProductOrders(productOrderUDTOSMap.get("productOrders"));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/order")
    public ResponseEntity<ProductOrderUDTO> fetchOrder()
    {
        return new ResponseEntity<>(shopClientManager.fetchProductOrder(), HttpStatus.OK);
    }

    @GetMapping(path = "/orders")
    public ResponseEntity<Map<String, List<ProductOrderUDTO>>> fetchOrders(@RequestParam Long customerId)
    {
        Map<String, List<ProductOrderUDTO>> returnMap = new HashMap<>();
        returnMap.put("productOrders", shopClientManager.fetchProductOrders(customerId));
        return new ResponseEntity<>(returnMap, HttpStatus.OK);
    }
}
