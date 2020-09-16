package wieczorek.jakub.shop.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import wieczorek.jakub.shop.dto.Article;

@Service
//by default singleton, so one bean per spring application
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@RequestScope // object per request - first size null always
//@SessionScope // object per session - first size null only at the beginning, if 2 cards opened and one passes aSize==50
//then in second card first System out will print 50, if card is closed, then after reopening aSize will be retained.
public class ArticleDAO
{
    private Long size;

    public Article retrieveArticle(Long aSize)
    {
        System.out.println(size);
        size = aSize;
        System.out.println(size);

        System.out.println();
        Article article = new Article();
        article.setAvailability(5L);
        article.setDescription("Sport shoes from Nike company, best for running, but suitable also for other daily use.");
        article.setName("Nike, Total 50 shoe, size " + aSize);
        article.setPrice(199.99);
        return article;
    }
}
