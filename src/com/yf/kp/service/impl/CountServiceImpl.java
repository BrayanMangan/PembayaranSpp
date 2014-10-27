package com.yf.kp.service.impl;

import com.yf.kp.service.CountService;
import com.yf.kp.utility.HibernateUtil;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author anonymous
 */
public class CountServiceImpl extends HibernateUtil implements CountService {

    @Override
    public Long countKelas() {
        Object jml = 0;
        connect();
        try {
            Query q = manager().createQuery("SELECT COUNT(K.id) AS jml FROM Kelas K");
            jml = q.uniqueResult();
            commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            rollback();
        } finally {
            close();
        }
        return (Long) jml;
    }

}
