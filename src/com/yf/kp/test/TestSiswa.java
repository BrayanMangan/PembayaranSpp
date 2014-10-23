/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yf.kp.test;

import com.yf.kp.model.Siswa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author anonymous
 */
public class TestSiswa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AnnotationConfiguration config = new AnnotationConfiguration();
        config.addAnnotatedClass(Siswa.class);
        config.configure("hibernate.cfg.xml");
        new SchemaExport(config).create(true, true);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Siswa fani = new Siswa();
        fani.setNama("Fani Triastowo");
        fani.setNis(1003040098);

        session.save(fani);
        session.getTransaction().commit();
    }

}
