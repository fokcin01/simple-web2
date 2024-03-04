package org.example.controller;

import client.to.ResourceTO;
import client.to.UserTO;
import org.example.model.Resource;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/all")
    public List<UserTO> getAll() {
//        logger.info(resourceRepository.findAll().toString());
        List<User> all = userRepository.findAll();
//        logger.info(all.toString());
        return all.stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    public User getById(int id) {

        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("GetByIdException"));
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
