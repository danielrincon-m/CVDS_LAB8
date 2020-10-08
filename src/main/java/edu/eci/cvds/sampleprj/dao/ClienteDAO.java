package edu.eci.cvds.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ClienteDAO {
    
    public Cliente consultarcliente(long doc) throws PersistenceException;

    public List<Cliente> consultarClientes() throws PersistenceException;

    public List<ItemRentado> consultarItemsRentados(long doc) throws PersistenceException;

    public void registrarCliente(Cliente cliente) throws PersistenceException;

    public void vetarCliente(long doc, boolean vetado) throws PersistenceException;

}
