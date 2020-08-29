package wieczorek.jakub.shop.business.spring.beans.ds;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Complaint;
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

    @Transactional
    public Customer findCustomerByEmail(String email)
    {
        return entityManager.unwrap(Session.class).bySimpleNaturalId(Customer.class).load(email);
    }

    @Transactional
    // check in spring project, whether to use transactional from spring or persistence
    public void removeOrder(Customer customer, Order order)
    {
        order.getDelivery().getDeliveryCompany().removeDelivery(order.getDelivery());
        order.getDelivery().removeOrder(order);
        customer.removeOrder(order);

        order.getComplaints().forEach(complaint -> {complaint.removeOrder(order);
            entityManager.remove(complaint);
        });

        customer.removeOrder(order);
    }

    public void removeComplaint(Complaint complaint)
    {
        complaint.getOrders().forEach(order -> order.removeComplaint(complaint));
        entityManager.remove(complaint);
    }
}