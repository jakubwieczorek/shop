package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class PromotionDTO
{
    private Long promotionId;

    private BigDecimal percentage;

    private String description;

    private Date deadline;

    private List<ProductDTO> products = new LinkedList<>();

    private List<DeliveryDTO> deliveries = new LinkedList<>();

    private List<CategoryDTO>categories = new LinkedList<>();

    public void addCategory(CategoryDTO category)
    {
        categories.add(category);
        category.setPromotion(this);
    }

    public void addDelivery(DeliveryDTO delivery)
    {
        deliveries.add(delivery);
        delivery.setPromotion(this);
    }

    public void addProduct(ProductDTO product)
    {
        products.add(product);
        product.setPromotion(this);
    }
}