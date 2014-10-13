package com.yf.kp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author BlackCode
 */
@Entity
@Table(name = "tabel_siswa")
@NamedQueries({
    @NamedQuery(name = "TabelSiswa.findAll", query = "SELECT t FROM TabelSiswa t")})
public class TabelSiswa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nis")
    private String nis;
    @Basic(optional = false)
    @Column(name = "nama_siswa")
    private String namaSiswa;
    @Basic(optional = false)
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;
    @Basic(optional = false)
    @Lob
    @Column(name = "alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "no_telpon")
    private String noTelpon;
    @Basic(optional = false)
    @Column(name = "id_agama_on_siswa")
    private int idAgamaOnSiswa;
    @Basic(optional = false)
    @Column(name = "tempat_lahir")
    private String tempatLahir;
    @Basic(optional = false)
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Basic(optional = false)
    @Column(name = "nama_ortu")
    private String namaOrtu;
    @Basic(optional = false)
    @Column(name = "pekerjaan_ortu")
    private String pekerjaanOrtu;
    @Basic(optional = false)
    @Column(name = "no_telpon_ortu")
    private String noTelponOrtu;
    @Basic(optional = false)
    @Column(name = "id_kelas_siswa")
    private String idKelasSiswa;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public TabelSiswa() {
    }

    public TabelSiswa(String nis) {
        this.nis = nis;
    }

    public TabelSiswa(String nis, String namaSiswa, String jenisKelamin, String alamat, String noTelpon, int idAgamaOnSiswa, String tempatLahir, Date tanggalLahir, String namaOrtu, String pekerjaanOrtu, String noTelponOrtu, String idKelasSiswa, String status) {
        this.nis = nis;
        this.namaSiswa = namaSiswa;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.noTelpon = noTelpon;
        this.idAgamaOnSiswa = idAgamaOnSiswa;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.namaOrtu = namaOrtu;
        this.pekerjaanOrtu = pekerjaanOrtu;
        this.noTelponOrtu = noTelponOrtu;
        this.idKelasSiswa = idKelasSiswa;
        this.status = status;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public int getIdAgamaOnSiswa() {
        return idAgamaOnSiswa;
    }

    public void setIdAgamaOnSiswa(int idAgamaOnSiswa) {
        this.idAgamaOnSiswa = idAgamaOnSiswa;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNamaOrtu() {
        return namaOrtu;
    }

    public void setNamaOrtu(String namaOrtu) {
        this.namaOrtu = namaOrtu;
    }

    public String getPekerjaanOrtu() {
        return pekerjaanOrtu;
    }

    public void setPekerjaanOrtu(String pekerjaanOrtu) {
        this.pekerjaanOrtu = pekerjaanOrtu;
    }

    public String getNoTelponOrtu() {
        return noTelponOrtu;
    }

    public void setNoTelponOrtu(String noTelponOrtu) {
        this.noTelponOrtu = noTelponOrtu;
    }

    public String getIdKelasSiswa() {
        return idKelasSiswa;
    }

    public void setIdKelasSiswa(String idKelasSiswa) {
        this.idKelasSiswa = idKelasSiswa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nis != null ? nis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelSiswa)) {
            return false;
        }
        TabelSiswa other = (TabelSiswa) object;
        if ((this.nis == null && other.nis != null) || (this.nis != null && !this.nis.equals(other.nis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelSiswa[ nis=" + nis + " ]";
    }

}
