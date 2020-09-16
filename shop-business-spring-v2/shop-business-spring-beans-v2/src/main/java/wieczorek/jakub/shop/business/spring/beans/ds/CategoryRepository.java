package wieczorek.jakub.shop.business.spring.beans.ds;

import org.springframework.data.jpa.repository.JpaRepository;
import wieczorek.jakub.shop.business.spring.model.domain.v2.CategoryU;

public interface CategoryRepository extends JpaRepository<CategoryU, Long> {
}
