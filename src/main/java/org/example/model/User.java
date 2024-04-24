package org.example.model;

import client.to.UserTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.modelmapper.ModelMapper;


@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    private String username;
    private String userPassword;

    public User(Integer id, String username,String userPassword) {
        super(id);
        this.username = username;
        this.userPassword = userPassword;
    }
    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public static User toEntity(UserTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, User.class);
    }

    public static UserTO toDto(User entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, UserTO.class);
    }
}
