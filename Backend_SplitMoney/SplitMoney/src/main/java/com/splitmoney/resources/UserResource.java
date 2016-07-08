package com.splitmoney.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.splitmoney.beans.CreatedMessage;
import com.splitmoney.beans.User;
import com.splitmoney.service.SplitMoneyService;
import com.splitmoney.utils.DataNotFoundException;
import com.splitmoney.utils.ResourceNotCreatedException;

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserResource {

	SplitMoneyService service = new SplitMoneyService();
	
	@GET
	public Response getAllUsers(@PathParam("groupId") String groupId){
		
		System.out.println("Get All Users");
		Set<User> users = service.readAGroup(groupId).getUsers();
		GenericEntity<Set<User>> entity = new GenericEntity<Set<User>>(users){};
		return Response.ok(entity).build();
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createUserInGroup(@PathParam("groupId") String groupId, User user, @Context UriInfo uriInfo) throws URISyntaxException{
		User createdUser =  service.createUser(user);
		
		if(createdUser!=null)
		{
		service.addToGroup(groupId, createdUser.getUserId());
		URI uri= uriInfo.getAbsolutePathBuilder().path(createdUser.getUserId()).build();
		return Response.created(uri).entity(new CreatedMessage("User with ID \'" + createdUser.getUserId() +"\' is created")).build();
		
		}
	
	throw new ResourceNotCreatedException("User \'"+ user.getfName() + " "+ user.getlName() +"\' could not be created. User with this name already exists.");
	}
	
	
	@GET
	@Path("/{userId}")
	public Response getUserInAGroup(@PathParam("groupId") String groupId, @PathParam("userId") String userId ){
		
		User user = service.readUserFromAGroup(groupId, userId);
		
		if(user != null){
			return Response.ok(user).build();
		}
		
		throw new DataNotFoundException("User with Id " + userId + " doesn't exists in the group "+ groupId+ ".");
	}
	
	
	
	/*
	
	@GET
	public Response getAllUsers(@PathParam("groupId") String groupId){
		
		System.out.println("Get All Users");
		Set<User> users = service.readAGroup(groupId).getUsers();
		GenericEntity<Set<User>> entity = new GenericEntity<Set<User>>(users){};
		return Response.ok(entity).build();
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createUser(User user) throws URISyntaxException{
		service.createUser(user);
		System.out.println("Inside this");
		return Response.created(new URI("")).build();
		
	}*/
	
}
