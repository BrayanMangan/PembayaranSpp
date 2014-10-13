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
@Table(name = "tabel_bayar_spp")
@NamedQueries({
    @NamedQuery(name = "TabelBayarSpp.findAll", query = "SELECT t FROM TabelBayarSpp t")})
public class TabelBayarSpp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_bayar_spp")
    private String idBayarSpp;
    @Basic(optional = false)
    @Column(name = "nis_siswa")
    private String nisSiswa;
    @Basic(optional = false)
    @Column(name = "id_spp_on_bayar")
    private int idSppOnBayar;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "tanggal_bayar")
    @Temporal(TemporalType.DATE)
    private Date tanggalBayar;

    public TabelBayarSpp() {
    }

    public TabelBayarSpp(String idBayarSpp) {
        this.idBayarSpp = idBayarSpp;
    }

    public TabelBayarSpp(String idBayarSpp, String nisSiswa, int idSppOnBayar, String status, Date tanggalBayar) {
        this.idBayarSpp = idBayarSpp;
        this.nisSiswa = nisSiswa;
        this.idSppOnBayar = idSppOnBayar;
        this.status = status;
        this.tanggalBayar = tanggalBayar;
    }

    public String getIdBayarSpp() {
        return idBayarSpp;
    }

    public void setIdBayarSpp(String idBayarSpp) {
        this.idBayarSpp = idBayarSpp;
    }

    public String getNisSiswa() {
        return nisSiswa;
    }

    public void setNisSiswa(String nisSiswa) {
        this.nisSiswa = nisSiswa;
    }

    public int getIdSppOnBayar() {
        return idSppOnBayar;
    }

    public void setIdSppOnBayar(int idSppOnBayar) {
        this.idSppOnBayar = idSppOnBayar;
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
        hash += (idBayarSpp != null ? idBayarSpp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelBayarSpp)) {
            return false;
        }
        TabelBayarSpp other = (TabelBayarSpp) object;
        if ((this.idBayarSpp == null && other.idBayarSpp != null) || (this.idBayarSpp != null && !this.idBayarSpp.equals(other.idBayarSpp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelBayarSpp[ idBayarSpp=" + idBayarSpp + " ]";
    }

}
