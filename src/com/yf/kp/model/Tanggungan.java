package com.yf.kp.model;

import com.yf.kp.model.base.BaseModel;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "tanggungan")
public class Tanggungan extends BaseModel {

    private Integer nis;
    private String nama;
    private String kelas;
    private String tanggungan;

    public Integer getNis() {
        return nis;
    }

    public void setNis(Integer nis) {
        this.nis = nis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getTanggungan() {
        return tanggungan;
    }

    public void setTanggungan(String tanggungan) {
        this.tanggungan = tanggungan;
    }

}
