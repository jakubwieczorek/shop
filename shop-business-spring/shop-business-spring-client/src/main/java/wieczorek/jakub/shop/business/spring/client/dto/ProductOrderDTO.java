package wieczorek.jakub.shop.business.spring.client.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductOrderDTO
{
    private ProductOrderIdDTO productOrderId = new ProductOrderIdDTO();

    private Long amountOfOrderedProducts;

    private ProductDTO product;

    private OrderDTO order;

    public void addProductOrder(OrderDTO aOrder, ProductDTO aProduct)
    {
        order = aOrder;
        product = aProduct;

        aOrder.getProductOrders().add(this);
        aProduct.getProductOrders().add(this);
    }
}
