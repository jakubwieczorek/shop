package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Delivery")
@Getter
@Setter
public class Delivery
{
    @Id
    @SequenceGenerator(name = "delivery_id_sequence", sequenceName = "delivery_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "delivery_id_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_id")
    private Long deliveryId;

    private BigDecimal price;

    @Temporal(TemporalType.DATE)
    private Date deliveryTime;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order>orders = new LinkedList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_company_id", nullable = false)
    private DeliveryCompany deliveryCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    public void addOrder(Order order)
    {
        orders.add(order);
        order.setDelivery(this);
    }

    public void removeOrder(Order order)
    {
        orders.remove(order);
        order.setDelivery(null);
    }
}
