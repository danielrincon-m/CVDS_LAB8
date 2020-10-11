package edu.eci.cvds.test;

import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

public class ServiciosAlquilerTest {

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    @Before
    public void setUp() {
    }

    @Test
    public void emptyDB() {
        for (int i = 0; i < 100; i += 10) {
            Cliente cliente;
            try {
                cliente = serviciosAlquiler.consultarCliente(i);
                Assert.assertNull(cliente);
            } catch (ExcepcionServiciosAlquiler | IndexOutOfBoundsException e) {
                return;
            }
        }
    }
    
    @Test
    public void unCliente() throws ExcepcionServiciosAlquiler {
        long documento = ThreadLocalRandom.current().nextInt(0, 1000 + 1);;
        Cliente cliente = new Cliente("Daniel R", documento, "3216549875", "Calle -3", "a.b@c");
        serviciosAlquiler.registrarCliente(cliente);
        Assert.assertEquals(serviciosAlquiler.consultarCliente(documento).getDocumento(), documento);
    }

    @Test
    public void vetarCliente() throws ExcepcionServiciosAlquiler {
        long documento = ThreadLocalRandom.current().nextInt(0, 1000 + 1);;
        Cliente cliente = new Cliente("Daniel R", documento, "3216549875", "Calle -3", "a.b@c");

        serviciosAlquiler.registrarCliente(cliente);
        Assert.assertFalse(serviciosAlquiler.consultarCliente(documento).isVetado());

        serviciosAlquiler.vetarCliente(documento, true);
        Assert.assertTrue(serviciosAlquiler.consultarCliente(documento).isVetado());
    }

    @Test
    public void registrarItem() throws ExcepcionServiciosAlquiler {
        int tarifaxdia = 15;
        int id = ThreadLocalRandom.current().nextInt(0, 1000 + 1);;

        Date date = new Date(System.currentTimeMillis());
        TipoItem tipoItem = new TipoItem(1, "Tipo del item");
        Item item = new Item(tipoItem, id, "PAPA", "Papita", date, tarifaxdia, "HOLA", "AAAA");

        serviciosAlquiler.registrarItem(item);

        Assert.assertEquals(tarifaxdia, serviciosAlquiler.consultarItem(id).getTarifaxDia());
    }

    @Test
    public void actualizarTarifaItem() throws ExcepcionServiciosAlquiler {
        int tarifaxdia = 15;
        int nuevaTarifa = 20;
        int id = ThreadLocalRandom.current().nextInt(0, 1000 + 1);

        Date date = new Date(System.currentTimeMillis());
        TipoItem tipoItem = new TipoItem(1, "Tipo del item");
        Item item = new Item(tipoItem, id, "PAPA", "Papita", date, tarifaxdia, "HOLA", "AAAA");

        serviciosAlquiler.registrarItem(item);
        Assert.assertEquals(tarifaxdia, serviciosAlquiler.consultarItem(id).getTarifaxDia());

        serviciosAlquiler.actualizarTarifaItem(id, nuevaTarifa);
        Assert.assertEquals(nuevaTarifa, serviciosAlquiler.consultarItem(id).getTarifaxDia());
    }

    @Test
    public void consultarCostoItem() throws ExcepcionServiciosAlquiler {
        int tarifaxdia = 15;
        int numDias = 2;
        int tarifaEsperada = tarifaxdia * numDias;
        int id = ThreadLocalRandom.current().nextInt(0, 1000 + 1);

        Date date = new Date(System.currentTimeMillis());
        TipoItem tipoItem = new TipoItem(1, "Tipo del item");
        Item item = new Item(tipoItem, id, "PAPA", "Papita", date, tarifaxdia, "HOLA", "AAAA");

        serviciosAlquiler.registrarItem(item);
        Assert.assertEquals(tarifaEsperada, serviciosAlquiler.consultarCostoAlquiler(id, numDias));
    }
}
