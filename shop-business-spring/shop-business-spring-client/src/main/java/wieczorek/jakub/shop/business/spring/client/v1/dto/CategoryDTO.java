package wieczorek.jakub.shop.business.spring.client.v1.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO implements Serializable
{
    private Long categoryId;

    @JsonBackReference
    private CategoryDTO parentCategory;

    @JsonManagedReference
    private List<CategoryDTO> subCategories = new LinkedList<>();

    private String categoryName;

    @JsonManagedReference
    private List<ProductDTO> products = new LinkedList<>();

    @JsonBackReference
    private PromotionDTO promotion;

    public void addProduct(ProductDTO product)
    {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(ProductDTO product)
    {
        products.remove(product);
        product.setCategory(null);
    }

    public void addSubCategory(CategoryDTO category)
    {
        subCategories.add(category);
        category.setParentCategory(this);
    }

    public void removeSubCategory(CategoryDTO category)
    {
        subCategories.remove(category);
        category.setParentCategory(null);
    }
}
