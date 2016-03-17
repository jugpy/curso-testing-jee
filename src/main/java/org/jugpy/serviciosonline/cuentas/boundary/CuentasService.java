/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.cuentas.boundary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jugpy.serviciosonline.exceptions.ServiceException;
import org.jugpy.serviciosonline.clientes.boundary.ClientesService;
import org.jugpy.serviciosonline.clientes.entity.Cliente;
import org.jugpy.serviciosonline.cuentas.dto.CuentaACuotas;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;

/**
 *
 * @author raphapy
 */
@Stateless
public class CuentasService {
    
    @EJB
    ClientesService clientesService;
    @PersistenceContext(unitName = "PagosOnlinePU")
    EntityManager entityManager;
            
    public List<Cuenta> obtenerCuentasDelCliente(String numeroDocumento) throws ServiceException {
        Cliente cliente = clientesService.obtenerCliente(numeroDocumento);
        if(cliente==null) {
            throw new ServiceException("El cliente no existe.");
        }
        
        TypedQuery<Cuenta> buscadorCuentasCliente = entityManager.createNamedQuery("buscarCuentasDelCliente", Cuenta.class);
        buscadorCuentasCliente.setParameter("id_cliente", cliente.getIdCliente());
        return buscadorCuentasCliente.getResultList();
    }
    
    public List<Cuenta> obtenerCuentasActivasDelCliente(String numeroDocumento) throws ServiceException {
        List<Cuenta> cuentas = this.obtenerCuentasDelCliente(numeroDocumento);
        return cuentas.stream()
                      .filter(c->c.getFechaCancelacion()==null)
                      .sorted((c1,c2)-> Long.compare(c1.getIdCuenta(), c2.getIdCuenta()))
                      .collect(Collectors.toList());
    }
    
    public Cuenta obtenerCuenta(Long idCuenta) {
        return entityManager.find(Cuenta.class, idCuenta);
    }

    public void actualizarCuenta(Cuenta cuenta) {
        entityManager.merge(cuenta);
    }
    
    public void crearCuentaACuotas(Cuenta cuenta, List<DetalleCuotas> cuotas) {
        entityManager.persist(cuenta);
        if(cuotas!=null && !cuotas.isEmpty()) {
            cuotas.stream().forEach(d-> {
                                        d.setCuenta(cuenta);
                                        entityManager.persist(d);
                                    });
        }
    }
    
    public List<DetalleCuotas> buscarCuotasDeLaCuenta(Long idCuenta) {
        TypedQuery<DetalleCuotas> buscarCuotasDeLaCuenta = 
                entityManager.createNamedQuery("buscarCuotasDeLaCuenta", DetalleCuotas.class);
        buscarCuotasDeLaCuenta.setParameter("id_cuenta", idCuenta);
        return buscarCuotasDeLaCuenta.getResultList();
    }

    public void actualizarCuota(DetalleCuotas c) {
        entityManager.merge(c);
    }

    public List<CuentaACuotas> obtenerCuentasConCuotas(String numeroDocumento) throws ServiceException {
        List<Cuenta> cuentasDelCliente = obtenerCuentasDelCliente(numeroDocumento);
        
        if(cuentasDelCliente!=null && !cuentasDelCliente.isEmpty()) {
            final List<CuentaACuotas> cuentasACuota = new ArrayList<>(cuentasDelCliente.size());
            
            cuentasDelCliente.forEach(cuenta->{
                
                    List<DetalleCuotas> detalle = buscarCuotasDeLaCuenta(cuenta.getIdCuenta());
                    if(detalle!=null && !detalle.isEmpty()) {
                        CuentaACuotas item = new CuentaACuotas();
                        item.setCuenta(cuenta);
                        item.setCuotas(detalle);
                        cuentasACuota.add(item);
                    }
            
            });
            
            return cuentasACuota;
            
        } else { 
                return null;
        }
    }
}
