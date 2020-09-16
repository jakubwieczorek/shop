package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "delivery")
@Getter
@Setter
public class DeliveryU
{
    @Id
    @SequenceGenerator(name = "delivery_id_sequence", sequenceName = "delivery_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "delivery_id_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_id")
    private Long deliveryId;

    private BigDecimal price;

    @Temporal(TemporalType.DATE)
    private Date deliveryTime;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_company_id", nullable = false)
    private DeliveryCompanyU deliveryCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private PromotionU promotion;
}
