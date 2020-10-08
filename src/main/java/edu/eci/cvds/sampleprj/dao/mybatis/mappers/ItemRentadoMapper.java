package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import java.sql.Date;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoMapper {

    public ItemRentado consultarItemRentado(@Param("id") int id);

    public void registrarRenta(@Param("doc") long documento, @Param("itemId") int id,
            @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);

}
