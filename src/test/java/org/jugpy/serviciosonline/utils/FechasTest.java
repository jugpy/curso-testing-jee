/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raphapy
 */
public class FechasTest {
    
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
    public FechasTest() {
    }
    
    @Test
    public void testIncrementarMesPositivamente() throws Exception {
        Date fecha = sdf.parse("2016-12-15");
        int incremento = 2;
        Date expResult = sdf.parse("2017-02-15");
        Date result = Fechas.incrementarMes(fecha, incremento);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIncrementarMesNegativamente() throws Exception {
        Date fecha = sdf.parse("2016-12-15");
        int incremento = -2;
        Date expResult = sdf.parse("2016-10-15");
        Date result = Fechas.incrementarMes(fecha, incremento);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testIncrementarMesConCero() throws Exception {
        Date fecha = sdf.parse("2016-12-15");
        int incremento = 0;
        Date result = Fechas.incrementarMes(fecha, incremento);
        assertEquals(fecha, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIncrementarMesIllegalArgument() throws Exception {
        Fechas.incrementarMes(null, null);
    }
    
}
