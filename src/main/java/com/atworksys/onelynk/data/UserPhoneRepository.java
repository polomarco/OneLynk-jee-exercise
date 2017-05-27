package com.atworksys.onelynk.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.atworksys.onelynk.model.PhoneType;
import com.atworksys.onelynk.model.UserPhone;

@ApplicationScoped
public class UserPhoneRepository {

    @Inject
    private EntityManager entityManager;

    public void createUserPhone(UserPhone userPhone) {
    	entityManager.persist(userPhone);
    }
    
    public void updateUserPhone(UserPhone userPhone) {
    	entityManager.merge(userPhone);
    }
    
    public void deleteUserPhone(UserPhone userPhone) {
    	entityManager.remove(userPhone);
    }
    
    public UserPhone selectUserPhoneByUserandType(Long userId, PhoneType type) {
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<UserPhone> criteria = cb.createQuery(UserPhone.class);
    	Root<UserPhone> userPhone = criteria.from(UserPhone.class);
    	criteria.select(userPhone).where(cb.equal(userPhone.get("user"), userId), cb.equal(userPhone.get("type"),type));
    	
    	return entityManager.createQuery(criteria).getSingleResult();
    }
    
    public UserPhone findById(Long id) {
        return entityManager.find(UserPhone.class, id);
    }
    
    public List<UserPhone> findAllUserPhones() {
    	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<UserPhone> criteria = cb.createQuery(UserPhone.class);
    	Root<UserPhone> userPhone = criteria.from(UserPhone.class);
    	criteria.select(userPhone);
    	
    	return entityManager.createQuery(criteria).getResultList();
    }
}
