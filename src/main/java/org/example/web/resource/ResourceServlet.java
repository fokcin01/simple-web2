package org.example.web.resource;

import org.example.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.module.Configuration;

public class ResourceServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ResourceServlet.class);
    private ConfigurableApplicationContext springContext;
    private ResourceController controller;

    @Override
    public void init() throws ServletException {
        springContext = new ClassPathXmlApplicationContext("spring-app.xml");
        controller = springContext.getBean(ResourceController.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringId = req.getParameter("id");
        Integer id = stringId == null ? null : Integer.parseInt(stringId);
        String name = req.getParameter("name");
        Integer price = Integer.valueOf(req.getParameter("price"));
        logger.info(String.valueOf(id));
        logger.info(name);
        logger.info(String.valueOf(price));
        Resource resource = new Resource(id,name,price);
        controller.save(resource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        logger.info("action: " + req.getParameter("action"));
        if(action == null){
            action = "all";
        }
        if (action.equals("delete")) {
            Integer id = Integer.valueOf(req.getParameter("id"));
            logger.info("id: " + id);
            controller.delete(id);
        }
        else if( action.equals("update") || action.equals("create")){
            Resource resource;
            Integer id = Integer.valueOf(req.getParameter("id"));
            if(action.equals("create")){
                resource = new Resource(null,"daIPohui",1);
            }else{
                resource = controller.getById(id);
            }
            req.setAttribute("resource",resource);
            req.getRequestDispatcher("/resourceForm.jsp").forward(req,resp);
            logger.info("id: " + id);
        }
        else{
            req.setAttribute("resources", controller.getAll());
            req.getRequestDispatcher("resources.jsp").forward(req, resp);
        }
        req.setAttribute("resources", controller.getAll());
        req.getRequestDispatcher("resources.jsp").forward(req, resp);

    }

}
