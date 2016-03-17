/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;

/**
 *
 * @author raphapy
 */
@XmlRootElement
public class RespuestaPago {
    private String estado;
    private String mensaje;
    private Double credito;
    private List<DetalleCuotas> estadoCuenta;

    public RespuestaPago() {
    }
    
    public RespuestaPago(String estado, String mensaje) {
        this.estado = estado;
        this.mensaje = mensaje;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the credito
     */
    public Double getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(Double credito) {
        this.credito = credito;
    }

    /**
     * @return the estadoCuenta
     */
    public List<DetalleCuotas> getEstadoCuenta() {
        return estadoCuenta;
    }

    /**
     * @param estadoCuenta the estadoCuenta to set
     */
    public void setEstadoCuenta(List<DetalleCuotas> estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }
}
