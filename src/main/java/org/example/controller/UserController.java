package org.example.controller;

import client.to.ResourceTO;
import client.to.UserTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Resource;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.secutiry.SecurityUtil;
import org.example.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/all")
    public List<UserTO> getAll() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/login")
    public void login(@RequestBody String userJson) {
        if (userJson == null || userJson.isEmpty()) {
            throw new IllegalArgumentException("can't login, user is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserTO user = objectMapper.readValue(userJson, UserTO.class);
            int id = user.getId();
            SecurityUtil.setAuthUserId(id);
            logger.info("set auth user id: {}", id);
        } catch (JsonProcessingException e) {
            logger.error("error in login");
            e.printStackTrace();
        }
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
