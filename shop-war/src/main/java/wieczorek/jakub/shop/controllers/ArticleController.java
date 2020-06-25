package wieczorek.jakub.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wieczorek.jakub.shop.business.spring.client.boundry.TestBean;
import wieczorek.jakub.shop.business.spring.client.dto.CustomerDTO;
import wieczorek.jakub.shop.dto.Article;
import wieczorek.jakub.shop.services.ArticleDAO;

import java.util.List;

@RestController
public class ArticleController
{
    private ArticleDAO articleDAO;
    private TestBean testBean;

    @Autowired
    public ArticleController(ArticleDAO articleDAO, TestBean testBean)
    {
        this.articleDAO = articleDAO;
        this.testBean = testBean;
    }

    @GetMapping("/getShoes")
    public Article getShoes(@RequestParam(value="size", defaultValue="42") String aSize)
    {
        return articleDAO.retrieveArticle(Long.parseLong(aSize));
    }

    @GetMapping("/testDB")
    public List<CustomerDTO> testDB()
    {
        return testBean.readCustomers();
    }
}