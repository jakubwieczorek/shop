package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeliveryCompanyDTO
{
    private String deliveryCompanyName;

    private List<DeliveryDTO> deliveries;
}