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
@Table(name = "tabel_bulan")
@NamedQueries({
    @NamedQuery(name = "TabelBulan.findAll", query = "SELECT t FROM TabelBulan t")})
public class TabelBulan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "bulan")
    private String bulan;

    public TabelBulan() {
    }

    public TabelBulan(Integer id) {
        this.id = id;
    }

    public TabelBulan(Integer id, String bulan) {
        this.id = id;
        this.bulan = bulan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelBulan)) {
            return false;
        }
        TabelBulan other = (TabelBulan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelBulan[ id=" + id + " ]";
    }

}
