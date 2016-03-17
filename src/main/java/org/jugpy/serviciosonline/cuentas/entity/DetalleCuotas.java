/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.cuentas.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.jugpy.serviciosonline.utils.Fechas;

/**
 *
 * @author raphapy
 */
@Entity
@Table(name = "detalle_cuotas")
@XmlRootElement
@NamedQueries(@NamedQuery(name = "buscarCuotasDeLaCuenta",query = "SELECT cuo FROM DetalleCuotas cuo WHERE cuo.cuenta.idCuenta=:id_cuenta"))
public class DetalleCuotas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_cuotas")
    private Long idDetalleCuotas;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cuenta", referencedColumnName = "id_cuenta", nullable = false)
    private Cuenta cuenta;
    @Column(name = "nro_cuota")
    private Integer nroCuota;
    @Column(name = "monto_cuota")
    private Double montoCuota;
    @Column(name = "saldo_cuota")
    private Double saldoCuota;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_cancelacion")
    private Date fechaCancelacion;

    @PrePersist
    public void prePersistCallback() {
        this.fechaAlta = new Date();
        this.fechaCancelacion = null;
        this.fechaVencimiento = 
                Fechas.incrementarMes(this.fechaAlta, this.nroCuota);
    }
    
    /**
     * @return the idDetalleCuotas
     */
    public Long getIdDetalleCuotas() {
        return idDetalleCuotas;
    }

    /**
     * @param idDetalleCuotas the idDetalleCuotas to set
     */
    public void setIdDetalleCuotas(Long idDetalleCuotas) {
        this.idDetalleCuotas = idDetalleCuotas;
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

    /**
     * @return the nroCuota
     */
    public Integer getNroCuota() {
        return nroCuota;
    }

    /**
     * @param nroCuota the nroCuota to set
     */
    public void setNroCuota(Integer nroCuota) {
        this.nroCuota = nroCuota;
    }

    /**
     * @return the montoCuota
     */
    public Double getMontoCuota() {
        return montoCuota;
    }

    /**
     * @param montoCuota the montoCuota to set
     */
    public void setMontoCuota(Double montoCuota) {
        this.montoCuota = montoCuota;
    }

    /**
     * @return the saldoCuota
     */
    public Double getSaldoCuota() {
        return saldoCuota;
    }

    /**
     * @param saldoCuota the saldoCuota to set
     */
    public void setSaldoCuota(Double saldoCuota) {
        this.saldoCuota = saldoCuota;
    }

    /**
     * @return the fechaAlta
     */
    public Date getFechaAlta() {
        return fechaAlta;
    }

    /**
     * @param fechaAlta the fechaAlta to set
     */
    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * @return the fechaCancelacion
     */
    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    /**
     * @param fechaCancelacion the fechaCancelacion to set
     */
    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idDetalleCuotas);
        hash = 29 * hash + Objects.hashCode(this.cuenta);
        hash = 29 * hash + Objects.hashCode(this.nroCuota);
        hash = 29 * hash + Objects.hashCode(this.montoCuota);
        hash = 29 * hash + Objects.hashCode(this.saldoCuota);
        hash = 29 * hash + Objects.hashCode(this.fechaAlta);
        hash = 29 * hash + Objects.hashCode(this.fechaVencimiento);
        hash = 29 * hash + Objects.hashCode(this.fechaCancelacion);
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
        final DetalleCuotas other = (DetalleCuotas) obj;
        if (!Objects.equals(this.idDetalleCuotas, other.idDetalleCuotas)) {
            return false;
        }
        if (!Objects.equals(this.cuenta, other.cuenta)) {
            return false;
        }
        if (!Objects.equals(this.nroCuota, other.nroCuota)) {
            return false;
        }
        if (!Objects.equals(this.montoCuota, other.montoCuota)) {
            return false;
        }
        if (!Objects.equals(this.saldoCuota, other.saldoCuota)) {
            return false;
        }
        if (!Objects.equals(this.fechaAlta, other.fechaAlta)) {
            return false;
        }
        if (!Objects.equals(this.fechaVencimiento, other.fechaVencimiento)) {
            return false;
        }
        if (!Objects.equals(this.fechaCancelacion, other.fechaCancelacion)) {
            return false;
        }
        return true;
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static class Builder {
        private Long idDetalleCuotas;
        private Cuenta cuenta;
        private Integer nroCuota;
        private Double montoCuota;
        private Double saldoCuota;
        private Date fechaAlta;
        private Date fechaVencimiento;
        private Date fechaCancelacion;

        Builder() {
        }

        public Builder idDetalleCuotas(Long idDetalleCuotas) {
            this.idDetalleCuotas=idDetalleCuotas;
            return this;
        }
        
        public Builder cuenta(Cuenta cuenta) {
            this.cuenta = cuenta;
            return this;
        }

        public Builder nroCuota(Integer nroCuota) {
            this.nroCuota = nroCuota;
            return this;
        }

        public Builder montoCuota(Double montoCuota) {
            this.montoCuota = montoCuota;
            return this;
        }

        public Builder saldoCuota(Double saldoCuota) {
            this.saldoCuota = saldoCuota;
            return this;
        }

        public Builder fechaAlta(Date fechaAlta) {
            this.fechaAlta = fechaAlta;
            return this;
        }

        public Builder fechaVencimiento(Date fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
            return this;
        }

        public Builder fechaCancelacion(Date fechaCancelacion) {
            this.fechaCancelacion = fechaCancelacion;
            return this;
        }

        public DetalleCuotas build() {
            DetalleCuotas det = new DetalleCuotas();
            det.setIdDetalleCuotas(this.idDetalleCuotas);
            det.setNroCuota(this.nroCuota);
            det.setCuenta(this.cuenta);
            det.setMontoCuota(this.montoCuota);
            if(this.saldoCuota==null) {
                det.setSaldoCuota(this.montoCuota);
            } else {           
                det.setSaldoCuota(this.saldoCuota);
            }
            det.setFechaAlta(this.fechaAlta);
            det.setFechaVencimiento(this.fechaVencimiento);
            det.setFechaCancelacion(this.fechaCancelacion);
            return det;
        }
    }
}
