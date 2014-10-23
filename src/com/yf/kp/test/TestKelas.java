/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yf.kp.test;

import com.yf.kp.model.Kelas;
import com.yf.kp.model.Siswa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author anonymous
 */
public class TestKelas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        AnnotationConfiguration config = new AnnotationConfiguration();
        config.addAnnotatedClass(Kelas.class);
        config.addAnnotatedClass(Siswa.class);
        config.configure("hibernate.cfg.xml");
        new SchemaExport(config).create(true, true);

        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Kelas kelas1A = new Kelas();
        kelas1A.setNama_kelas("1A");

        Kelas kelas1B = new Kelas();
        kelas1B.setNama_kelas("1B");

        Kelas kelas2A = new Kelas();
        kelas2A.setNama_kelas("2A");

        Kelas kelas2B = new Kelas();
        kelas2B.setNama_kelas("2B");

        Kelas kelas3A = new Kelas();
        kelas3A.setNama_kelas("3A");

        Kelas kelas3B = new Kelas();
        kelas3B.setNama_kelas("3B");

        session.save(kelas1A);
        session.save(kelas1B);
        session.save(kelas2A);
        session.save(kelas2B);
        session.save(kelas3A);
        session.save(kelas3B);

        session.getTransaction().commit();
    }

}
