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
import org.jugpy.serviciosonline.clientes.entity.Cliente;

/**
 *
 * @author raphapy
 */
@XmlRootElement
@Table(name = "cuenta")
@Entity
@NamedQueries(@NamedQuery(name = "buscarCuentasDelCliente", 
                          query = "SELECT c FROM Cuenta c WHERE c.cliente.idCliente=:id_cliente"))
public class Cuenta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuenta")
    private Long idCuenta;
    @Column(name = "deuda_total")
    private Double deudaTotal;
    @Column(name = "deuda_actual")
    private Double deudaActual;
    @Column(name = "credito")
    private Double credito;
    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "fecha_cancelacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCancelacion;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    
    @PrePersist
    public void prePersistCallback(){
        this.fechaAlta = new Date();
        this.fechaCancelacion = null;
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
     * @return the deudaTotal
     */
    public Double getDeudaTotal() {
        return deudaTotal;
    }

    /**
     * @param deudaTotal the deudaTotal to set
     */
    public void setDeudaTotal(Double deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    /**
     * @return the deudaActual
     */
    public Double getDeudaActual() {
        return deudaActual;
    }

    /**
     * @param deudaActual the deudaActual to set
     */
    public void setDeudaActual(Double deudaActual) {
        this.deudaActual = deudaActual;
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

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.getIdCuenta());
        hash = 59 * hash + Objects.hashCode(this.getDeudaTotal());
        hash = 59 * hash + Objects.hashCode(this.getDeudaActual());
        hash = 59 * hash + Objects.hashCode(this.getCredito());
        hash = 59 * hash + Objects.hashCode(this.getFechaAlta());
        hash = 59 * hash + Objects.hashCode(this.getFechaCancelacion());
        hash = 59 * hash + Objects.hashCode(this.getCliente());
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
        final Cuenta other = (Cuenta) obj;
        if (!Objects.equals(this.idCuenta, other.idCuenta)) {
            return false;
        }
        if (!Objects.equals(this.deudaTotal, other.deudaTotal)) {
            return false;
        }
        if (!Objects.equals(this.deudaActual, other.deudaActual)) {
            return false;
        }
        if (!Objects.equals(this.credito, other.credito)) {
            return false;
        }
        if (!Objects.equals(this.fechaAlta, other.fechaAlta)) {
            return false;
        }
        if (!Objects.equals(this.fechaCancelacion, other.fechaCancelacion)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static class Builder {

        private Long idCuenta;
        private Double deudaTotal;
        private Double deudaActual;
        private Double credito;
        private Date fechaAlta;
        private Date fechaCancelacion;
        private Cliente cliente;

        Builder() {

        }

        public Builder idCuenta(Long idCuenta) {
            this.idCuenta = idCuenta;
            return this;
        }

        public Builder deudaTotal(Double deudaTotal) {
            this.deudaTotal = deudaTotal;
            return this;
        }

        public Builder deudaActual(Double deudaActual) {
            this.deudaActual = deudaActual;
            return this;
        }

        public Builder credito(Double credito) {
            this.credito = credito;
            return this;
        }

        public Builder fechaAlta(Date fechaAlta) {
            this.fechaAlta = fechaAlta;
            return this;
        }

        public Builder fechaCancelacion(Date fechaCancelacion) {
            this.fechaCancelacion = fechaCancelacion;
            return this;
        }

        public Builder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Cuenta build() {
            Cuenta c = new Cuenta();
            c.setIdCuenta(this.idCuenta);
            c.setCliente(this.cliente);
            c.setDeudaActual(this.deudaActual);
            c.setDeudaTotal(this.deudaTotal);
            c.setCredito(this.credito);
            c.setFechaAlta(this.fechaAlta);
            c.setFechaCancelacion(this.fechaCancelacion);
            return c;
        }
    }
}
