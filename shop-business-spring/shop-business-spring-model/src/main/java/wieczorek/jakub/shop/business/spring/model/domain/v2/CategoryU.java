package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoryU
{
    @Id
    @Column(name = "category_id")
    @SequenceGenerator(name = "category_id_sequence", sequenceName = "category_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "category_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private CategoryU parentCategory;

    @Column(name= "category_name")
    @NaturalId
    private String categoryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private PromotionU promotion;
}
