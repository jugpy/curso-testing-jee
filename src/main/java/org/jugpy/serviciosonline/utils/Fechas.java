/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author raphapy
 */
public class Fechas {

    /**
     * 
     * @param fecha
     * @param incremento
     * @return 
     */
    public static Date incrementarMes(Date fecha, Integer incremento) {
        if (fecha==null || incremento==null) {
            throw new IllegalArgumentException("Se requieren la fecha y el incremento");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + incremento);
        return cal.getTime();
    }
}
