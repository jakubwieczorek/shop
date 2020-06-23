package wieczorek.jakub.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = {Application.class, ApplicationConfig.class})
public class ShopApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ShopApplication.class, args);
	}
}