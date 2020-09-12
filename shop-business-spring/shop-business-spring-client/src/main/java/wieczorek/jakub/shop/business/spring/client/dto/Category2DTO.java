package wieczorek.jakub.shop.business.spring.client.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="categoryId")
public class Category2DTO implements Serializable
{
    private Long categoryId;
    private String categoryName;

    @JsonManagedReference
    private List<Product2DTO> products = new LinkedList<>();

    public void addProduct(Product2DTO product)
    {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product2DTO product)
    {
        products.remove(product);
        product.setCategory(null);
    }
}


