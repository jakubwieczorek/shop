package wieczorek.jakub.shop.business.spring.client.v1.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference
    private DeliveryDTO delivery;

    @JsonBackReference
    private CustomerDTO customer;

    @JsonManagedReference
    private Set<ProductOrderDTO> productOrders = new HashSet<>();

    @JsonManagedReference
    private Set<ComplaintDTO> complaints = new HashSet<>();
}
