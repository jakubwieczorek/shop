package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Delivery")
@Getter
@Setter
public class DeliveryUniDirectional
{
    @Id
    @SequenceGenerator(name = "delivery_id_sequence", sequenceName = "delivery_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "delivery_id_sequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "delivery_id")
    public Long deliveryId;

    public BigDecimal price;

    @Temporal(TemporalType.DATE)
    public Date deliveryTime;

//    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
//    public List<Order>orders = new LinkedList<>();
}
