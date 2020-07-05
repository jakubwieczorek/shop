package wieczorek.jakub.shop.business.spring.model.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import wieczorek.jakub.shop.business.spring.client.dto.OrderDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Orders")
@Getter
@Setter
public class Order
{
    @Id
    @Column(name = "order_id")
    @SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "order_id_sequence", strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE)
    private Long orderId;

    private Long deliveryId;

    private BigDecimal costOfProducts;

    private BigDecimal costOfDelivery;

    private BigDecimal finalCost;

    public OrderDTO toDTO()
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setCostOfDelivery(costOfDelivery);
        orderDTO.setCostOfProducts(costOfProducts);
        orderDTO.setDeliveryId(deliveryId);
        orderDTO.setFinalCost(finalCost);

        return orderDTO;
    }

    public Order()
    {

    }

    public Order(OrderDTO orderDTO)
    {
        setCostOfDelivery(orderDTO.getCostOfDelivery());
        setCostOfProducts(orderDTO.getCostOfProducts());
        setDeliveryId(orderDTO.getDeliveryId());
        setFinalCost(orderDTO.getFinalCost());
    }
}
