package com.splitmoney.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.splitmoney.beans.ErrorMessage;
import com.splitmoney.utils.DataNotFoundException;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "Resource Not Found");
		return Response.status(404)
				.entity(errorMessage)
				.build();
	}

}
