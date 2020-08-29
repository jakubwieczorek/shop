package wieczorek.jakub.shop.business.spring.beans.boundry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wieczorek.jakub.shop.business.spring.beans.ds.CustomerRepository;
import wieczorek.jakub.shop.business.spring.beans.service.Converter;
import wieczorek.jakub.shop.business.spring.client.boundry.TestBean;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TestBeanImpl implements TestBean
{
    private CustomerRepository customerRepository;

    @Autowired
    public TestBeanImpl(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Autowired
    private Converter converter;

    public List<CustomerDTO> readCustomers()
    {
        List<Customer> list = StreamSupport.stream(this.customerRepository.findAll().spliterator(), false).collect(Collectors.toList());

        List<CustomerDTO> listDTO = new ArrayList<>();

        list.forEach(customer -> {listDTO.add(converter.convert(customer, CustomerDTO.class));});

        return listDTO;
    }
}
