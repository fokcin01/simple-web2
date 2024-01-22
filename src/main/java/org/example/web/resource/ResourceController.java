package org.example.web.resource;

import org.example.model.Resource;
import org.example.repository.ResourceRepository;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ResourceController {
    private ResourceRepository resourceRepository;
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    public ResourceController(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }


    public List<Resource> getAll() {
        logger.info(resourceRepository.getAll().toString());
        return resourceRepository.getAll();
    }

    public Resource getById(int id) {

        return resourceRepository.getById(id);
    }

    public void  delete(int id) {
        resourceRepository.delete(id);
    }

    public void save(Resource resource) {
        resourceRepository.save(resource);
    }


}
