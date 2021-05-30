package wieczorek.jakub.shop.business.spring.beans.ds;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import wieczorek.jakub.shop.business.spring.model.domain.v2.CustomerU;

import java.util.List;

@NoRepositoryBean
public interface CustomerRepository2<CustomerU, Long> extends JpaRepository<CustomerU, Long> {
    @Override
    List<CustomerU> findAll();
}
