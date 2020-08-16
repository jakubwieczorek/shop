package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;
import wieczorek.jakub.shop.business.spring.client.dto.DeliveryCompanyDTO;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
    private String deliveryCompanyName;

    @OneToMany(mappedBy = "deliveryCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Delivery> deliveries = new LinkedList<>();

    public void addDelivery(Delivery delivery)
    {
        deliveries.add(delivery);
        delivery.setDeliveryCompany(this);
    }

    public DeliveryCompany()
    {

    }

    public DeliveryCompany(DeliveryCompanyDTO deliveryCompanyDTO)
    {
        setDeliveryCompanyName(deliveryCompanyDTO.getDeliveryCompanyName());
        setDeliveries(deliveryCompanyDTO.getDeliveries().stream().map(Delivery::new).collect(Collectors.toList()));
    }

    public DeliveryCompanyDTO toDTO()
    {
        DeliveryCompanyDTO deliveryCompanyDTO = new DeliveryCompanyDTO();
        deliveryCompanyDTO.setDeliveryCompanyName(getDeliveryCompanyName());
        deliveryCompanyDTO.setDeliveries(getDeliveries().stream().map(Delivery::toDTO).collect(Collectors.toList()));
        return deliveryCompanyDTO;
    }
}