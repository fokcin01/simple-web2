package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;
    public void save(User user) {
       userRepository.save(user);
        logger.info(user.toString() + "was successfully saved");
    }
    public void delete(int userId) {
        userRepository.deleteById(userId);
    }
}
