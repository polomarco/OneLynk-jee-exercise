package com.atworksys.onelynk.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.atworksys.onelynk.model.User;

@ApplicationScoped
public class UserRepository {

    @Inject
    private EntityManager entityManager;

    public void createUser(User user) {
    	entityManager.persist(user);
    }
    
    public void updateUser(User user) {
    	entityManager.merge(user);
    }
    
    public void deleteUser(User user) {
    	entityManager.remove(user);
    }
    
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
    
    public List<User> findAllUsers() {
    	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<User> criteria = cb.createQuery(User.class);
    	Root<User> user = criteria.from(User.class);
    	criteria.select(user);
    	
    	return entityManager.createQuery(criteria).getResultList();
    }
}
