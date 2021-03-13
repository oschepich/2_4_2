package com.oschepich.spring_security.repository;

import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;

import java.util.List;

public interface RoleDao<T>{

    T getRoleById(Long id);


    Role getRoleByName(String name) ;


    List<Role> getListRole();
}
