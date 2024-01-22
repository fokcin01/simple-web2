package org.example;

import org.example.web.resource.ResourceController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext conf= new ClassPathXmlApplicationContext("spring-app.xml")) {
            ResourceController controller= conf.getBean(ResourceController.class);
            System.out.println(controller.getAll());
        }
    }
}