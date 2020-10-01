/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del archivo
     * de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * 
     * @param args
     * @throws SQLException
     * @throws ParseException
     */
    public static void main(String args[]) throws SQLException, ParseException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        SqlSession sqlss = sessionfact.openSession();

        // Crear el mapper y usarlo:
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        ItemMapper im = sqlss.getMapper(ItemMapper.class);

        
        // Consultar todos los clientes
        System.out.println(cm.consultarClientes());

        // Consultar un cliente por documento
        // System.out.println(cm.consultarCliente(-703));

        // Insertar un item rentado
        // cm.agregarItemRentadoACliente(-703, 99, dateFormat.parse("2020-09-26"),
        // dateFormat.parse("2021-09-26"));

        // Insertar un nuevo item
        // TipoItem tipoItem = new TipoItem(4, "virus");
        // Item item = new Item(tipoItem, 1000002, "aaa", "aaaaaaaaaaa", dateFormat.parse("2020-09-26"), 44444, "a",
        //         "aaaaaaa");
        // im.insertarItem(item);

        // Consultar todos los items
        // System.out.println(im.consultarItems());

        // Consultar item por id
        // System.out.println(im.consultarItem(1));


        sqlss.commit();
        sqlss.close();
    }
}
