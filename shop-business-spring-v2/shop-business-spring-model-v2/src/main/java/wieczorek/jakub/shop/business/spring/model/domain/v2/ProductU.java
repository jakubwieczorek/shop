package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Setter
@Getter
public class ProductU
{
    @Id
    @Column(name = "product_id")
    @SequenceGenerator(name = "product_id_sequence", sequenceName = "product_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "product_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Long amountInStock;
    private String description;
    private Long soldAmount;
    private BigDecimal weight;
    private String vendor;
    private Long productionYear;

    @Column(name = "`size`")
    private String size;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryU category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private PromotionU promotion;
}
