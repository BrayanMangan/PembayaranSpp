package com.yf.kp.model;

import com.yf.kp.model.base.BaseModel;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "siswa")
public class Siswa extends BaseModel {

    private Integer nis;
    private String nama;
    private String kelas;
    private String jenis_kelamin;
    private String agama;
    private String tempat_lahir;

    @Temporal(TemporalType.DATE)
    private Date tgl_lahir;

    private String alamat;
    private String nama_ortu;
    private String pekerjaan;
    private String agama_ortu;
    private String telp;
    private String alamat_ortu;

    public Siswa() {
    }

    public Siswa(Integer nis, String nama, String kelas, String jenis_kelamin, String agama, String tempat_lahir, Date tgl_lahir, String alamat, String nama_ortu, String pekerjaan, String agama_ortu, String telp, String alamat_ortu) {
        this.nis = nis;
        this.nama = nama;
        this.kelas = kelas;
        this.jenis_kelamin = jenis_kelamin;
        this.agama = agama;
        this.tempat_lahir = tempat_lahir;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.nama_ortu = nama_ortu;
        this.pekerjaan = pekerjaan;
        this.agama_ortu = agama_ortu;
        this.telp = telp;
        this.alamat_ortu = alamat_ortu;
    }

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

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Date getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(Date tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama_ortu() {
        return nama_ortu;
    }

    public void setNama_ortu(String nama_ortu) {
        this.nama_ortu = nama_ortu;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAgama_ortu() {
        return agama_ortu;
    }

    public void setAgama_ortu(String agama_ortu) {
        this.agama_ortu = agama_ortu;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat_ortu() {
        return alamat_ortu;
    }

    public void setAlamat_ortu(String alamat_ortu) {
        this.alamat_ortu = alamat_ortu;
    }

}
