package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBatisItemDAO implements ItemDAO {

    @Inject
    private ItemMapper itemMapper;

    @Override
    public void save(Item it) throws PersistenceException {
        try {
            itemMapper.insertarItem(it);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al registrar el item " + it.toString(), e);
        }
    }

    @Override
    public Item load(int id) throws PersistenceException {
        try {
            return itemMapper.consultarItem(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el item " + Integer.toString(id), e);
        }
    }

    @Override
    public List<Item> loadAll() throws PersistenceException {
        try {
            return itemMapper.consultarItems();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los items", e);
        }
    }

    @Override
    public long valorMultaXDia(int itemId) throws PersistenceException {
        Item it = load(itemId);
        return it.getTarifaxDia();
    }

    @Override
    public List<Item> itemsDisponibles() throws PersistenceException {
        try {
            return itemMapper.consultarItemsDisponibles();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los items", e);
        }
    }    

    @Override
    public TipoItem tipoItem(int itemId) throws PersistenceException {
        Item it = load(itemId);
        return it.getTipo();
    }

    @Override
    public List<TipoItem> tipoItems() throws PersistenceException {
        List<Item> items = loadAll();
        List<TipoItem> tipoItems = new ArrayList<>();
        for (Item item : items) {
            tipoItems.add(item.getTipo());
        }
        return tipoItems;
    }

    @Override
    public long costoAlquiler(int itemId, int numDias) throws PersistenceException {
        Item item = load(itemId);
        return item.getTarifaxDia() * numDias;
    }

    @Override
    public void actualizarTarifa(int itemId, long nuevaTarifa) throws PersistenceException {
        itemMapper.actualizarTarifa(itemId, nuevaTarifa);
    }
}
