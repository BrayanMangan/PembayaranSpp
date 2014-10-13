package com.yf.kp.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author BlackCode
 */
@Entity
@Table(name = "tabel_agama")
@NamedQueries({
    @NamedQuery(name = "TabelAgama.findAll", query = "SELECT t FROM TabelAgama t")})
public class TabelAgama implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_agama")
    private Integer idAgama;
    @Basic(optional = false)
    @Column(name = "nama_agama")
    private String namaAgama;

    public TabelAgama() {
    }

    public TabelAgama(Integer idAgama) {
        this.idAgama = idAgama;
    }

    public TabelAgama(Integer idAgama, String namaAgama) {
        this.idAgama = idAgama;
        this.namaAgama = namaAgama;
    }

    public Integer getIdAgama() {
        return idAgama;
    }

    public void setIdAgama(Integer idAgama) {
        this.idAgama = idAgama;
    }

    public String getNamaAgama() {
        return namaAgama;
    }

    public void setNamaAgama(String namaAgama) {
        this.namaAgama = namaAgama;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgama != null ? idAgama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelAgama)) {
            return false;
        }
        TabelAgama other = (TabelAgama) object;
        if ((this.idAgama == null && other.idAgama != null) || (this.idAgama != null && !this.idAgama.equals(other.idAgama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelAgama[ idAgama=" + idAgama + " ]";
    }

}
