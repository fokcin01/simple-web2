package org.example.controller;

import org.example.model.Resource;
import org.example.repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ResourceController {
    @Autowired
    private ResourceRepository resourceRepository;
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);


    @GetMapping("/resources/all")
    public List<Resource> getAll() {
        logger.info(resourceRepository.findAll().toString());
        return resourceRepository.findAll();
    }

    public Resource getById(int id) {

        return resourceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("GetByIdException"));
    }

    public void delete(int id) {
        resourceRepository.deleteById(id);
    }

    public void save(Resource resource) {
        resourceRepository.save(resource);
    }


}
