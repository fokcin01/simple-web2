package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"org.example.controller"})
//@EnableJpaRepositories("org.example.repository")
public class SpringMain {
    @Autowired
     ApplicationContext applicationContext;
    @PostConstruct
    public void huinya(){
//        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class,args);

    }
}