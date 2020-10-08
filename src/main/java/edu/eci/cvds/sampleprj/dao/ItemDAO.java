package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

public interface ItemDAO {

    public void save(Item it) throws PersistenceException;

    public Item load(int id) throws PersistenceException;

    public List<Item> loadAll() throws PersistenceException;

    public long valorMultaXDia(int itemId) throws PersistenceException;

    public List<Item> itemsDisponibles() throws PersistenceException;

    public TipoItem tipoItem(int itemId) throws PersistenceException;

    public List<TipoItem> tipoItems() throws PersistenceException;

    public long costoAlquiler(int itemId, int numDias) throws PersistenceException;

    public void actualizarTarifa(int itemId, long nuevaTarifa) throws PersistenceException;

}
