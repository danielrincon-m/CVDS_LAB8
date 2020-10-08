package edu.eci.cvds.sampleprj.dao;

import java.sql.Date;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.Item;

public interface ItemRentadoDAO {

    public long multaAlquiler(int itemId, Date fechaDevolucion) throws PersistenceException;

    public void registrarAlquiler(long doc, Item item, Date fechainicio, int numDias) throws PersistenceException;

}
