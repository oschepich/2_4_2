package com.oschepich.spring_security.dao;

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
        entityManager.merge(user);
    }

//    метод обновления пользователя (пригодится на потом - пока не используется)
    @Override
    public void updateUser(Long id, String name, String email) {
    User user = entityManager.find(User.class, id);
    user.setUsername(name);
    user.setEmail(email);
}


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
    @Override
    public User getUserByUsername(String username) {
        String hql = "from User u where u.username = :username";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("username", username);
        List<User> userList = query.getResultList();
        return userList.isEmpty() ? null : userList.get(0);
    }

    @Override
    public void creatUser(User user) {
        String hql = "from Role r where r.id = 1";
        TypedQuery<Role> query = entityManager.createQuery(hql, Role.class);
        List<Role> listRoles = query.getResultList();
        Role role = listRoles.get(0);
        user.getRole().add(role);
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }
    @Override
    public Role getRoleById(Long id) {
        Role role = entityManager.find(Role.class, new Long(id));
        entityManager.detach(role);
        return role;
    }
}