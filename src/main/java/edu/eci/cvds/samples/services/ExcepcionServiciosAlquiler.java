package edu.eci.cvds.samples.services;

import java.security.PublicKey;

public class ExcepcionServiciosAlquiler extends Exception {

    public ExcepcionServiciosAlquiler(String m) {
        super(m);
    }

    public ExcepcionServiciosAlquiler(String m, Throwable t) {
        super(m, t);
    }
}
