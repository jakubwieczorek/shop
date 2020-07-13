package wieczorek.jakub.shop.business.spring.model.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import wieczorek.jakub.shop.business.spring.client.dto.DeliveryDTO;
import wieczorek.jakub.shop.business.spring.client.dto.OrderDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Delivery")
@Getter
@Setter
class Delivery
{
    @Id
    @SequenceGenerator(name = "delivery_id_sequence", sequenceName = "delivery_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "delivery_id_sequence", strategy = GenerationType.SEQUENCE)
    @Setter(value = AccessLevel.NONE)
    @Column(name = "delivery_id")
    public Long deliveryId;

    public BigDecimal price;

    @Temporal(TemporalType.DATE)
    public Date deliveryTime;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    public List<Order>orders = new LinkedList<>();

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_company_id", nullable = false)
    public DeliveryCompany deliveryCompany;

    public void addOrder(Order order)
    {
        orders.add(order);
        order.setDelivery(this);
    }

    public Delivery()
    {

    }

    public Delivery(DeliveryDTO deliveryDTO)
    {
        setDeliveryTime(deliveryDTO.getDeliveryTime());
        setPrice(deliveryDTO.getPrice());
        setOrders(deliveryDTO.getOrders().stream().map(Order::new).collect(Collectors.toList()));
    }

    public DeliveryDTO toDTO()
    {
        DeliveryDTO deliveryDTO = new DeliveryDTO();

        deliveryDTO.setDeliveryTime(getDeliveryTime());
        deliveryDTO.setPrice(getPrice());
        deliveryDTO.setOrders(orders.stream().map(Order::toDTO).collect(Collectors.toList()));

        return deliveryDTO;
    }
}
