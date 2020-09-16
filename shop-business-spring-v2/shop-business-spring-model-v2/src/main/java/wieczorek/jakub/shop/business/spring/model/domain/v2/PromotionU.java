package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "promotion")
@Setter
@Getter
public class PromotionU
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
}