package wieczorek.jakub.shop.test.scopes;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class Request
{
    private Long size;

    public void test(Long aSize)
    {
        System.out.println(size);
        size = aSize;
        System.out.println(size);
    }
}