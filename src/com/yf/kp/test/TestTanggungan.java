/*
 * Copyright (C) 2014 anonymous
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
package com.yf.kp.test;

import com.yf.kp.model.Tanggungan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author anonymous
 */
public class TestTanggungan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.addAnnotatedClass(Tanggungan.class);
        config.configure("hibernate.cfg.xml");
        new SchemaExport(config).create(true, true);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Tanggungan tanggungan = new Tanggungan();
        tanggungan.setNis(1003040098);
        tanggungan.setNama("Fani Triastowo");
        tanggungan.setKelas("1A");
        tanggungan.setTanggungan("SPP");

        session.save(tanggungan);
        session.getTransaction().commit();
    }

}
