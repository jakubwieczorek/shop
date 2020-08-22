package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ProductOrderIdDTO implements Serializable
{
    private Long productId;

    private Long orderId;

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(!(o instanceof ProductOrderIdDTO)) return false;

        ProductOrderIdDTO that = (ProductOrderIdDTO) o;
        return Objects.equals(getProductId(), that.getProductId()) &&
                Objects.equals(getOrderId(), that.getOrderId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getOrderId(), getProductId());
    }
}
