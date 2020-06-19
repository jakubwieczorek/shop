package wieczorek.jakub.shop.test.scopes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Prototype
{
    private Long size;

    public void test(Long aSize)
    {
        System.out.println(size);
        size = aSize;
        System.out.println(size);
    }
}
