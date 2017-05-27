package com.atworksys.onelynk.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.atworksys.onelynk.data.UserHobbyRepository;
import com.atworksys.onelynk.data.UserPhoneRepository;
import com.atworksys.onelynk.data.UserRepository;
import com.atworksys.onelynk.data.UserRoleRepository;
import com.atworksys.onelynk.model.PhoneType;
import com.atworksys.onelynk.model.User;
import com.atworksys.onelynk.model.UserHobby;
import com.atworksys.onelynk.model.UserPhone;
import com.atworksys.onelynk.model.UserRole;

@Stateless
public class UserService {

    @Inject
    private Logger log;

    @Inject
    private UserRepository userRepository;
    
    @Inject
    private UserPhoneRepository userPhoneRepository;
    
    @Inject
    private UserHobbyRepository userHobbyRepository;
    
    @Inject
    private UserRoleRepository userRoleRepository;

    public void createUser(User user) throws Exception {
        log.info("Creating user " + user.getUsername());
        userRepository.createUser(user);
    }
    
    public List<User> getAllUsers(){
    	log.info("Retrieving all users ");
    	return userRepository.findAllUsers();
    }
    
    public List<UserPhone> getAllUserPhones(){
    	log.info("Retrieving all user phones ");
    	return userPhoneRepository.findAllUserPhones();
    }
    
    public List<UserRole> getAllUserRoles(){
    	log.info("Retrieving all user roles ");
    	return userRoleRepository.findAllUserRoles();
    }
    
    
    public void deleteUserPhone(Long userId, String type){
    	UserPhone userPhone = userPhoneRepository.selectUserPhoneByUserandType(userId, PhoneType.valueOf(type));
    	
    	log.info("Deleting phone of type: " + userPhone.getType().toString() + "for user: " + userPhone.getUser().getUsername());
    	userPhoneRepository.deleteUserPhone(userPhone);
    }
    
    public List<UserHobby> getUserHobbies(Long userId){
    	log.info("Retrieving all user hobbies ");
    	return userHobbyRepository.findUserHobbiesByUserId(userId);
    }
    
    public void updatePhone(Long userId, String type, String phone){
    	UserPhone userPhone = userPhoneRepository.selectUserPhoneByUserandType(userId, PhoneType.valueOf(type));
    	userPhone.setPhoneNumber(phone);
    	
    }
    
}
