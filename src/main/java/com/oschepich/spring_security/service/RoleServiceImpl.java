package com.oschepich.spring_security.service;

import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import com.oschepich.spring_security.repository.RoleDao;
import com.oschepich.spring_security.repository.UserDao;
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
public class RoleServiceImpl implements RoleService {

        private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

//    @Override
//    public Role getRoleById(Long id) {
//        return (Role) this.roleDao.getRoleById(id);
//    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    @Transactional
    public List<Role> getListRole() {
        return roleDao.getListRole();
    }


}
