package wieczorek.jakub.shop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import wieczorek.jakub.shop.business.spring.client.BusinessConfig;

@Configuration
@Import(value = {BusinessConfig.class})
//@Profile({"production"})
public class Config {
}
