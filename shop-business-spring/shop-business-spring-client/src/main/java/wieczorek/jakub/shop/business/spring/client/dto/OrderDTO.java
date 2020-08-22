package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderDTO
{
    private Long orderId;

    private BigDecimal costOfProducts;

    private BigDecimal costOfDelivery;

    private BigDecimal finalCost;

    private DeliveryDTO delivery;

    private CustomerDTO customer;

    private Set<ProductOrderDTO> productOrders = new HashSet<>();

    private Set<ComplaintDTO> complaints = new HashSet<>();
}
