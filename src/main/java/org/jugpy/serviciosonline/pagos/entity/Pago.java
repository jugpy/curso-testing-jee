/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;

/**
 *
 * @author raphapy
 */
@Entity
@Table(name = "pago")
public class Pago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;
    @Column(name = "monto_pago")
    private Double montoPago;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_pago")
    private Date fechaPago;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_procesamiento_pago")
    private Date fechaProcesamientoPago;
    @ManyToOne
    @JoinColumn(name = "cuenta")
    private Cuenta cuenta;
    
    public Pago() {
    }
    
    Pago(Double montoPago, Date fechaPago, Date fechaProcesamientoPago, Cuenta cuenta) {
        this.montoPago = montoPago;
        this.fechaPago = fechaPago;
        this.fechaProcesamientoPago = fechaProcesamientoPago;
        this.cuenta = cuenta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.idPago);
        hash = 67 * hash + Objects.hashCode(this.montoPago);
        hash = 67 * hash + Objects.hashCode(this.fechaPago);
        hash = 67 * hash + Objects.hashCode(this.fechaProcesamientoPago);
        hash = 67 * hash + Objects.hashCode(this.cuenta);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pago other = (Pago) obj;
        if (!Objects.equals(this.idPago, other.idPago)) {
            return false;
        }
        if (!Objects.equals(this.montoPago, other.montoPago)) {
            return false;
        }
        if (!Objects.equals(this.fechaPago, other.fechaPago)) {
            return false;
        }
        if (!Objects.equals(this.fechaProcesamientoPago, other.fechaProcesamientoPago)) {
            return false;
        }
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        return true;
    }

    /**
     * @return the idPago
     */
    public Long getIdPago() {
        return idPago;
    }

    /**
     * @param idPago the idPago to set
     */
    public void setIdPago(Long idPago) {
        this.idPago = idPago;
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
     * @return the fechaPago
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * @param fechaPago the fechaPago to set
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * @return the fechaProcesamientoPago
     */
    public Date getFechaProcesamientoPago() {
        return fechaProcesamientoPago;
    }

    /**
     * @param fechaProcesamientoPago the fechaProcesamientoPago to set
     */
    public void setFechaProcesamientoPago(Date fechaProcesamientoPago) {
        this.fechaProcesamientoPago = fechaProcesamientoPago;
    }

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
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static class Builder {
        private Long idPago;
        Double montoPago;
        private Date fechaPago;
        private Date fechaProcesamientoPago;
        private Cuenta cuenta;

        Builder() {
        }
        
        public Builder idPago(Long idPago) {
            this.idPago=idPago;
            return this;
        }
        
        public Builder montoPago(Double montoPago) {
            this.montoPago=montoPago;
            return this;
        }

        public Builder fechaPago(Date fechaPago) {
            this.fechaPago = fechaPago;
            return this;
        }

        public Builder fechaProcesamientoPago(Date fechaProcesamientoPago) {
            this.fechaProcesamientoPago = fechaProcesamientoPago;
            return this;
        }

        public Builder cuenta(Cuenta cuenta) {
            this.cuenta = cuenta;
            return this;
        }

        public Pago build() {
            Pago p = new Pago();
            p.setIdPago(this.idPago);
            p.setCuenta(this.cuenta);
            p.setMontoPago(this.montoPago);
            p.setFechaPago(this.fechaPago);
            p.setFechaProcesamientoPago(this.fechaProcesamientoPago);
            return p;
        }
    }
}
