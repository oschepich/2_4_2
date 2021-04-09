package com.oschepich.spring_security.service;

import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface RoleService<T>{

//    T getRoleById(Long id);

    Role getRoleByName(String name);

    public List<Role> getListRole();

}
