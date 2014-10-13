package com.yf.kp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author BlackCode
 */
@Entity
@Table(name = "tabel_kelas")
@NamedQueries({
    @NamedQuery(name = "TabelKelas.findAll", query = "SELECT t FROM TabelKelas t")})
public class TabelKelas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_kelas")
    private String idKelas;
    @Basic(optional = false)
    @Column(name = "nip_wali_kelas")
    private String nipWaliKelas;
    @Basic(optional = false)
    @Column(name = "nama_kelas")
    private String namaKelas;

    public TabelKelas() {
    }

    public TabelKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public TabelKelas(String idKelas, String nipWaliKelas, String namaKelas) {
        this.idKelas = idKelas;
        this.nipWaliKelas = nipWaliKelas;
        this.namaKelas = namaKelas;
    }

    public String getIdKelas() {
        return idKelas;
    }

    public void setIdKelas(String idKelas) {
        this.idKelas = idKelas;
    }

    public String getNipWaliKelas() {
        return nipWaliKelas;
    }

    public void setNipWaliKelas(String nipWaliKelas) {
        this.nipWaliKelas = nipWaliKelas;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKelas != null ? idKelas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelKelas)) {
            return false;
        }
        TabelKelas other = (TabelKelas) object;
        if ((this.idKelas == null && other.idKelas != null) || (this.idKelas != null && !this.idKelas.equals(other.idKelas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelKelas[ idKelas=" + idKelas + " ]";
    }

}
