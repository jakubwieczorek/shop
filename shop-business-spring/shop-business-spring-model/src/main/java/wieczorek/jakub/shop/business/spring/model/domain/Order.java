package wieczorek.jakub.shop.business.spring.model.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import wieczorek.jakub.shop.business.spring.client.dto.OrderDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Orders")
@Getter
@Setter
class Order
{
    @Id
    @Column(name = "order_id")
    @SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "order_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long orderId;

    private BigDecimal costOfProducts;

    private BigDecimal costOfDelivery;

    private BigDecimal finalCost;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private Set<ProductOrder> productOrders = new HashSet<>();

//    public void addProduct(Product product)
//    {
//        products.add(product);
//        product.getOrders().add(this);
//    }
//
//    public void removeProduct(Product product)
//    {
//        products.remove(product);
//        product.getOrders().remove(this);
//    }

    public OrderDTO toDTO()
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setCostOfDelivery(costOfDelivery);
        orderDTO.setCostOfProducts(costOfProducts);
        orderDTO.setFinalCost(finalCost);
        // set
        return orderDTO;
    }

    public Order()
    {

    }

    public Order(OrderDTO orderDTO)
    {
        setCostOfDelivery(orderDTO.getCostOfDelivery());
        setCostOfProducts(orderDTO.getCostOfProducts());
        setFinalCost(orderDTO.getFinalCost());

        // set
    }
}
