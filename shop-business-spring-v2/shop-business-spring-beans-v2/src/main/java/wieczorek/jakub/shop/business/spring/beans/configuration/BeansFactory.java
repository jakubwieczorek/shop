package wieczorek.jakub.shop.business.spring.beans.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansFactory
{
    @Bean
    public ModelMapper produceModelMapper()
    {
        return new ModelMapper();
    }
}
