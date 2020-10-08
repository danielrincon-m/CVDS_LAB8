package edu.eci.cvds.samples.services;

import static com.google.inject.Guice.createInjector;

import java.util.Optional;

import com.google.inject.Injector;

import org.mybatis.guice.XMLMyBatisModule;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisItemRentadoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisTipoItemDAO;
import edu.eci.cvds.samples.services.impl.ServiciosAlquilerImpl;

public class ServiciosAlquilerFactory {

    private static ServiciosAlquilerFactory instance = new ServiciosAlquilerFactory();

    private static Optional<Injector> optInjector;

    private Injector myBatisInjector(String env, String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId(env);
                setClassPathResource(pathResource);
                
                bind(ItemDAO.class).to(MyBatisItemDAO.class);
                bind(ClienteDAO.class).to(MyBatisClienteDAO.class);
                bind(ItemRentadoDAO.class).to(MyBatisItemRentadoDAO.class);
                bind(TipoItemDAO.class).to(MyBatisTipoItemDAO.class);
                bind(ServiciosAlquiler.class).to(ServiciosAlquilerImpl.class);
            }
        });
    }

    private ServiciosAlquilerFactory() {
        optInjector = Optional.empty();
    }

    public ServiciosAlquiler getServiciosAlquiler() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development", "mybatis-config.xml"));
        }

        return optInjector.get().getInstance(ServiciosAlquiler.class);
    }

    public ServiciosAlquiler getServiciosAlquilerTesting() {
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("test", "mybatis-config-h2.xml"));
        }

        return optInjector.get().getInstance(ServiciosAlquiler.class);
    }

    public static ServiciosAlquilerFactory getInstance() {
        return instance;
    }

}
