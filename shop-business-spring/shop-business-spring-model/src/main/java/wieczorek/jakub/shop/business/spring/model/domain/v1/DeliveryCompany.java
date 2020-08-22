package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "delivery_company")
@Setter
@Getter
public class DeliveryCompany {

    @Id
    @Column(name = "delivery_company_id")
    @SequenceGenerator(name = "delivery_company_id_sequence", sequenceName = "delivery_company_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "delivery_company_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long deliveryCompanyId;

    @Column(unique = true)
    @NaturalId
    private String deliveryCompanyName;

    @OneToMany(mappedBy = "deliveryCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveries = new LinkedList<>();

    public void addDelivery(Delivery delivery)
    {
        deliveries.add(delivery);
        delivery.setDeliveryCompany(this);
    }

    public void removeDelivery(Delivery delivery)
    {
        deliveries.remove(delivery);
        delivery.setDeliveryCompany(null);
    }
}