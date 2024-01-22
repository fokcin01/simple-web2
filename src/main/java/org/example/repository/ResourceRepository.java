package org.example.repository;

import org.example.model.Resource;

import java.util.List;

public interface ResourceRepository {
       List<Resource> getAll();
       Resource getById(int id);
       void delete(int id);
       void save(Resource resource);
}
