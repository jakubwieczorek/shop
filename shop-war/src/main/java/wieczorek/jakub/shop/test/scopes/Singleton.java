package wieczorek.jakub.shop.test.scopes;

import org.springframework.stereotype.Service;

@Service
public class Singleton
{
    private Long size;

    public void test(Long aSize)
    {
        System.out.println(size);
        size = aSize;
        System.out.println(size);
    }
}
