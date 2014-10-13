package com.yf.kp.service;

import com.yf.kp.model.TabelPengguna;
import com.yf.kp.utility.AbstractService;
import org.hibernate.HibernateException;

/**
 *
 * @author BlackCode
 */
public interface TabelPenggunaService extends AbstractService<TabelPengguna> {
    
    public TabelPengguna loginPengguna(String username, String password)throws HibernateException;

}
