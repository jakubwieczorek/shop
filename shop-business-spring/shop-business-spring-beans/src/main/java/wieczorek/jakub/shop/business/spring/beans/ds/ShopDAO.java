package wieczorek.jakub.shop.business.spring.beans.ds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Customer;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Order;

import javax.persistence.EntityManager;

@Repository
public class ShopDAO
{
    private EntityManager entityManager;

    @Autowired
    public ShopDAO(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void removeOrder(Customer customer, Order order)
    {
        order.getDelivery().getDeliveryCompany().removeDelivery(order.getDelivery());
        entityManager.remove(order.getDelivery());
        order.getDelivery().removeOrder(order);
        customer.removeOrder(order);

        order.getComplaints().forEach(complaint -> {complaint.removeOrder(order);
            entityManager.remove(complaint);
        });

        entityManager.remove(order);
        customer.removeOrder(order);
    }
}