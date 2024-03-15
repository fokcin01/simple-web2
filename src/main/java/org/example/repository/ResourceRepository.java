package org.example.repository;

import org.example.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Resource,Integer> {
    List<Resource> findAllByUserId(int userId);
}
