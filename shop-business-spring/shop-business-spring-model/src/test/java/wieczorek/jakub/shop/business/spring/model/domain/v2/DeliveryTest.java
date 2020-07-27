package wieczorek.jakub.shop.business.spring.model.domain.v2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import wieczorek.jakub.shop.business.spring.client.BusinessConfig;
import wieczorek.jakub.shop.business.spring.model.domain.v1.Delivery;
import wieczorek.jakub.shop.business.spring.model.domain.v1.DeliveryCompany;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {BusinessConfig.class})
@RunWith(SpringRunner.class)
@Sql("init.sql")
@Sql(scripts = "clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EntityScan(basePackages = {"wieczorek.jakub.shop.business.spring.model.domain.v2"})
public class DeliveryTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void selectDeliveryByDate() throws ParseException {
        // Given
        Date deliveryTime = new SimpleDateFormat("dd/MM/yyyy").parse("25/06/2020");

        // when
        Delivery delivery = entityManager.getEntityManager().createQuery("select d from Delivery d where d.deliveryTime = :deliveryTime", Delivery.class)
                .setParameter("deliveryTime", deliveryTime).getSingleResult();

        // then
        Assert.assertNotNull(delivery);
    }

    @Test
    public void insertDelivery()
    {
        DeliveryCompany deliveryCompany = new DeliveryCompany();
        deliveryCompany.setDeliveryCompanyName("aa");

        Delivery delivery = new Delivery();
        delivery.setPrice(BigDecimal.ONE);
        delivery.setDeliveryTime(new Date());
        deliveryCompany.addDelivery(delivery);

        entityManager.persist(deliveryCompany);
        entityManager.flush();
    }

    @Test
    public void insertDeliveryUniDirectional()
    {
        DeliveryCompanyUniDirectional deliveryCompany = new DeliveryCompanyUniDirectional();
        deliveryCompany.setDeliveryCompanyName("aa");

        DeliveryUniDirectional delivery = new DeliveryUniDirectional();
        delivery.setPrice(BigDecimal.ONE);
        delivery.setDeliveryTime(new Date());

        deliveryCompany.getDeliveries().add(delivery);

        entityManager.persist(deliveryCompany);
        entityManager.flush();
    }
}