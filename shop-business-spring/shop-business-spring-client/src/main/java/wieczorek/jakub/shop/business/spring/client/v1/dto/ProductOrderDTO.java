package wieczorek.jakub.shop.business.spring.client.v1.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOrderDTO
{
    private ProductOrderIdDTO productOrderId = new ProductOrderIdDTO();

    private Long amountOfOrderedProducts;

    @JsonBackReference
    private ProductDTO product;

    @JsonBackReference
    private OrderDTO order;

    public void addProductOrder(OrderDTO aOrder, ProductDTO aProduct)
    {
        order = aOrder;
        product = aProduct;

        aOrder.getProductOrders().add(this);
        aProduct.getProductOrders().add(this);
    }
}
