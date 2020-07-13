package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DeliveryDTO
{
    public BigDecimal price;

    public Date deliveryTime;

    public List<OrderDTO>orders;
}
