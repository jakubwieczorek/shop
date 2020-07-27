package wieczorek.jakub.shop.business.spring.model.domain.v1;

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

@DataJpaTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BusinessConfig.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("init.sql")
@Sql(scripts = "clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@EntityScan(basePackages = "wieczorek.jakub.shop.business.spring.model.domain.v1")
public class OrderTest
{
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void test()
    {

    }
}
