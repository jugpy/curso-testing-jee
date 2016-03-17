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
public class ServiceException extends Exception {
    public ServiceException() {
    }
    
    public ServiceException(Throwable cause) {
        super(cause);
    }
    
    public ServiceException(String message) {
        super(message);
    } 
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
