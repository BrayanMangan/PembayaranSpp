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

import com.yf.kp.model.JenisPembayaran;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author anonymous
 */
public class TestJenisPembayaran {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.addAnnotatedClass(JenisPembayaran.class);
        config.configure("hibernate.cfg.xml");
        new SchemaExport(config).create(true, true);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        JenisPembayaran jenisPembayaran = new JenisPembayaran();
        jenisPembayaran.setNama("Fani Triastowo");
        jenisPembayaran.setTipe("SPP");
        jenisPembayaran.setNominal(50000.0);

        session.save(jenisPembayaran);
        session.getTransaction().commit();
    }

}
