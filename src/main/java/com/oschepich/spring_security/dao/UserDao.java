package com.oschepich.spring_security.dao;

import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;

import java.util.List;

public interface UserDao <T>{
    List<T> getAllUser();

    void saveUser(User user);

    void updateUser(Long id, String name, String email);

    void creatUser(User user);

    T show(Long id);

    void deleteUser(Long id);

    T getUserByUsername(String username);

    T getRoleById(Long id);
}
