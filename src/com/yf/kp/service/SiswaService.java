package com.yf.kp.service;

import com.yf.kp.model.Siswa;
import java.util.List;

/**
 *
 * @author anonymous
 */
public interface SiswaService extends AbstractService<Siswa>{
    
    public List<Siswa> findByNis(String nis);
    
    public List<Siswa> findByNama(String nama);

    public List<Siswa> findAllByKelas(String kelas);

    public List<Siswa> findAllByKelasAndAngsuran(String toString, Boolean b);

    public List<Siswa> findAllByKelasAndBulanan(String toString, boolean b);

    public List<Siswa> findAllByKelasAndTunai(String toString, boolean b);

    public Siswa findOneByName(String nama);
    
}
