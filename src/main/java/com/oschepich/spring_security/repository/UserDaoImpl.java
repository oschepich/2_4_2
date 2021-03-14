package com.oschepich.spring_security.repository;

import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

// метод передачи всего списка пользователей
    @Override
    public List<User> getAllUser() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        List<User> list = query.getResultList();
        return list;
    }
// метод обновления и добавления пользователя
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
    }

////    метод обновления пользователя (возможно пригодится на потом - пока не используется)
//    @Override
//    public void updateUser(Long id, String name, String email) {
//    User user = entityManager.find(User.class, id);
//    user.setUsername(name);
//    user.setEmail(email);
//}

    // метод нахождения одного пользователя
    @Override
    public User show(Long id) {
        return entityManager.find(User.class, id);
    }

// метод удаления пользователя
    @Override
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
// метод идентификации юзера по имени
    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = entityManager
                .createQuery("from User user where user.username = :username", User.class);
        query.setParameter("username", username);
        List<User> userList = query.getResultList();
        return userList.isEmpty() ? null : userList.get(0);
    }

}