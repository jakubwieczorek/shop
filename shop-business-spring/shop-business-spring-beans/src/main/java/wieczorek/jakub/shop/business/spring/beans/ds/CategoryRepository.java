package wieczorek.jakub.shop.business.spring.beans.ds;

import org.springframework.data.jpa.repository.JpaRepository;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
