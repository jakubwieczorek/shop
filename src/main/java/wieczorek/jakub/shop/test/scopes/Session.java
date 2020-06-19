package wieczorek.jakub.shop.test.scopes;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class Session
{
    private Long size;

    public void test(Long aSize)
    {
        System.out.println(size);
        size = aSize;
        System.out.println(size);
    }
}
