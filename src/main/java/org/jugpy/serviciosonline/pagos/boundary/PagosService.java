/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.boundary;

import java.util.Date;
import org.jugpy.serviciosonline.pagos.dto.RespuestaPago;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Validator;
import org.jugpy.serviciosonline.cuentas.boundary.CuentasService;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;
import org.jugpy.serviciosonline.exceptions.PagoNoAplicableException;
import org.jugpy.serviciosonline.pagos.control.PagosControl;
import org.jugpy.serviciosonline.pagos.dto.ResultadoDeAmortizacion;
import org.jugpy.serviciosonline.pagos.dto.SolicitudDePago;
import org.jugpy.serviciosonline.pagos.entity.Pago;

/**
 *
 * @author raphapy
 */
@Stateless
public class PagosService {
    
    @PersistenceContext(unitName = "PagosOnlinePU")
    EntityManager entityManager;
    @Inject
    Validator validator;
    @Inject
    CuentasService cuentasServices;
    @Inject
    PagosControl pagosController;
    
    /**
     * Aplica un pago a una cuenta de cliente dada.
     * 
     * @param solicitudPago
     * @return la cuenta pagada actualizada
     * @throws org.jugpy.serviciosonline.exceptions.PagoNoAplicableException
     */
    public RespuestaPago procesarPago(SolicitudDePago solicitudPago) throws PagoNoAplicableException {
        if(solicitudPago==null) {
            throw new PagoNoAplicableException("No se recibió la solicitud de pago.");
        } else {
            validator.validate(solicitudPago);
        }
        
        Cuenta cuenta = cuentasServices.obtenerCuenta(solicitudPago.getIdCuenta());
        if(cuenta==null) {
            throw new PagoNoAplicableException("La cuenta no existe.");
        }
        
        if(cuenta.getFechaCancelacion()!=null) {
            throw new PagoNoAplicableException("La cuenta ya fue cancelada.");
        } else {
            List<DetalleCuotas> cuotas = cuentasServices.buscarCuotasDeLaCuenta(cuenta.getIdCuenta());
            ResultadoDeAmortizacion amortizacion = pagosController.amortizarCuotas(solicitudPago.getMontoPago(), cuotas);
            
            Double deudaActual = amortizacion.getEstadoCuenta().stream()
                  .mapToDouble(c->c.getSaldoCuota())
                  .sum();
            
            cuenta.setDeudaActual(deudaActual);
            cuenta.setCredito(amortizacion.getCredito()+cuenta.getCredito());
            
            if(deudaActual==0d) {
                cuenta.setFechaCancelacion(new Date());
            }
            
            cuentasServices.actualizarCuenta(cuenta);
            cuotas = amortizacion.getEstadoCuenta();
            
            cuotas.forEach(c->{
                                cuentasServices.actualizarCuota(c);
                           });
            
            Pago pago = Pago.getBuilder()
                            .cuenta(cuenta)
                            .fechaPago(solicitudPago.getFechaSolicitud())
                            .fechaProcesamientoPago(new Date())
                            .montoPago(solicitudPago.getMontoPago())
                            .build();
            
            registrarPagoCliente(pago);
            
            RespuestaPago respuestaPago = new RespuestaPago("OK", "Transacción realizada con éxito.");
            respuestaPago.setCredito(cuenta.getCredito());
            respuestaPago.setEstadoCuenta(cuotas);
            return respuestaPago;
        }
    }
    
    public void registrarPagoCliente(Pago pago) {
        entityManager.persist(pago);
    }
}
