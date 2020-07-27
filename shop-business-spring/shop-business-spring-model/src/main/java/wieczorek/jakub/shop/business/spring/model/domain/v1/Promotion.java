package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "Promotion")
@Setter
@Getter
public class Promotion
{
    @Id
    @Column(name = "PROMOTION_ID")
    @SequenceGenerator(name = "promotion_id_sequence", sequenceName = "promotion_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "promotion_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long promotionId;

    private BigDecimal percentage;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    //@OneToMany(mappedBy = "promotion")
    @OneToMany(mappedBy = "promotion", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Product> products = new LinkedList<>();

    @OneToMany(mappedBy = "promotion", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Delivery> deliveries = new LinkedList<>();

    @OneToMany(mappedBy = "promotion", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Category>categories = new LinkedList<>();

    public void addCategory(Category category)
    {
        categories.add(category);
        category.setPromotion(this);
    }

    public void addDelivery(Delivery delivery)
    {
        deliveries.add(delivery);
        delivery.setPromotion(this);
    }

    public void addProduct(Product product)
    {
        products.add(product);
        product.setPromotion(this);
    }
}