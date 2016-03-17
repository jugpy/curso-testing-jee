/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugpy.serviciosonline.exceptions;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author raphapy
 */
@XmlRootElement
public class ServiceExceptionMessage implements Serializable {
    private Integer status;
    private String message;

    public ServiceExceptionMessage() {
    }

    public ServiceExceptionMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
    
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
