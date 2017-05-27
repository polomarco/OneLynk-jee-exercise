package com.atworksys.onelynk.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.atworksys.onelynk.model.UserHobby;

@ApplicationScoped
public class UserHobbyRepository {

    @Inject
    private EntityManager entityManager;

    public void createUserHobby(UserHobby userHobby) {
    	entityManager.persist(userHobby);
    }
    
    public void updateHobby(UserHobby userHobby) {
    	entityManager.merge(userHobby);
    }
    
    public void deleteHobby(UserHobby userHobby) {
    	entityManager.remove(userHobby);
    }
    
    public UserHobby findByUserHobbyId(Long id) {
        return entityManager.find(UserHobby.class, id);
    }
    
    public List<UserHobby> findAllUserHobbies() {
    	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<UserHobby> criteria = cb.createQuery(UserHobby.class);
    	Root<UserHobby> userHobby = criteria.from(UserHobby.class);
    	criteria.select(userHobby);
    	
    	return entityManager.createQuery(criteria).getResultList();
    }
    
    public List<UserHobby> findUserHobbiesByUserId(Long userId) {
    	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<UserHobby> criteria = cb.createQuery(UserHobby.class);
    	Root<UserHobby> userHobby = criteria.from(UserHobby.class);
    	criteria.select(userHobby).where(cb.equal(userHobby.get("user"), userId));
    	
    	return entityManager.createQuery(criteria).getResultList();
    }
}
