/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.iterableWithSize;
import org.jugpy.serviciosonline.clientes.entity.Cliente;
import org.jugpy.serviciosonline.cuentas.entity.Cuenta;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;
import org.jugpy.serviciosonline.pagos.dto.ResultadoDeAmortizacion;
import org.jugpy.serviciosonline.utils.Fechas;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author raphapy
 */
public class PagosControlTest {

    List<DetalleCuotas> cuotas = new ArrayList<>(10);
    PagosControl pagosControl = new PagosControl();
    private Cliente cliente;
    private Cuenta cuenta;
    private Date fechaAlta;

    @Before
    public void setUp() throws ParseException {
        //para todas las operaciones, misma fecha alta
        fechaAlta = new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01");
        
        //cliente
        cliente = Cliente.getBuilder()
                .nombre("Rafael")
                .apellido("Benegas")
                .numeroDocumento("7777777")
                .build();

        //cuenta del cliente
        cuenta = Cuenta.getBuilder()
                       .idCuenta(1L)
                       .cliente(cliente)
                       .deudaTotal(4_000_000d)
                       .fechaAlta(this.fechaAlta)
                       .deudaActual(4_000_000d).credito(0d)
                       .build();

        //cuotas
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
    
    @Test
    public void testAmortizarCuotasParcialmente() {
        
        ResultadoDeAmortizacion result = pagosControl.amortizarCuotas(500_000d, cuotas);
        List<DetalleCuotas> cuotasAmortizadas
                = result.getEstadoCuenta();

        List<DetalleCuotas> cuotasCanceladas
                = cuotasAmortizadas.stream()
                .filter(c -> c.getFechaCancelacion() != null
                        && c.getSaldoCuota() == 0d)
                .collect(Collectors.toList());
        
        assertThat(cuotasCanceladas, is(iterableWithSize(1)));
        
        List<DetalleCuotas> cuotasParcialmenteCanceladas
                = cuotasAmortizadas.stream()
                .filter(c -> c.getFechaCancelacion() == null
                        && c.getSaldoCuota() > 0d
                        && c.getSaldoCuota() < c.getMontoCuota())
                .collect(Collectors.toList());
        
        assertThat(cuotasParcialmenteCanceladas, is(iterableWithSize(1)));

        List<DetalleCuotas> cuotasPendientes
                = cuotasAmortizadas.stream()
                .filter(c -> c.getFechaCancelacion() == null
                        && c.getSaldoCuota() > 0d)
                .collect(Collectors.toList());
        
        assertThat(cuotasPendientes, is(iterableWithSize(9)));
        
        DetalleCuotas cuotaSaldo300000 = 
                DetalleCuotas.getBuilder()
                    .nroCuota(2)
                    .cuenta(cuenta)
                    .fechaAlta(fechaAlta)
                    .fechaVencimiento(Fechas.incrementarMes(fechaAlta, 2))
                    .montoCuota(400_000d)
                    .saldoCuota(300_000d)
                    .build();
        
        assertThat(cuotasPendientes, hasItem(cuotaSaldo300000));
    }

}
