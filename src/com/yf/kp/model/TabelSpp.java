package com.yf.kp.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tabel_spp")
@NamedQueries({
    @NamedQuery(name = "TabelSpp.findAll", query = "SELECT t FROM TabelSpp t")})
public class TabelSpp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_spp")
    private Integer idSpp;
    @Basic(optional = false)
    @Column(name = "id_konfig_on_spp")
    private int idKonfigOnSpp;
    @Basic(optional = false)
    @Column(name = "bulan")
    private String bulan;
    @Basic(optional = false)
    @Column(name = "nominal")
    private double nominal;
    @Basic(optional = false)
    @Column(name = "tanggal_mulai_bayar")
    @Temporal(TemporalType.DATE)
    private Date tanggalMulaiBayar;
    @Basic(optional = false)
    @Column(name = "tanggal_akhir_bayar")
    @Temporal(TemporalType.DATE)
    private Date tanggalAkhirBayar;

    public TabelSpp() {
    }

    public TabelSpp(Integer idSpp) {
        this.idSpp = idSpp;
    }

    public TabelSpp(Integer idSpp, int idKonfigOnSpp, String bulan, double nominal, Date tanggalMulaiBayar, Date tanggalAkhirBayar) {
        this.idSpp = idSpp;
        this.idKonfigOnSpp = idKonfigOnSpp;
        this.bulan = bulan;
        this.nominal = nominal;
        this.tanggalMulaiBayar = tanggalMulaiBayar;
        this.tanggalAkhirBayar = tanggalAkhirBayar;
    }

    public Integer getIdSpp() {
        return idSpp;
    }

    public void setIdSpp(Integer idSpp) {
        this.idSpp = idSpp;
    }

    public int getIdKonfigOnSpp() {
        return idKonfigOnSpp;
    }

    public void setIdKonfigOnSpp(int idKonfigOnSpp) {
        this.idKonfigOnSpp = idKonfigOnSpp;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public Date getTanggalMulaiBayar() {
        return tanggalMulaiBayar;
    }

    public void setTanggalMulaiBayar(Date tanggalMulaiBayar) {
        this.tanggalMulaiBayar = tanggalMulaiBayar;
    }

    public Date getTanggalAkhirBayar() {
        return tanggalAkhirBayar;
    }

    public void setTanggalAkhirBayar(Date tanggalAkhirBayar) {
        this.tanggalAkhirBayar = tanggalAkhirBayar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpp != null ? idSpp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelSpp)) {
            return false;
        }
        TabelSpp other = (TabelSpp) object;
        if ((this.idSpp == null && other.idSpp != null) || (this.idSpp != null && !this.idSpp.equals(other.idSpp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelSpp[ idSpp=" + idSpp + " ]";
    }

}
