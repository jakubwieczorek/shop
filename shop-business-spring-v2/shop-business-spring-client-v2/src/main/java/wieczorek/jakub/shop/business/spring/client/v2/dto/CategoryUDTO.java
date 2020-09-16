package wieczorek.jakub.shop.business.spring.client.v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryUDTO implements Serializable
{
    private Long categoryId;

    private CategoryUDTO parentCategory;

    private String categoryName;

    private PromotionUDTO promotion;
}
