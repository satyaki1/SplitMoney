package com.splitmoney.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.splitmoney.beans.ErrorMessage;
import com.splitmoney.utils.ResourceNotCreatedException;

@Provider
public class ResourceNotCreatedExceptionMapper implements ExceptionMapper<ResourceNotCreatedException> {

	@Override
	public Response toResponse(ResourceNotCreatedException ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "Resource could not be created. This might have happenned for a number of reasons.");
		return Response.serverError().entity(errorMessage).build();
	}

}
