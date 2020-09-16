package wieczorek.jakub.shop.business.spring.client.v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
public class ProductUDTO implements Serializable
{
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Long amountInStock;
    private String description;
    private Long soldAmount;
    private BigDecimal weight;
    private String vendor;
    private Long productionYear;
    private String size;

    private CategoryUDTO category;

    private PromotionUDTO promotion;
}
