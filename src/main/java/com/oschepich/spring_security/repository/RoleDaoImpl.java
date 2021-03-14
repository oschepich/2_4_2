package com.oschepich.spring_security.repository;

import com.oschepich.spring_security.model.Role;
import com.oschepich.spring_security.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public Role getRoleById(Long id) {
//        Role role = entityManager.find(Role.class, new Long(id));
//        entityManager.detach(role);
//        return role;
//    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select role from  Role role where role.role =: name",Role.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    @Override
    public List<Role> getListRole() {
        return entityManager.createQuery("from Role").getResultList();
    }
}