package org.example;

import org.example.controller.ResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = {"org.example.repository",
        "org.example.controller"})
public class SpringMain {
    @Autowired
     ApplicationContext applicationContext;
    @PostConstruct
    public void huinya(){
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class,args);

    }
}