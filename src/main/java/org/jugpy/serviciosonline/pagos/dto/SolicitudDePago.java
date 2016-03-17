/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.dto;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raphapy
 */
@XmlRootElement
public class SolicitudDePago {
    @NotNull(message = "Se requiere el identificador del cliente.")
    private Long idCliente;
    @NotNull(message = "Se requiere la cuenta a pagar.")
    private Long idCuenta;
    @NotNull(message = "Se requiere el monto a pagar.")
    private Double montoPago;
    @NotNull(message = "Se requiere la fecha de la solicitud de pago.")
    private Date fechaSolicitud;

    @PostConstruct
    public void postConstructCallback() {
        this.fechaSolicitud = new Date();
    }
    /**
     * @return the idCliente
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idCuenta
     */
    public Long getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the montoPago
     */
    public Double getMontoPago() {
        return montoPago;
    }

    /**
     * @param montoPago the montoPago to set
     */
    public void setMontoPago(Double montoPago) {
        this.montoPago = montoPago;
    }

    /**
     * @return the fechaSolicitud
     */
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    /**
     * @param fechaSolicitud the fechaSolicitud to set
     */
    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
}
