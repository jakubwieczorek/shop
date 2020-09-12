package wieczorek.jakub.shop;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import wieczorek.jakub.shop.controllers.CategoryController;

import javax.servlet.ServletContext;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CategoryController.class})
//@WebAppConfiguration
@WebMvcTest // with this MockMvc is automatically configured
public class CategoryControllerTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void printBeans() {
        System.out.println("aaa");
        Arrays.asList(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println("aaa");
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);

        printBeans();
        Assert.assertNotNull(webApplicationContext.getBean("categoryController"));
    }

//    @Before
//    public void setUp()
//    {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    public void aaa() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/order"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
