package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ProductOrderId implements Serializable
{
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "order_id")
    private Long orderId;

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof ProductOrderId)) return false;

        ProductOrderId that = (ProductOrderId) o;
        return Objects.equals(getProductId(), that.getProductId()) &&
                Objects.equals(getOrderId(), that.getOrderId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getOrderId(), getProductId());
    }
}
