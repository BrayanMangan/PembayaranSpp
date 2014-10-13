package com.yf.kp.serviceImpl;

import com.yf.kp.model.TabelPengguna;
import com.yf.kp.service.TabelPenggunaService;
import com.yf.kp.utility.AbstractServiceImpl;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author BlackCode
 */
public class TabelPenggunaServiceImpl extends AbstractServiceImpl<TabelPengguna> implements TabelPenggunaService{
    
    public TabelPenggunaServiceImpl(){
        super(TabelPengguna.class);
    }

    @Override
    public TabelPengguna loginPengguna(String username, String password) throws HibernateException {
        TabelPengguna pengguna = null;
        connect();
        try{
            Query q = manager().createQuery("SELECT P FROM TabelPengguna P WHERE P.username =:isi1 AND P.password =:isi2").setParameter("isi1", username).setParameter("isi2", password);
            pengguna = (TabelPengguna) q.uniqueResult();
            commit();
        }catch(HibernateException ex){
            rollback();
            throw ex;
        }finally{
            close();
        }
        return pengguna;
    }

}
