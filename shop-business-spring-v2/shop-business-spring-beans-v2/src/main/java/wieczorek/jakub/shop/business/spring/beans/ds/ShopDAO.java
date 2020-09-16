package wieczorek.jakub.shop.business.spring.beans.ds;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import wieczorek.jakub.shop.business.spring.model.domain.v2.CategoryU;
import wieczorek.jakub.shop.business.spring.model.domain.v2.CustomerU;
import wieczorek.jakub.shop.business.spring.model.domain.v2.OrderU;
import wieczorek.jakub.shop.business.spring.model.domain.v2.ProductU;

import javax.persistence.EntityManager;
import java.util.List;

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
    public List<CategoryU> fetchCategories()
    {
        return categoryRepository.findAll();
    }

    @Transactional
    public CustomerU findCustomerByEmail(String email)
    {
        return entityManager.unwrap(Session.class).bySimpleNaturalId(CustomerU.class).load(email);
    }

    @Transactional
    public void removeOrder(OrderU order)
    {
        entityManager.remove(order.getDelivery());
        order.getComplaints().forEach(complaint -> {
            entityManager.remove(complaint);
        });
        entityManager.remove(order);
    }

    @Transactional
    public List<ProductU> fetchProducts(Long categoryId)
    {
        return entityManager.createQuery("select p from ProductU p where p.category.categoryId = :categoryId", ProductU.class).
                setParameter("categoryId", categoryId).getResultList();
    }
}