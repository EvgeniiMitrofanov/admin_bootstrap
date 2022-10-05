package ru.javamentor.java_pp_313.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import  ru.javamentor.java_pp_313.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUser(User user);
    User getUserById(Long id);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
    User getUserByName(String name);
}
