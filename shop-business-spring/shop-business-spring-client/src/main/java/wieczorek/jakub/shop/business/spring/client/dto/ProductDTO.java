package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class ProductDTO
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
    private Set<ProductOrderDTO> productOrders = new HashSet<>();
    private CategoryDTO category;
    private PromotionDTO promotion;
}
