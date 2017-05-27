package com.atworksys.onelynk.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atworksys.onelynk.model.User;
import com.atworksys.onelynk.model.UserHobby;
import com.atworksys.onelynk.model.UserPhone;
import com.atworksys.onelynk.model.UserRole;
import com.atworksys.onelynk.service.UserService;

@Path("/users")
@RequestScoped
public class UserRestService {

	@Inject
	UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@GET
	@Path("/phones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserPhone> getUserPhones() {
		return userService.getAllUserPhones();
	}

	@GET
	@Path("/roles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserRole> getUserRoles() {
		return userService.getAllUserRoles();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user){

		Response.ResponseBuilder builder = null;

		try {
			userService.createUser(user);
			builder = Response.ok();
		} catch (Exception e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}

		return builder.build();    	
	}

	@GET
	@Path("/{id:[0-9][0-9]*}/hobbies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserHobby> getHobbies(@PathParam("id") Long userId){
		return userService.getUserHobbies(userId);

	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}/phone/{type}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deletePhone(@PathParam("id") Long userId, @PathParam("type") String type){
		userService.deleteUserPhone(userId, type.toUpperCase());
	}
	
	@PUT
	@Path("/{id:[0-9][0-9]*}/phone/{type}/{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public void updatePhone(@PathParam("id") Long userId, @PathParam("type") String type, @PathParam("phone") String phone){
		userService.updatePhone(userId, type.toUpperCase(), phone);
		
	}

}
