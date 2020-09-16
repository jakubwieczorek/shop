package wieczorek.jakub.shop.test.scopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@RestController
public class ScopeController
{
    private Prototype prototype1;
    private Prototype prototype2;

    private Singleton singleton1;
    private Singleton singleton2;

    private Request request;

    private Session session;

    @Autowired
    ScopeController(Prototype prototype1, Prototype prototype2,
                    Singleton singleton1, Singleton singleton2,
                    Request request, Session session)
    {
        this.prototype1 = prototype1;
        this.prototype2 = prototype2;

        this.singleton1 = singleton1;
        this.singleton2 = singleton2;

        this.request = request;

        this.session = session;
    }

    @GetMapping("/testPrototype")
    public void testPrototype()
    {
        prototype1.test(5L);  // null 5
        prototype2.test(10L); // null 10 - two different objects
    }

    @GetMapping("/testSingleton")
    public void testSingleton()
    {
        singleton1.test(5L);  // null 5
        singleton2.test(10L); // 5 10 - same object
    }

    // only available in web aware scopes
    @GetMapping("/testRequest")
    public void testRequest()
    {
        request.test(5L); // null 5 always if invoked in the same http method
    }

    @GetMapping("/testSession")
    public void testSession()
    {
        // change application properties to manage session timeout using tomcat only more then 60s
        session.test(5L); // null 5 only once, next null 5 when session expires
    }

    @GetMapping("/testApplication")
    public void testApplication()
    {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(A.class);
        applicationContext.refresh();

        AnnotationConfigWebApplicationContext applicationContext2 = new AnnotationConfigWebApplicationContext();
        applicationContext2.register(A.class);
        applicationContext2.refresh();

        // java.lang.IllegalStateException: No Scope registered for scope name 'a', when @ApplicationScope
        A a = (A)applicationContext.getBean("a");
        a.test(5L);

        A a2 = (A)applicationContext2.getBean("a");
        a2.test(5L);
    }
}