package com.atworksys.onelynk.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.atworksys.onelynk.model.UserRole;

@ApplicationScoped
public class UserRoleRepository {

    @Inject
    private EntityManager entityManager;

    public void createUserRole(UserRole userRole) {
    	entityManager.persist(userRole);
    }
    
    public void updateUserRole(UserRole userRole) {
    	entityManager.merge(userRole);
    }
    
    public void deleteUserRole(UserRole userRole) {
    	entityManager.remove(userRole);
    }
    
    public UserRole findById(Long id) {
        return entityManager.find(UserRole.class, id);
    }
    
    public List<UserRole> findAllUserRoles() {
    	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<UserRole> criteria = cb.createQuery(UserRole.class);
    	Root<UserRole> userRole = criteria.from(UserRole.class);
    	criteria.select(userRole);
    	
    	return entityManager.createQuery(criteria).getResultList();
    }
}
