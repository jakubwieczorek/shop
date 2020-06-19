//package wieczorek.jakub.shop.test.scopes;
//
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import wieczorek.jakub.shop.test.scopes.ctx1.Ctx1Config;
//import wieczorek.jakub.shop.test.scopes.ctx2.Ctx2Config;
//
//public class ScopeTestApp
//{
//    public static void main(String [] args)
//    {
//        new SpringApplicationBuilder()
//                .sources(ParentConfig.class).web(WebApplicationType.NONE)
//                .child(Ctx1Config.class).web(WebApplicationType.SERVLET)
//                .sibling(Ctx2Config.class).web(WebApplicationType.SERVLET)
//                .build().run(args);
//    }
//}
