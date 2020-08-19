package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category
{
    @Id
    @Column(name = "category_id")
    @SequenceGenerator(name = "category_id_sequence", sequenceName = "category_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "category_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subCategories = new LinkedList<>();

    @Column(name= "category_name", unique = true)
    @NaturalId
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new LinkedList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    public void addProduct(Product product)
    {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product)
    {
        products.remove(product);
        product.setCategory(null);
    }

    public void addSubCategory(Category category)
    {
        subCategories.add(category);
        category.setParentCategory(this);
    }

    public void removeSubCategory(Category category)
    {
        subCategories.remove(category);
        category.setParentCategory(null);
    }
}
