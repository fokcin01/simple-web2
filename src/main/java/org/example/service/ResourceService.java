package org.example.service;

import client.to.ResourceTO;
import org.example.model.Resource;
import org.example.repository.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceService {
    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);

    @Autowired
    private ResourceRepository resourceRepository;

    public List<ResourceTO> getAll(int userId) {
        List<Resource> all = resourceRepository.findAllByUserId(userId);
        logger.info(all.toString());
        return all.stream()
                .filter(resource -> resource.getUser().getId() == userId)
                .map(Resource::toDto)
                .collect(Collectors.toList());
    }

    public Resource getById(int resourceId) {
        return resourceRepository.findById(resourceId).orElseThrow(() -> new RuntimeException("resource not found by id: " + resourceId));
    }

    public void delete(int resourceId) {
        resourceRepository.deleteById(resourceId);
    }

    public void save(Resource resource) {
        resourceRepository.save(resource);
    }

}
