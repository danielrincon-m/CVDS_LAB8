package edu.eci.cvds.samples.services;

public class ExcepcionServiciosAlquiler extends Exception {

    private static final long serialVersionUID = 7269534185833609587L;

    public ExcepcionServiciosAlquiler(String m) {
        super(m);
    }

    public ExcepcionServiciosAlquiler(String m, Throwable t) {
        super(m, t);
    }
}
