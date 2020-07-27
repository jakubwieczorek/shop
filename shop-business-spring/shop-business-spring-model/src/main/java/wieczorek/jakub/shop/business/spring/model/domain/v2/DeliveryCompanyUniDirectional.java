package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "delivery_company")
@Setter
@Getter
public class DeliveryCompanyUniDirectional {

    @Id
    @Column(name = "delivery_company_id")
    @SequenceGenerator(name = "delivery_company_id_sequence", sequenceName = "delivery_company_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "delivery_company_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long deliveryCompanyId;

    @Column(unique = true)
    private String deliveryCompanyName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_company_id", referencedColumnName="delivery_company_id", nullable = false)
    private List<DeliveryUniDirectional> deliveries = new LinkedList<>();
}