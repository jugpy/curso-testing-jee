/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.entity;

import java.util.Date;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;

/**
 *
 * @author raphapy
 */
public class PagoBuilder {
    Double montoPago;
    private Date fechaPago;
    private Date fechaProcesamientoPago;
    private Cuenta cuenta;
    
    PagoBuilder() {
    }
    
    public PagoBuilder montoPago(Double montoPago) {
        this.montoPago=montoPago;
        return this;
    }
    
    public PagoBuilder fechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
        return this;
    }
    
    public PagoBuilder fechaProcesamientoPago(Date fechaProcesamientoPago) {
        this.fechaProcesamientoPago = fechaProcesamientoPago;
        return this;
    }
    
    public PagoBuilder cuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        return this;
    }
    
    public Pago build() {
        return new Pago(this.montoPago,this.fechaPago, this.fechaProcesamientoPago, this.cuenta);
    }
}
