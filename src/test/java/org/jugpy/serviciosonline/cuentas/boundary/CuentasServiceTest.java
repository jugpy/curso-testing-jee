/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.cuentas.boundary;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import org.jugpy.serviciosonline.clientes.boundary.ClientesService;
import org.jugpy.serviciosonline.clientes.entity.Cliente;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;
import org.jugpy.serviciosonline.exceptions.ServiceException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author raphapy
 */
public class CuentasServiceTest {
    
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    
    @BeforeClass
    public static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("testPU");
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    @AfterClass
    public static void tearDownClass() {
        entityManager.close();
        entityManagerFactory.close();
    }
    
    private CuentasService cuentasService;
    private Cliente cliente;

    public CuentasServiceTest() {
    }

    @Before
    public void setUp() {
        cuentasService = new CuentasService();
        cuentasService.entityManager = entityManager;
        
        cliente = Cliente.getBuilder()
                          .idCliente(1L)
                          .nombre("Rafael")
                          .apellido("Benegas")
                          .numeroDocumento("2384667")
                          .build();
        
    }

    /**
     * Test of obtenerCuentasDelCliente method, of class CuentasService.
     * @throws org.jugpy.serviciosonline.exceptions.ServiceException
     */
    @Test
    public void testObtenerCuentasDelCliente() throws ServiceException {
        //mock o emulación del comportamiento de un método del cual dependemos
        cuentasService.clientesService = mock(ClientesService.class);
        when(cuentasService.clientesService.obtenerCliente("2384667")).thenReturn(cliente);
        final List<Cuenta> cuentasDelCliente = cuentasService.obtenerCuentasDelCliente("2384667");
        assertThat(cuentasDelCliente, is(iterableWithSize(0)));
    }

}
