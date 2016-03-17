/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.cuentas.dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;

/**
 *
 * @author raphapy
 */
@XmlRootElement
public class CuentaACuotas implements Serializable {
    @NotNull    
    private Cuenta cuenta;
    @NotNull
    @Size(min = 1)
    private List<DetalleCuotas> cuotas;

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the cuotas
     */
    public List<DetalleCuotas> getCuotas() {
        return cuotas;
    }

    /**
     * @param cuotas the cuotas to set
     */
    public void setCuotas(List<DetalleCuotas> cuotas) {
        this.cuotas = cuotas;
    }
}
