package org.example.model;

import client.to.UserTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.modelmapper.ModelMapper;


@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    private String username;
    private String userPassword;
    @Column(unique = true, nullable = false)
    private String userEmail;

    public User(Integer id, String username,String userPassword,String userEmail) {
        super(id);
        this.username = username;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
    public User() {

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

    public String getUserEmail() {
        return userEmail;
    }

    public void setEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public static User toEntity(UserTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, User.class);
    }

    public static UserTO toDto(User entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, UserTO.class);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
