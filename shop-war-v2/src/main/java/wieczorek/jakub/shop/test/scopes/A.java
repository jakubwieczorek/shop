package wieczorek.jakub.shop.test.scopes;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class A
{
    private Long value;

    public void test(Long aValue)
    {
        System.out.println(value);
        value = aValue;
    }
}