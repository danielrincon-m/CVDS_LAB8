package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBatisClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public Cliente consultarcliente(long doc) throws PersistenceException {
        try {
            return clienteMapper.consultarCliente(doc);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar el cliente " + Long.toString(doc), e);
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws PersistenceException {
        try {
            return clienteMapper.consultarClientes();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar los clientes", e);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsRentados(long doc) throws PersistenceException {
        Cliente cliente = consultarcliente(doc);
        return cliente.getRentados();
    }

    @Override
    public void registrarCliente(Cliente cliente) throws PersistenceException {
        try {
            clienteMapper.registrarCliente(cliente);   
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al insertar el cliente", e);
        }
    }

    @Override
    public void vetarCliente(long doc, boolean vetado) throws PersistenceException {
        try {
            clienteMapper.setVetado(doc, vetado);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al modificar el estado de vetado del cliente " + Long.toString(doc), e);
        }
    }

}
