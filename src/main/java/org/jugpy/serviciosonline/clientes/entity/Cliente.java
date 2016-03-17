/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.clientes.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raphapy
 */
@XmlRootElement
@Entity
@Table(name = "cliente", uniqueConstraints = {@UniqueConstraint(columnNames = {"numero_documento"}, name = "uq_numero_documento")})
@NamedQueries(@NamedQuery(name = "buscarPorNumeroDocumento", 
                          query = "SELECT c FROM Cliente c WHERE c.numeroDocumento=:nro_doc"
                         )
             )
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idCliente);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        return true;
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }
    
    public static class Builder {
        private Long idCliente;
        private String numeroDocumento;
        private String nombre;
        private String apellido;
    
        Builder() {
        }
        
        public Builder idCliente(Long idCliente) {
            this.idCliente = idCliente;
            return this;
        }
        
        public Builder numeroDocumento(String numeroDocumento) {
            this.numeroDocumento = numeroDocumento;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder apellido(String apellido) {
            this.apellido = apellido;
            return this;
        }

        public Cliente build() {
            Cliente c = new Cliente();
            c.setNumeroDocumento(this.numeroDocumento);
            c.setNombre(this.nombre);
            c.setApellido(this.apellido);
            c.setIdCliente(this.idCliente);
            return c;
        }
    }
}
