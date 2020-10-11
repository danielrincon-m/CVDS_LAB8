package edu.eci.cvds.sampleprj.dao.mybatis;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.google.inject.Inject;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBatisItemRentadoDAO implements ItemRentadoDAO {
    @Inject
    private ItemRentadoMapper itemRentadoMapper;

    @Override
    public long multaAlquiler(int itemId, Date fechaDevolucion) {
        try {
            ItemRentado itemRentado = itemRentadoMapper.consultarItemRentado(itemId);
            LocalDate finRenta = itemRentado.getFechafinrenta().toLocalDate();
            LocalDate devolucion = fechaDevolucion.toLocalDate();
            return ChronoUnit.DAYS.between(finRenta, devolucion) * itemRentado.getItem().getTarifaxDia();
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar la multa de " + Integer.toString(itemId), e);
        }
    }

    @Override
    public long multaAlquiler(Date fechaFinRenta, Date fechaDevolucion, long tarifaxdia) throws PersistenceException {
        try {
            LocalDate finRenta = fechaFinRenta.toLocalDate();
            LocalDate devolucion = fechaDevolucion.toLocalDate();
            return ChronoUnit.DAYS.between(finRenta, devolucion) * tarifaxdia;
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar la multa", e);
        }
    }

    @Override
    public void registrarAlquiler(long doc, Item item, Date fechaInicio, int numDias) {
        LocalDate fechaInicial = fechaInicio.toLocalDate();
        LocalDate fechaFinal = fechaInicial.plusDays(numDias);
        try {
            itemRentadoMapper.registrarRenta(doc, item.getId(), fechaInicio, Date.valueOf(fechaFinal));
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al registrar el alquiler de " + Long.toString(doc), e);
        }
    }
}
