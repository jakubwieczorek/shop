package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO
{
    private Long orderId;

    private Long deliveryId;

    private BigDecimal costOfProducts;

    private BigDecimal costOfDelivery;

    private BigDecimal finalCost;
}