package wieczorek.jakub.shop.business.spring.beans.ds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
