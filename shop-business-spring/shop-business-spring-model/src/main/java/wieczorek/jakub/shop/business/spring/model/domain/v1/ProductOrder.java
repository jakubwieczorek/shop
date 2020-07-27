package wieczorek.jakub.shop.business.spring.model.domain.v1;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_order")
@Setter
@Getter
public class ProductOrder
{
    @EmbeddedId
    private ProductOrderId productOrderId = new ProductOrderId();

    private Long amountOfOrderedProducts;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public void addProductOrder(Order aOrder, Product aProduct)
    {
        order = aOrder;
        product = aProduct;

        aOrder.getProductOrders().add(this);
        aProduct.getProductOrders().add(this);
    }
}
