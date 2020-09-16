package wieczorek.jakub.shop.business.spring.client.v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DeliveryDTO
{
    public Long deliveryId;

    public BigDecimal price;

    public Date deliveryTime;

    private DeliveryCompanyUDTO deliveryCompany;

    private PromotionUDTO promotion;
}
