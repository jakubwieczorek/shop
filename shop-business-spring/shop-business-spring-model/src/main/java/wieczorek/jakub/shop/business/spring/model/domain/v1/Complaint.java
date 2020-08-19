package wieczorek.jakub.shop.business.spring.model.domain.v1;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "complaint")
@Setter
@Getter
public class Complaint
{
    @Id
    @Column(name = "complaint_id")
    @SequenceGenerator(name = "complaint_id_sequence", sequenceName = "complaint_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "complaint_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long complaintId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date complaintTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date handlingTime;

    @Column(nullable = false)
    private String content;

    @ManyToMany(mappedBy = "complaints", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Order> orders = new HashSet<>();

    public void addOrder(Order order)
    {
        orders.add(order);
        order.getComplaints().add(this);
    }

    public void removeOrder(Order order)
    {
        orders.remove(order);
        order.getComplaints().remove(this);
    }
}
