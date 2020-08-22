package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product
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

    @OneToMany(mappedBy = "product")
    private Set<ProductOrder> productOrders= new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
}
