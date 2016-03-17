/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.pagos.control;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.jugpy.serviciosonline.cuentas.entity.DetalleCuotas;
import org.jugpy.serviciosonline.pagos.dto.RespuestaPago;
import org.jugpy.serviciosonline.pagos.dto.ResultadoDeAmortizacion;

/**
 *
 * @author raphapy
 */
public class PagosControl {
    
    /**
     * Aplica amortización a una lista de cuotas dadas.
     * @param montoPagado
     * @param cuotas
     * @return devuelve la lista de cuotas amortizadas en funcion al pago
     */
    public ResultadoDeAmortizacion amortizarCuotas(Double montoPagado, List<DetalleCuotas> cuotas) {
        List<DetalleCuotas> cuotasOrdenadas = null;
        ResultadoDeAmortizacion result = new ResultadoDeAmortizacion();
        if(montoPagado!=null && montoPagado>0) {
           cuotasOrdenadas = cuotas.stream()
                  .filter(c->c.getFechaCancelacion()==null)
                  .sorted((c1,c2)-> c1.getNroCuota().compareTo(c2.getNroCuota()))
                  .collect(Collectors.toList());
            
            Double saldoAFavor=montoPagado;
            for(DetalleCuotas d:cuotasOrdenadas) {
                if(saldoAFavor>0) {
                    Double saldoCuota = d.getSaldoCuota()==null ? d.getMontoCuota() : d.getSaldoCuota();
                    if(saldoAFavor>=saldoCuota) {
                        saldoAFavor = saldoAFavor - d.getMontoCuota();
                        d.setSaldoCuota(0.0);
                        d.setFechaCancelacion(new Date());
                    } else {
                        Double nuevoSaldo = saldoCuota - saldoAFavor;
                        saldoAFavor = 0d;
                        d.setSaldoCuota(nuevoSaldo);
                    }
                } else {
                    break;
                }
            }
            
            result.setCredito(saldoAFavor);
            result.setEstadoCuenta(cuotasOrdenadas);
        }
        
        return result;
    }
}
