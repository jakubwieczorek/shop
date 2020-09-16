package wieczorek.jakub.shop.business.spring.model.domain.v2;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "complaint")
@Setter
@Getter
public class ComplaintU
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
}
