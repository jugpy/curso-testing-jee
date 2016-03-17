/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.cuentas.boundary;

import org.jugpy.serviciosonline.cuentas.dto.CuentaACuotas;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jugpy.serviciosonline.exceptions.PagoNoAplicableException;
import org.jugpy.serviciosonline.exceptions.ServiceException;
import org.jugpy.serviciosonline.pagos.boundary.PagosService;
import org.jugpy.serviciosonline.pagos.dto.RespuestaPago;
import org.jugpy.serviciosonline.pagos.dto.SolicitudDePago;

/**
 *
 * @author raphapy
 */
@Path("cuentas")
@Produces(MediaType.APPLICATION_JSON)
public class CuentasResource {
    
    @Inject
    private CuentasService cuentasService;        
    @Inject
    private PagosService pagoService;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCuentaACuotas(CuentaACuotas cuentaACuotas) {
       cuentasService.crearCuentaACuotas(cuentaACuotas.getCuenta(),cuentaACuotas.getCuotas());
       return Response.ok().build();
    }
    
    @GET
    @Path("/{numeroDocumento}")
    public List<CuentaACuotas> obtenerCuentasACuota(@PathParam("numeroDocumento")String numeroDocumento) throws ServiceException {
        return cuentasService.obtenerCuentasConCuotas(numeroDocumento);
    }
    
    @POST
    @Path("/pagos")
    @Consumes(MediaType.APPLICATION_JSON)
    public RespuestaPago procesarPago(SolicitudDePago solicitudPago) throws PagoNoAplicableException {
        return pagoService.procesarPago(solicitudPago);
    }
}
