package wieczorek.jakub.shop.business.spring.client.v2.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ComplaintUDTO
{
    private Long complaintId;

    private Date complaintTime;

    private Date handlingTime;

    private String content;
}
