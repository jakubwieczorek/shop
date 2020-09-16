package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "delivery_company")
@Setter
@Getter
public class DeliveryCompanyU {

    @Id
    @Column(name = "delivery_company_id")
    @SequenceGenerator(name = "delivery_company_id_sequence", sequenceName = "delivery_company_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "delivery_company_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long deliveryCompanyId;

    @Column
    @NaturalId
    private String deliveryCompanyName;
}