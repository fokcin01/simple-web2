package org.example.controller;

import client.to.ResourceTO;
import org.example.model.Resource;
import org.example.repository.ResourceRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class ResourceController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping("/resources/all")
    public List<ResourceTO> getAll() {
        logger.info(resourceRepository.findAll().toString());
        List<Resource> all = resourceRepository.findAll();
        logger.info(all.toString());
        return all.stream()
                .map(Resource::toDto)
                .collect(Collectors.toList());
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
