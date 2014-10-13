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
@Table(name = "tabel_wali_kelas")
@NamedQueries({
    @NamedQuery(name = "TabelWaliKelas.findAll", query = "SELECT t FROM TabelWaliKelas t")})
public class TabelWaliKelas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nip")
    private String nip;
    @Basic(optional = false)
    @Column(name = "nama_wali_kelas")
    private String namaWaliKelas;

    public TabelWaliKelas() {
    }

    public TabelWaliKelas(String nip) {
        this.nip = nip;
    }

    public TabelWaliKelas(String nip, String namaWaliKelas) {
        this.nip = nip;
        this.namaWaliKelas = namaWaliKelas;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNamaWaliKelas() {
        return namaWaliKelas;
    }

    public void setNamaWaliKelas(String namaWaliKelas) {
        this.namaWaliKelas = namaWaliKelas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nip != null ? nip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelWaliKelas)) {
            return false;
        }
        TabelWaliKelas other = (TabelWaliKelas) object;
        if ((this.nip == null && other.nip != null) || (this.nip != null && !this.nip.equals(other.nip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelWaliKelas[ nip=" + nip + " ]";
    }

}
