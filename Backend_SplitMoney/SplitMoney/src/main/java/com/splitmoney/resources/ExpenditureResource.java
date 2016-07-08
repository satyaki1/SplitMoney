package com.splitmoney.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import com.splitmoney.beans.Expenditure;
import com.splitmoney.service.SplitMoneyService;
import com.splitmoney.utils.ResourceNotCreatedException;

@Path("/")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ExpenditureResource {

	SplitMoneyService service = new SplitMoneyService();
	
	@GET
	public Response getAllExpendituresInGroup(@PathParam("groupId") String groupId){
		List<Expenditure> list = service.getAllExpenditures(groupId);
		GenericEntity<List<Expenditure>> entity = new GenericEntity<List<Expenditure>>(list){};
		return Response.ok(entity).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createExpenditureInGroup(@PathParam("groupId") String groupId, Expenditure expenditure, @Context UriInfo uriInfo ) throws URISyntaxException{
		Expenditure createdExpenditure = service.createExpenditure(expenditure);
		
		if(createdExpenditure!=null){
			URI uri= uriInfo.getAbsolutePathBuilder().path(createdExpenditure.getExpenditureId()).build();
			return Response.created(uri).entity(new CreatedMessage("Expenditure with ID \'" + expenditure.getExpenditureId() +"\' is created")).build();
		}
		
		throw new ResourceNotCreatedException("Expenditure \'"+ expenditure.getSpendingName() + "\' could not be created. ");
	}
	
	@GET
	@Path("/{userId}")
	public Response getAllExpendituresInGroupUser(@PathParam("groupId") String groupId, @PathParam("userId") String userId){
		List<Expenditure> list = service.getAllExpendituresUser(groupId, userId);
		GenericEntity<List<Expenditure>> entity = new GenericEntity<List<Expenditure>>(list){};
		return Response.ok(entity).build();
	}
	
	
}
