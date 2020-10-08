package edu.eci.cvds.sampleprj.dao.mybatis.mappers;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.Item;

/**
 *
 * @author 2106913
 */
public interface ItemMapper {
    
    public List<Item> consultarItems();
    
    public List<Item> consultarItemsDisponibles();
    
    public Item consultarItem(@Param("idItem") int id);
    
    public void insertarItem(@Param("item") Item it);

    public void actualizarTarifa(@Param("idItem") int id, @Param("tarifa") long tarifa);
}
