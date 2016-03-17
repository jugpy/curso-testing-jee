/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.clientes.boundary;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jugpy.serviciosonline.clientes.entity.Cliente;

/**
 *
 * @author raphapy
 */
@Stateless
public class ClientesService {
    private static final Logger LOGGER = Logger.getLogger(ClientesService.class.getName());
    
    @PersistenceContext(unitName = "PagosOnlinePU")
    EntityManager em;
    
    public Cliente obtenerCliente(String numeroDocumento) {
        TypedQuery<Cliente> queryBuscarCliente = em.createNamedQuery("buscarPorNumeroDocumento", Cliente.class);
        try {
            queryBuscarCliente.setParameter("nro_doc", numeroDocumento);
            return queryBuscarCliente.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.log(Level.WARNING, String.format("Excepción al intentar obtener cliente con documento nro %s.", numeroDocumento),e);
            return null;
        }
    }
    
    public void crearCliente(Cliente cliente) {
        em.persist(cliente);
    }
}
