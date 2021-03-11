package com.oschepich.spring_security.service;

import com.oschepich.spring_security.dao.UserDao;
import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

       //  метод передачи всего списка user-ов
    @Override
    @Transactional
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    //  метод добавления одного user-а в списка
    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    //    метод обновления пользователя (пригодится на потом - пока не используется)
    @Override
    @Transactional
    public void updateUser(Long id, String name, String email) {
        userDao.updateUser(id, name, email);
    }

    @Override
    @Transactional
    public void creatUser(User user) {userDao.creatUser(user); }


    //  метод изменения одного user-а в списка
    @Override
    @Transactional
    public User show(Long id) {
        return (User) userDao.show(id);
    }

    //  метод удаления одного user-а из списка
    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }


    @Override
    public Role getRoleById(Long id) {
        return (Role) this.userDao.getRoleById(id);
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userDao.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("USER not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRole()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
    }


}
