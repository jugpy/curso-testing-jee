/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.cuentas.boundary;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jugpy.serviciosonline.clientes.boundary.ClientesService;
import org.jugpy.serviciosonline.clientes.entity.Cliente;
import org.jugpy.serviciosonline.cuentas.dto.CuentaACuotas;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;
import org.jugpy.serviciosonline.utils.Fechas;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.core.IsNull.nullValue;
import org.jugpy.serviciosonline.exceptions.ServiceException;
/**
 *
 * @author raphapy
 */
@RunWith(Arquillian.class)
public class CuentasServiceIT {
    
    @Inject
    private CuentasService cuentasService;
        
    private List<DetalleCuotas> cuotas;
    private Cliente cliente;
    private Cuenta cuenta;
    private Date fechaAlta;
    
    public CuentasServiceIT() {
    }
    
    @Deployment
    public static WebArchive generateDeployment() {
        return ShrinkWrap.create(WebArchive.class, "cuentas-service.war")
                .addPackage("org.jugpy.serviciosonline.exceptions")
                .addPackage("org.jugpy.serviciosonline.clientes.entity")
                .addPackage("org.jugpy.serviciosonline.cuentas.dto")
                .addPackage("org.jugpy.serviciosonline.cuentas.entity")
                .addPackage("org.jugpy.serviciosonline.pagos.entity")
                .addPackage("org.jugpy.serviciosonline.utils")
                .addClasses(CuentasService.class, ClientesService.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml");
    }

    @Before
    public void setUp() throws ParseException {
        //para todas las operaciones, misma fecha alta
        fechaAlta = new Date();
        
        //cliente
        cliente = Cliente.getBuilder()
                .nombre("Rafael")
                .apellido("Benegas")
                .numeroDocumento("2384667")
                .build();

        //cuenta del cliente
        cuenta = Cuenta.getBuilder()
                       .cliente(cliente)
                       .deudaTotal(4_000_000d)
                       .fechaAlta(this.fechaAlta)
                       .deudaActual(4_000_000d)
                       .credito(0d)
                       .build();

        //cuotas
        cuotas = new ArrayList<>();
        for (int i = 10; i >=1; i--) {
            DetalleCuotas det = DetalleCuotas.getBuilder().nroCuota(i)
                    .cuenta(cuenta)
                    .fechaAlta(this.fechaAlta)
                    .fechaVencimiento(Fechas.incrementarMes(this.fechaAlta, i))
                    .montoCuota(400_000d)
                    .saldoCuota(400_000d)
                    .build();
            cuotas.add(det);

        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of obtenerCuentasDelCliente method, of class CuentasService.
     */
    @Test
    @InSequence(1)
    public void testCrearCuentaACuotas() {
        cuentasService.crearCuentaACuotas(cuenta, cuotas);
        
        assertThat(cuenta.getIdCuenta(), is(not(nullValue())));
    }
    
    /**
     * Test of obtenerCuentasConCuotas method, of class CuentasService.
     */
    @Test
    @InSequence(2)
    public void testObtenerCuentasConCuotas() throws ServiceException {
        List<CuentaACuotas> cuentasACuotas = cuentasService.obtenerCuentasConCuotas("2384667");
        CuentaACuotas cuentaCreada = cuentasACuotas.get(0);
        
        assertThat(cuentasACuotas, is(iterableWithSize(1)));
        assertThat(cuentaCreada.getCuenta(), is(not(nullValue())));
        assertThat(cuentaCreada.getCuotas(), is(iterableWithSize(10)));
        
    }
    
    @Test(expected = ServiceException.class)
    @InSequence(3)
    public void testObtenerCuentasConCuotasClienteNoExiste() throws ServiceException {
        //el cliente xxxx no existe
        cuentasService.obtenerCuentasConCuotas("xxxx");
    }

    /**
     * Test of obtenerCuentasDelCliente method, of class CuentasService.
     */
    @Test
    public void testObtenerCuentasDelCliente() throws Exception {
    }

    /**
     * Test of obtenerCuentasActivasDelCliente method, of class CuentasService.
     */
    @Test
    public void testObtenerCuentasActivasDelCliente() throws Exception {
    }

    /**
     * Test of obtenerCuenta method, of class CuentasService.
     */
    @Test
    public void testObtenerCuenta() throws Exception {
    }

    /**
     * Test of actualizarCuenta method, of class CuentasService.
     */
    @Test
    public void testActualizarCuenta() throws Exception {
    }

    /**
     * Test of buscarCuotasDeLaCuenta method, of class CuentasService.
     */
    @Test
    public void testBuscarCuotasDeLaCuenta() throws Exception {
    }

    /**
     * Test of actualizarCuota method, of class CuentasService.
     */
    @Test
    public void testActualizarCuota() throws Exception {
    }
}
