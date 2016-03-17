/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.dto;

import java.io.Serializable;
import java.util.List;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;

/**
 *
 * @author raphapy
 */
public class ResultadoDeAmortizacion implements Serializable {
    
    private Double credito;
    private List<DetalleCuotas> estadoCuenta;

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
