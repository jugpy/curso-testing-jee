/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.clientes.boundary;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.hamcrest.CoreMatchers.is;
import org.jugpy.serviciosonline.clientes.entity.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author raphapy
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClientesServiceTest {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    
    //under test
    private final ClientesService clienteServices = new ClientesService();
    private Cliente cliente;
    
    @BeforeClass
    public static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("testPU");
        entityManager = entityManagerFactory.createEntityManager();        
    }
    
    @AfterClass
    public static void afterClass() {
        if(entityManager!=null) {
            entityManager.close();
        }
        
        if(entityManagerFactory!=null) {
            entityManagerFactory.close();
        }
    }
    
    @Before
    public void setUp() {
        
        this.cliente = new Cliente();
        this.cliente.setNombre("Nombre Prueba");
        this.cliente.setApellido("Apellido Prueba");
        this.cliente.setNumeroDocumento("2384667");
        
        //inyección de dependencia
        this.clienteServices.em=entityManager;
        this.clienteServices.em.getTransaction().begin();
    }
    
    @After
    public void tearDown() {
        this.clienteServices.em.getTransaction().rollback();
    }
    
    @Test
    public void testObtenerClienteNotFound() throws Exception {
        Cliente c = clienteServices.obtenerCliente("2384667");
        assertNull(c);
    }
    
    @Test
    public void testObtenerCliente() throws Exception {
        clienteServices.crearCliente(cliente);
        Cliente c = clienteServices.obtenerCliente("2384667");
        assertThat(c, is(cliente));
    }

    @Test
    public void testCrearCliente() throws Exception {
        clienteServices.crearCliente(cliente);
    }
}
