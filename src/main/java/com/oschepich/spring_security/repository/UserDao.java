package com.oschepich.spring_security.repository;

import com.oschepich.spring_security.model.User;

import java.util.List;

public interface UserDao <T>{
    List<T> getAllUser();

    void saveUser(User user);

//    void updateUser(Long id, String name, String email);

    T show(Long id);

    void deleteUser(Long id);

    T getUserByUsername(String username);

}
