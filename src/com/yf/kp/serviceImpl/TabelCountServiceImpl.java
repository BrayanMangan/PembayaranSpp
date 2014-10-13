package com.yf.kp.serviceImpl;

import com.yf.kp.service.TabelCountService;
import com.yf.kp.utility.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author BlackCode
 */
public class TabelCountServiceImpl extends HibernateUtil implements TabelCountService{

    @Override
    public Long jumlahAgama() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.idAgama) AS JUMLAH FROM TabelAgama T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahBayarBiayaTambahan() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.idBayarTambahan) AS JUMLAH FROM TabelBayarBiayaTambahan T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahBayarSpp() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.idBayarSpp) AS JUMLAH FROM TabelBayarSpp T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahBiayaTambahan() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.idBiayaTambahan) AS JUMLAH FROM TabelBiayaTambahan T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahKelas() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.idKelas) AS JUMLAH FROM TabelKelas T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahKonfig() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.idKonfig) AS JUMLAH FROM TabelKonfig T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahSiswa() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.nis) AS JUMLAH FROM TabelSiswa T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahSpp() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.idSpp) AS JUMLAH FROM TabelSpp T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

    @Override
    public Long jumlahWaliKelas() throws HibernateException {
        Object jumlah = 0L;
        connect();
        try{
            Query q = manager().createQuery("SELECT COUNT(T.nip) AS JUMLAH FROM TabelWaliKelas T");
            jumlah = q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return (Long) jumlah;
    }

}
