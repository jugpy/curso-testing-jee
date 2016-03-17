/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author raphapy
 */
@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException>{

    @Override
    public Response toResponse(ServiceException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ServiceExceptionMessage(
                        Response.Status.BAD_REQUEST.getStatusCode(), 
                        exception.getMessage())).build();
    }
}
