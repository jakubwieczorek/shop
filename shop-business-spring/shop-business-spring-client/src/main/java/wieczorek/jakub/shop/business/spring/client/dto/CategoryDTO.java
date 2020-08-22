package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class CategoryDTO
{
    private Long categoryId;

    private CategoryDTO parentCategory;

    private List<CategoryDTO> subCategories = new LinkedList<>();

    private String categoryName;

    private List<ProductDTO> products = new LinkedList<>();

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
