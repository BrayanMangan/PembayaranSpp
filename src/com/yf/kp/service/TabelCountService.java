package com.yf.kp.service;

import org.hibernate.HibernateException;

/**
 *
 * @author BlackCode
 */
public interface TabelCountService {
    
    public Long jumlahAgama()throws HibernateException;
    public Long jumlahBayarBiayaTambahan()throws HibernateException;
    public Long jumlahBayarSpp()throws HibernateException;
    public Long jumlahBiayaTambahan()throws HibernateException;
    public Long jumlahKelas()throws HibernateException;
    public Long jumlahKonfig()throws HibernateException;
    public Long jumlahSiswa()throws HibernateException;
    public Long jumlahSpp()throws HibernateException;
    public Long jumlahWaliKelas()throws HibernateException;

}
