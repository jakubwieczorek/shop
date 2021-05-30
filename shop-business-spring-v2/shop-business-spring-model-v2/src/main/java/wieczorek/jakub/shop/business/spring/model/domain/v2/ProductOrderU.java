package wieczorek.jakub.shop.business.spring.model.domain.v2;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_order")
@Setter
@Getter
public class ProductOrderU
{
    @EmbeddedId
    private ProductOrderIdU productOrderId = new ProductOrderIdU();

    private Long amountOfOrderedProducts;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductU product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private OrderU order;
}
