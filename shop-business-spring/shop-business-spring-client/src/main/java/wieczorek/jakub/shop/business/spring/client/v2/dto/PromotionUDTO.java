package wieczorek.jakub.shop.business.spring.client.v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class PromotionUDTO
{
    private Long promotionId;

    private BigDecimal percentage;

    private String description;

    private Date deadline;
}