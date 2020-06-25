package wieczorek.jakub.shop.business.spring.beans.boundry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wieczorek.jakub.shop.business.spring.beans.ds.ShopDAO;
import wieczorek.jakub.shop.business.spring.client.boundry.TestBean;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;
import wieczorek.jakub.shop.business.spring.model.entities.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TestBeanImpl implements TestBean
{
    private ShopDAO shopDAO;

    @Autowired
    public TestBeanImpl(ShopDAO shopDAO)
    {
        this.shopDAO = shopDAO;
    }

    public List<CustomerDTO> readCustomers()
    {
        List<Customer> list = StreamSupport.stream(this.shopDAO.findAll().spliterator(), false).collect(Collectors.toList());

        List<CustomerDTO> listDTO = new ArrayList<>();

        list.forEach(customer -> {listDTO.add(customer.toDTO());});

        return listDTO;
    }
}
