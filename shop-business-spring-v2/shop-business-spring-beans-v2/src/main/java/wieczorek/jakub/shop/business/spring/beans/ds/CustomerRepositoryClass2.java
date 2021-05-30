package wieczorek.jakub.shop.business.spring.beans.ds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

public class CustomerRepositoryClass2<CustomerU, Long> extends
        SimpleJpaRepository<CustomerU, Long> implements
        CustomerRepository2<CustomerU, Long> {
    private EntityManager entityManager;

    public CustomerRepositoryClass2(JpaEntityInformation<CustomerU, ?>
                                          entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
}
