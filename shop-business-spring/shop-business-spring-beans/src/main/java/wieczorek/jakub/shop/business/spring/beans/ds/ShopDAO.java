package wieczorek.jakub.shop.business.spring.beans.ds;

import org.springframework.data.repository.CrudRepository;
import wieczorek.jakub.shop.business.spring.model.entities.Customer;

public interface ShopDAO extends CrudRepository<Customer, String>
{
}
