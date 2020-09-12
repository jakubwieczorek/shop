package wieczorek.jakub.shop.business.spring.client.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class DeliveryCompanyDTO
{

    private Long deliveryCompanyId;

    private String deliveryCompanyName;

    @JsonManagedReference
    private List<DeliveryDTO> deliveries = new LinkedList<>();

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