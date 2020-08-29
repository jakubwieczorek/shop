package wieczorek.jakub.shop.business.spring.beans.boundry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wieczorek.jakub.shop.business.spring.beans.ds.CustomerRepository;
import wieczorek.jakub.shop.business.spring.beans.ds.ShopDAO;
import wieczorek.jakub.shop.business.spring.beans.service.Converter;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Customer;

@Service
public class ShopClientManager
{
    private CustomerRepository customerRepository;

    private Converter converter;

    private ShopDAO shopDAO;

    @Autowired
    public ShopClientManager(ShopDAO shopDAO,
                             Converter converter,
                             CustomerRepository customerRepository)
    {
        this.shopDAO = shopDAO;
        this.converter = converter;
        this.customerRepository = customerRepository;
    }

    public void createNewCustomer(CustomerDTO customerDTO)
    {
        Customer customer = converter.convert(customerDTO, Customer.class);

        if(shopDAO.findCustomerByEmail(customer.getEmail()) != null)
        {
            throw new RuntimeException();
        }

        customerRepository.save(customer);
    }
}
