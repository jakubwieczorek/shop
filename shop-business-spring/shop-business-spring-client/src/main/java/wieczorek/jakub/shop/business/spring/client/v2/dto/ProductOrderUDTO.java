package wieczorek.jakub.shop.business.spring.client.v2.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOrderUDTO
{
    private ProductOrderIdUDTO productOrderId = new ProductOrderIdUDTO();

    private Long amountOfOrderedProducts;

    @JsonBackReference
    private ProductUDTO product;

    @JsonBackReference
    private OrderUDTO order;
}
