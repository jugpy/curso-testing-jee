/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.exceptions;

/**
 *
 * @author raphapy
 */
public class PagoNoAplicableException extends Exception {
    
    public PagoNoAplicableException() {
    }
    
    public PagoNoAplicableException(Throwable cause) {
        super(cause);
    }
    
    public PagoNoAplicableException(String message) {
        super(message);
    } 
    
    public PagoNoAplicableException(String message, Throwable cause) {
        super(message, cause);
    }
}
