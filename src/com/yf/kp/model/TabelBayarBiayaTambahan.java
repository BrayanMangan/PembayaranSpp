package com.yf.kp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "tabel_bayar_biaya_tambahan")
@NamedQueries({
    @NamedQuery(name = "TabelBayarBiayaTambahan.findAll", query = "SELECT t FROM TabelBayarBiayaTambahan t")})
public class TabelBayarBiayaTambahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bayar_tambahan")
    private String idBayarTambahan;
    @Basic(optional = false)
    @Column(name = "nis_siswa")
    private String nisSiswa;
    @Basic(optional = false)
    @Column(name = "id_biaya_tambahan_on_bayar")
    private int idBiayaTambahanOnBayar;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "tanggal_bayar")
    @Temporal(TemporalType.DATE)
    private Date tanggalBayar;

    public TabelBayarBiayaTambahan() {
    }

    public TabelBayarBiayaTambahan(String idBayarTambahan) {
        this.idBayarTambahan = idBayarTambahan;
    }

    public TabelBayarBiayaTambahan(String idBayarTambahan, String nisSiswa, int idBiayaTambahanOnBayar, String status, Date tanggalBayar) {
        this.idBayarTambahan = idBayarTambahan;
        this.nisSiswa = nisSiswa;
        this.idBiayaTambahanOnBayar = idBiayaTambahanOnBayar;
        this.status = status;
        this.tanggalBayar = tanggalBayar;
    }

    public String getIdBayarTambahan() {
        return idBayarTambahan;
    }

    public void setIdBayarTambahan(String idBayarTambahan) {
        this.idBayarTambahan = idBayarTambahan;
    }

    public String getNisSiswa() {
        return nisSiswa;
    }

    public void setNisSiswa(String nisSiswa) {
        this.nisSiswa = nisSiswa;
    }

    public int getIdBiayaTambahanOnBayar() {
        return idBiayaTambahanOnBayar;
    }

    public void setIdBiayaTambahanOnBayar(int idBiayaTambahanOnBayar) {
        this.idBiayaTambahanOnBayar = idBiayaTambahanOnBayar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTanggalBayar() {
        return tanggalBayar;
    }

    public void setTanggalBayar(Date tanggalBayar) {
        this.tanggalBayar = tanggalBayar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBayarTambahan != null ? idBayarTambahan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelBayarBiayaTambahan)) {
            return false;
        }
        TabelBayarBiayaTambahan other = (TabelBayarBiayaTambahan) object;
        if ((this.idBayarTambahan == null && other.idBayarTambahan != null) || (this.idBayarTambahan != null && !this.idBayarTambahan.equals(other.idBayarTambahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelBayarBiayaTambahan[ idBayarTambahan=" + idBayarTambahan + " ]";
    }

}
