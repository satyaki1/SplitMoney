package com.splitmoney.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

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
import com.splitmoney.beans.Group;
import com.splitmoney.service.SplitMoneyService;
import com.splitmoney.utils.DataNotFoundException;
import com.splitmoney.utils.ResourceNotCreatedException;

@Path("/groups")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GroupResource {
	
	SplitMoneyService service = new SplitMoneyService();
	
	//read All Groups
	@GET
	public Response getAllGroups(@Context UriInfo uriInfo){
		List<Group> list = service.getAllGroups();
		Iterator<Group> iterator = list.iterator();
		while(iterator.hasNext()){
			Group group = iterator.next();
			String groupId = group.getGroupId();
			group.addLink(getLinkSelf(groupId, uriInfo), "self");
			group.addLink(getLinkUser(groupId, uriInfo), "users");
			group.addLink(getLinkExpenditure(groupId, uriInfo), "expenditures");
			//group.addLink(uriInfo.getAbsolutePathBuilder().path(group.getGroupId()).build().toString(), "self");
		}
		GenericEntity<List<Group>> entity = new GenericEntity<List<Group>>(list){};
		return Response.ok(entity).build();
	}
	
	//Create a group
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createGroup(Group group, @Context UriInfo info) throws URISyntaxException{
		Group createdGroup = service.createGroup(group);
		if(createdGroup!=null)
			{
			URI uri = info.getAbsolutePathBuilder().path(createdGroup.getGroupName()).build();
			return Response.created(uri).entity(new CreatedMessage("Group with ID \'" + createdGroup.getGroupId() +"\' is created")).build();
			}
		
		throw new ResourceNotCreatedException("Group \'"+ group.getGroupName() +"\' could not be created. Group with this name already exists.");
	}
	
	//Read A Group
	@GET
	@Path("/{groupId}")
	public Response readAGroup(@PathParam("groupId") String groupId, @Context UriInfo uriInfo ){
		Group group = service.readAGroup(groupId);
		if(group!=null){
			group.addLink(getLinkSelf(groupId, uriInfo), "self");
			group.addLink(getLinkUser(groupId, uriInfo), "users");
			group.addLink(getLinkExpenditure(groupId, uriInfo), "expenditures");
			System.out.println("Inside Group");
			return Response.ok(group).build();	
		}
		
		String message = "Group with id "+ groupId + " doesn't exists.";
		throw new DataNotFoundException(message);
	}

	private String getLinkSelf(String groupId, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(GroupResource.class).path(groupId).build().toString();
	}
	
	private String getLinkUser(String groupId, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(GroupResource.class)
				.path(GroupResource.class, "userResource")
				.resolveTemplate("groupId", groupId)
				.build()
				.toString();
	}
	
	private String getLinkExpenditure(String groupId, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder()
				.path(GroupResource.class)
				.path(GroupResource.class, "expenditureResource")
				.resolveTemplate("groupId", groupId)
				.build()
				.toString();
	}
	
	
	/*public UserResource userResource(){
		return new UserResource();
	}*/
	
	//User Level APIs
	@Path("/{groupId}/users")
	public UserResource userResource(){
		System.out.println("Moving to User resource");
		return new UserResource();
	}
	
	@Path("/{groupId}/expenditures")
	public ExpenditureResource expenditureResource(){
		return new ExpenditureResource();
	}
	
}
