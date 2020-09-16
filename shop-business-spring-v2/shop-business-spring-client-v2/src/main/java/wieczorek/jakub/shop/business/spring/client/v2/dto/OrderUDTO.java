package wieczorek.jakub.shop.business.spring.client.v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderUDTO
{
    private Long orderId;

    private BigDecimal costOfProducts;

    private BigDecimal costOfDelivery;

    private BigDecimal finalCost;

    private DeliveryUDTO delivery;

    private CustomerUDTO customer;

    private Set<ComplaintUDTO> complaints = new HashSet<>();
}
