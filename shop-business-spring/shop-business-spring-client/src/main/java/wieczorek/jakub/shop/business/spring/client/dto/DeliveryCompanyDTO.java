package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeliveryCompanyDTO
{

    private Long deliveryCompanyId;

    private String deliveryCompanyName;

    private List<DeliveryDTO> deliveries;

    public void addDelivery(DeliveryDTO delivery)
    {
        deliveries.add(delivery);
        delivery.setDeliveryCompany(this);
    }

    public void removeDelivery(DeliveryDTO delivery)
    {
        deliveries.remove(delivery);
        delivery.setDeliveryCompany(null);
    }
}