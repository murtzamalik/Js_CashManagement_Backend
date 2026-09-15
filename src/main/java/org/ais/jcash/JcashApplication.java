package org.ais.jcash;

import org.ais.jcash.util.JsCashCORSFilter;
import org.ais.jcash.util.JsCashFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "org.ais.jcash.*")
@EnableSwagger2
@EnableScheduling
public class JcashApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JcashApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JcashApplication.class);
    }


    //    @Bean
//    public FilterRegistrationBean corsFilterRegistration() {
//        FilterRegistrationBean registrationBean =
//                new FilterRegistrationBean(new JsCashFilter());
//        registrationBean.setName("jscashfilterbean");
//        registrationBean.addUrlPatterns("/jscash/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
/*    @Bean
    public FilterRegistrationBean swaggerFilterRegistration() {
        FilterRegistrationBean registrationBean =
                new FilterRegistrationBean(new JsCashCORSFilter());
        registrationBean.setName("JsCashCORSFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }*/

/*
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registrationBean =
                new FilterRegistrationBean(new JsCashFilter());
        registrationBean.setName("JsCashFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }
*/

}
