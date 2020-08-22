package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class DeliveryDTO
{
    public Long deliveryId;

    public BigDecimal price;

    public Date deliveryTime;

    public List<OrderDTO>orders = new LinkedList<>();

    private DeliveryCompanyDTO deliveryCompany;

    private PromotionDTO promotion;

    public void addOrder(OrderDTO order)
    {
        orders.add(order);
        order.setDelivery(this);
    }

    public void removeOrder(OrderDTO order)
    {
        orders.remove(order);
        order.setDelivery(null);
    }
}
