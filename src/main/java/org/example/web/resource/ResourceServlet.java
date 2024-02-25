package org.example.web.resource;

import org.example.controller.ResourceController;
import org.example.model.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(ResourceServlet.class);
    private ConfigurableApplicationContext springContext;
    @Autowired
    private ResourceController controller;
    @Autowired
    ApplicationContext applicationContext;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String stringId = req.getParameter("id");
        Integer id = stringId.isEmpty() ? null : Integer.parseInt(stringId);
        String name = req.getParameter("name");
        Integer price = Integer.valueOf(req.getParameter("price"));
        logger.info(String.valueOf(id));
        logger.info(name);
        logger.info(String.valueOf(price));
        Resource resource = new Resource(id,name,price);
        controller.save(resource);
        resp.sendRedirect("resources");
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

            if(action.equals("create")){
                resource = new Resource(null,"daIPohui",1);
            }else{
                Integer id = Integer.valueOf(req.getParameter("id"));
                resource = controller.getById(id);
            }
            req.setAttribute("resource",resource);
            req.getRequestDispatcher("/resourceForm.jsp").forward(req,resp);

        }
        else{
            req.setAttribute("resources", controller.getAll());
            req.getRequestDispatcher("resources.jsp").forward(req, resp);
        }
        req.setAttribute("resources", controller.getAll());
        req.getRequestDispatcher("resources.jsp").forward(req, resp);

    }

}
