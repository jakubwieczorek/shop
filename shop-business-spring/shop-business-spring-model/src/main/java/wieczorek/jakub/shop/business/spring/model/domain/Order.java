package wieczorek.jakub.shop.business.spring.model.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    @GeneratedValue(generator = "order_id_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence", allocationSize = 1)
//    @GenericGenerator(name = "order_id_sequence", strategy = "sequence", parameters = {
//            @Parameter(name="sequence", value="order_id_sequence")
//    })
    private Long orderId;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "delivery_id")
    private Long deliveryId;

    @Column(name = "cost_of_products")
    private BigDecimal costOfProducts;

    @Column(name = "cost_of_delivery")
    private BigDecimal costOfDelivery;

    @Column(name = "final_cost")
    private BigDecimal finalCost;
}
