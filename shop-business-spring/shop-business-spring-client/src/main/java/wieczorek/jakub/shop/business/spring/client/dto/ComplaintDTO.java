package wieczorek.jakub.shop.business.spring.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class ComplaintDTO
{
    private Long complaintId;

    private Date complaintTime;

    private Date handlingTime;

    private String content;

    private Set<OrderDTO> orders = new HashSet<>();

    public void addOrder(OrderDTO order)
    {
        orders.add(order);
        order.getComplaints().add(this);
    }

    public void removeOrder(OrderDTO order)
    {
        orders.remove(order);
        order.getComplaints().remove(this);
    }
}
