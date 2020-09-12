package wieczorek.jakub.shop.business.spring.beans.ds;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import wieczorek.jakub.shop.business.spring.model.domain.v1.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository
public class ShopDAO
{
    private EntityManager entityManager;

    private CategoryRepository categoryRepository;

    @Autowired
    public ShopDAO(EntityManager entityManager, CategoryRepository categoryRepository)
    {
        this.entityManager = entityManager;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public List<Category> fetchCategories()
    {
        return categoryRepository.findAll();
    }

    @Transactional
    public Customer findCustomerByEmail(String email)
    {
        return entityManager.unwrap(Session.class).bySimpleNaturalId(Customer.class).load(email);
    }

    @Transactional
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

    @Transactional
    public void removeComplaint(Complaint complaint)
    {
        complaint.getOrders().forEach(order -> order.removeComplaint(complaint));
        entityManager.remove(complaint);
    }

    @Transactional
    public void addOrder(Customer customer, Order order, Set<ProductOrder> productOrders)
    {
        entityManager.persist(customer);
        customer.addOrder(order);

        order.setProductOrders(productOrders);
        order.getProductOrders().forEach(productOrder -> entityManager.persist(productOrder));
    }

    @Transactional
    public void addDelivery(DeliveryCompany deliveryCompany, Delivery delivery)
    {
        deliveryCompany.addDelivery(delivery);
        entityManager.persist(deliveryCompany);
        entityManager.persist(delivery);
    }

    // adds category and products to managed state
    @Transactional
    public void persistCategoryAndProducts(Category category, List<Product> products)
    {
        entityManager.persist(category);
        category.setProducts(products);
    }
}