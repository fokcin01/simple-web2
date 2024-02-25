package org.example.repository;

import org.example.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResourceRepository extends JpaRepository<Resource,Integer> {

}
