package edu.eci.cvds.samples.services.impl;

import java.sql.Date;
import java.util.List;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

public class ServiciosAlquilerImpl implements ServiciosAlquiler {

    @Inject
    private ClienteDAO clienteDAO;
    @Inject
    private ItemRentadoDAO itemRentadoDAO;
    @Inject
    private ItemDAO itemDAO;
    // @Inject
    // private TipoItemDAO tipoItemDAO;

    @Override
    public long valorMultaRetrasoxDia(int itemId) {
        return itemDAO.valorMultaXDia(itemId);
    }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        return clienteDAO.consultarcliente(docu);
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        return clienteDAO.consultarItemsRentados(idcliente);
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        return clienteDAO.consultarClientes();
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item " + id, ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() {
        return itemDAO.itemsDisponibles();
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        return itemRentadoDAO.multaAlquiler(iditem, fechaDevolucion);
    }

    @Override
	public long consultarMultaAlquiler(Date fechaFinRenta, Date fechaDevolucion, long tarifaxdia) throws ExcepcionServiciosAlquiler {
        return itemRentadoDAO.multaAlquiler(fechaFinRenta, fechaDevolucion, tarifaxdia);
	}

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        return itemDAO.tipoItem(id);
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        return itemDAO.tipoItems();
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias)
            throws ExcepcionServiciosAlquiler {
        itemRentadoDAO.registrarAlquiler(docu, item, date, numdias);
    }

    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        clienteDAO.registrarCliente(c);
    }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        return itemDAO.costoAlquiler(iditem, numdias);
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
        itemDAO.actualizarTarifa(id, tarifa);
    }

    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        itemDAO.save(i);
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        clienteDAO.vetarCliente(docu, estado);
    }
}
