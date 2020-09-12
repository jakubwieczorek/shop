package wieczorek.jakub.shop.business.spring.client.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class ProductDTO implements Serializable
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

    @JsonManagedReference
    private Set<ProductOrderDTO> productOrders = new HashSet<>();

    @JsonBackReference
    private CategoryDTO category;

    @JsonBackReference
    private PromotionDTO promotion;
}
