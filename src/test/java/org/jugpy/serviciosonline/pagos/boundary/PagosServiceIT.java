/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.boundary;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import static org.hamcrest.Matchers.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jugpy.serviciosonline.clientes.boundary.ClientesService;
import org.jugpy.serviciosonline.clientes.entity.Cliente;
import org.jugpy.serviciosonline.cuentas.boundary.CuentasService;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;
import org.jugpy.serviciosonline.pagos.control.PagosControl;
import org.jugpy.serviciosonline.pagos.dto.RespuestaPago;
import org.jugpy.serviciosonline.pagos.dto.SolicitudDePago;
import org.jugpy.serviciosonline.utils.Fechas;
import static org.hamcrest.MatcherAssert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author raphapy
 */
@RunWith(Arquillian.class)
public class PagosServiceIT {
    
    @Inject
    private PagosService pagosService;
    @Inject
    private CuentasService cuentasService;
    
    private List<DetalleCuotas> cuotas;
    private Cliente cliente;
    private Cuenta cuenta;
    private Date fechaAlta;
    
    public PagosServiceIT() {
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
        
        
        cuentasService.crearCuentaACuotas(cuenta, cuotas);
    }
    
    @Deployment
    public static WebArchive generateDeployment() {
        return ShrinkWrap.create(WebArchive.class, "cuentas-service.war")
                .addPackage("org.jugpy.serviciosonline.exceptions")
                .addPackage("org.jugpy.serviciosonline.clientes.entity")
                .addPackage("org.jugpy.serviciosonline.cuentas.dto")
                .addPackage("org.jugpy.serviciosonline.pagos.dto")
                .addPackage("org.jugpy.serviciosonline.cuentas.entity")
                .addPackage("org.jugpy.serviciosonline.pagos.entity")
                .addPackage("org.jugpy.serviciosonline.utils")
                .addClasses(PagosService.class, CuentasService.class, ClientesService.class, PagosControl.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml");
    }
    
    /**
     * Test of procesarPago method, of class PagosService.
     */
    @Test
    public void testProcesarPago() throws Exception {
        
        SolicitudDePago solicitud = new SolicitudDePago();
        solicitud.setIdCliente(cuenta.getCliente().getIdCliente());
        solicitud.setIdCuenta(cuenta.getIdCuenta());
        solicitud.setMontoPago(800_000d);
        
        final RespuestaPago respuestaPago = pagosService.procesarPago(solicitud);
        assertThat(respuestaPago.getEstado(), is("OK"));
        
        final List<DetalleCuotas> estadoCuenta = respuestaPago.getEstadoCuenta();
        List<DetalleCuotas> canceladas = estadoCuenta
                                        .stream()
                                        .filter(c->c.getFechaCancelacion()!=null)
                                        .collect(Collectors.toList());
        
        assertThat(canceladas, is(iterableWithSize(2)));
        
        Cuenta cuentaActualizada = cuentasService.obtenerCuenta(1L);
        assertThat(cuentaActualizada.getDeudaActual(), is(3_200_000d));
        
    }
}
