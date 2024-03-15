package org.example.controller;

import client.to.ResourceTO;
import org.example.model.Resource;
import org.example.secutiry.SecurityUtil;
import org.example.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ResourceController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService service;

    @GetMapping("/resources/all")
    public List<ResourceTO> getAll() {
        logger.info(service.getAll(SecurityUtil.getAuthUserId()).toString());
        logger.info("get all resources for user id {}", SecurityUtil.getAuthUserId());
        return service.getAll(SecurityUtil.getAuthUserId());
    }

    public Resource getById(int id) {
        return service.getById(id);
    }

    public void delete(int id) {
        service.delete(id);
    }

    public void save(Resource resource) {
        service.save(resource);
    }


}
