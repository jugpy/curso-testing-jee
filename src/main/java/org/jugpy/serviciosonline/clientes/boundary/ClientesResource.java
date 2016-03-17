/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.clientes.boundary;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jugpy.serviciosonline.clientes.entity.Cliente;

/**
 *
 * @author raphapy
 */
@Path("clientes")
public class ClientesResource {
    
    @Inject
    private ClientesService clientesServices;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCliente(Cliente cliente) {
        clientesServices.crearCliente(cliente);
        return Response.ok().build();
    }
}
