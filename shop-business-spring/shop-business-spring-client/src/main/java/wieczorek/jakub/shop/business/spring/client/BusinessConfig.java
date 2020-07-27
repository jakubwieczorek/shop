package wieczorek.jakub.shop.business.spring.client;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"wieczorek.jakub.shop.business.spring"})
@EnableJpaRepositories(basePackages = {"wieczorek.jakub.shop.business.spring"})
@EntityScan(basePackages = {"wieczorek.jakub.shop.business.spring.model.domain.v1"})
public class BusinessConfig
{
}
