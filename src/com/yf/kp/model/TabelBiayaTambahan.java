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
@Table(name = "tabel_biaya_tambahan")
@NamedQueries({
    @NamedQuery(name = "TabelBiayaTambahan.findAll", query = "SELECT t FROM TabelBiayaTambahan t")})
public class TabelBiayaTambahan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_biaya_tambahan")
    private Integer idBiayaTambahan;
    @Basic(optional = false)
    @Column(name = "id_konfig_on_tambahan")
    private int idKonfigOnTambahan;
    @Basic(optional = false)
    @Column(name = "nama_biaya_tambahan")
    private String namaBiayaTambahan;
    @Basic(optional = false)
    @Column(name = "nominal")
    private double nominal;
    @Basic(optional = false)
    @Column(name = "tanggal_awal_bayar")
    @Temporal(TemporalType.DATE)
    private Date tanggalAwalBayar;
    @Basic(optional = false)
    @Column(name = "tanggal_akhir_bayar")
    @Temporal(TemporalType.DATE)
    private Date tanggalAkhirBayar;

    public TabelBiayaTambahan() {
    }

    public TabelBiayaTambahan(Integer idBiayaTambahan) {
        this.idBiayaTambahan = idBiayaTambahan;
    }

    public TabelBiayaTambahan(Integer idBiayaTambahan, int idKonfigOnTambahan, String namaBiayaTambahan, double nominal, Date tanggalAwalBayar, Date tanggalAkhirBayar) {
        this.idBiayaTambahan = idBiayaTambahan;
        this.idKonfigOnTambahan = idKonfigOnTambahan;
        this.namaBiayaTambahan = namaBiayaTambahan;
        this.nominal = nominal;
        this.tanggalAwalBayar = tanggalAwalBayar;
        this.tanggalAkhirBayar = tanggalAkhirBayar;
    }

    public Integer getIdBiayaTambahan() {
        return idBiayaTambahan;
    }

    public void setIdBiayaTambahan(Integer idBiayaTambahan) {
        this.idBiayaTambahan = idBiayaTambahan;
    }

    public int getIdKonfigOnTambahan() {
        return idKonfigOnTambahan;
    }

    public void setIdKonfigOnTambahan(int idKonfigOnTambahan) {
        this.idKonfigOnTambahan = idKonfigOnTambahan;
    }

    public String getNamaBiayaTambahan() {
        return namaBiayaTambahan;
    }

    public void setNamaBiayaTambahan(String namaBiayaTambahan) {
        this.namaBiayaTambahan = namaBiayaTambahan;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public Date getTanggalAwalBayar() {
        return tanggalAwalBayar;
    }

    public void setTanggalAwalBayar(Date tanggalAwalBayar) {
        this.tanggalAwalBayar = tanggalAwalBayar;
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
        hash += (idBiayaTambahan != null ? idBiayaTambahan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelBiayaTambahan)) {
            return false;
        }
        TabelBiayaTambahan other = (TabelBiayaTambahan) object;
        if ((this.idBiayaTambahan == null && other.idBiayaTambahan != null) || (this.idBiayaTambahan != null && !this.idBiayaTambahan.equals(other.idBiayaTambahan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelBiayaTambahan[ idBiayaTambahan=" + idBiayaTambahan + " ]";
    }

}
