package wieczorek.jakub.shop.business.spring.client.v2.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOrderUDTO
{
    private ProductOrderIdUDTO productOrderId = new ProductOrderIdUDTO();

    private Long amountOfOrderedProducts;

    private ProductUDTO product;

    private OrderUDTO order;
}
