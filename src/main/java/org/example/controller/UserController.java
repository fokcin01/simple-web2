package org.example.controller;

import client.to.Constants;
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
import org.example.service.UserService;
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
    @Autowired
    private UserService userService;

    @GetMapping("/users/all")
    public List<UserTO> getAll() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(User::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/users/login")
    public String login(@RequestBody String userJson) {
        if (userJson == null || userJson.isEmpty()) {
            throw new IllegalArgumentException("can't login, user is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserTO user = objectMapper.readValue(userJson, UserTO.class);
            User userByUsername = userRepository.findUserByUsername(user.getUsername());
            if (userByUsername != null && userByUsername.getUserPassword().equals(user.getUserPassword())) {
                SecurityUtil.setAuthUserId(userByUsername.getId());
                return Constants.LOGIN_OK;
            } else {
                return Constants.LOGIN_FAILED;
            }
        } catch (JsonProcessingException e) {
            logger.error("error in login");
            e.printStackTrace();
            return Constants.LOGIN_FAILED;
        }
    }

    @PostMapping("/users/registration")
    public String registration(@RequestBody String userJson) {
        if (userJson == null || userJson.isEmpty()) {
            throw new IllegalArgumentException("can't registration, user is null");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            UserTO user = objectMapper.readValue(userJson, UserTO.class);
            User userByUserEmail = userRepository.findUserByUserEmail(user.getUserEmail());
            if (userByUserEmail != null) {
                return Constants.REGISTRATION_FAILED;
            } else {
                userService.save(User.toEntity(user));
                return Constants.REGISTRATION_OK;
            }
        } catch (JsonProcessingException e) {
            logger.error("error in registration");
            e.printStackTrace();
            return Constants.REGISTRATION_FAILED;
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
