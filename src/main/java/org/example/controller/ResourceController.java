package org.example.controller;

import client.to.ResourceTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Resource;
import org.example.secutiry.SecurityUtil;
import org.example.service.ResourceService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/resources/delete")
    public void delete(@RequestBody String resourceTO) {
        logger.info("body: " + resourceTO);
        JsonNode id;
        try {
            JsonNode node = new ObjectMapper().readTree(resourceTO);
            if (node.has("id")) {
                id = node.get("id");
                service.delete(id.asInt(-1));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/resources/save")
    public void save(String jsonResource) {
        logger.info("json in save: " + jsonResource);
        Resource resource = null;
        //todo сделать с помощью ModelMapper или ObjectMapper из json Resource и пнуть дальше в сервис

        service.save(resource);
    }


}
