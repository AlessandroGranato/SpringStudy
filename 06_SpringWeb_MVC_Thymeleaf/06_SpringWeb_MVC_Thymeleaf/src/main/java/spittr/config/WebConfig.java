package spittr.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan({"spittr.web", "spittr.data"})
public class WebConfig extends WebMvcConfigurerAdapter {


    /*
    WebConfig class extends WebMvcConfigurerAdapter and overrides its configureDefaultServletHandling() method.
    By calling enable() on the given DefaultServletHandlerConfigurer, you’re asking DispatcherServlet to forward
    requests for static resources to the servlet container’s default servlet and not to try to handle them itself.
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //----------------- Setting property file Resource bundle for messages -----------------------
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setCacheSeconds(0);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    //----------------------------------------

    @Bean
    public ViewResolver viewResolver(
            SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
    @Bean
    public TemplateEngine templateEngine(
            TemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver =
                new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }






//    //----------------- View Resolver with Apache tiles -----------------------
//    @Bean
//    public TilesConfigurer tilesConfigurer() {
//        TilesConfigurer tiles = new TilesConfigurer();
//        tiles.setDefinitions(new String[] {
//                "/WEB-INF/layout/tiles.xml"
//        });
//        tiles.setCheckRefresh(true);
//        return tiles;
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//        return new TilesViewResolver();
//    }
//    //----------------------------------------


//    //----------------- Simple View Resolver -----------------------
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver =
//                new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        resolver.setExposeContextBeansAsAttributes(true);
//        return resolver;
//    }
//    //----------------------------------------

//    //----------------- View Resolver with JstlView class -----------------------
//    @Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver resolver =
//                new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/views/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(
//                org.springframework.web.servlet.view.JstlView.class);
//        return resolver;
//    }
//    //----------------------------------------


//    //----------------- ApplicationContext for testing purposes -----------------------
//    private static ApplicationContext applicationContext;
//    public static void main(String[] args) {
//        applicationContext =
//                new AnnotationConfigApplicationContext(WebConfig.class);
//
//        System.out.println("-------- MY BEANS --------");
//        for (String beanName : applicationContext.getBeanDefinitionNames()) {
//            System.out.println(beanName);
//        }
//    }
//    //----------------------------------------
}