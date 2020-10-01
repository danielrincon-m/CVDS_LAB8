package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Cliente;

/**
 *
 * @author 2106913
 */
public interface ClienteMapper {

    /**
     * Consultar un cliente por su número de documento
     * 
     * @param id
     * @return El objeto que representa al cliente
     */
    public Cliente consultarCliente(@Param("idCliente") int id);

    /**
     * Registrar un nuevo item rentado asociado al cliente identificado con 'idc' y
     * relacionado con el item identificado con 'idi'
     * 
     * @param id
     * @param idit
     * @param fechainicio
     * @param fechafin
     */
    public void agregarItemRentadoACliente(@Param("idCliente") int id, @Param("idItem") int idit,
            @Param("fechaInicio") Date fechainicio, @Param("fechaFin") Date fechafin);

    /**
     * Consultar todos los clientes
     * 
     * @return
     */
    public List<Cliente> consultarClientes();

}
