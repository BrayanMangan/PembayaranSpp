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
@Table(name = "tabel_konfig")
@NamedQueries({
    @NamedQuery(name = "TabelKonfig.findAll", query = "SELECT t FROM TabelKonfig t")})
public class TabelKonfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_konfig")
    private Integer idKonfig;
    @Basic(optional = false)
    @Column(name = "tahun_ajaran")
    private String tahunAjaran;
    @Basic(optional = false)
    @Column(name = "semester")
    private String semester;

    public TabelKonfig() {
    }

    public TabelKonfig(Integer idKonfig) {
        this.idKonfig = idKonfig;
    }

    public TabelKonfig(Integer idKonfig, String tahunAjaran, String semester) {
        this.idKonfig = idKonfig;
        this.tahunAjaran = tahunAjaran;
        this.semester = semester;
    }

    public Integer getIdKonfig() {
        return idKonfig;
    }

    public void setIdKonfig(Integer idKonfig) {
        this.idKonfig = idKonfig;
    }

    public String getTahunAjaran() {
        return tahunAjaran;
    }

    public void setTahunAjaran(String tahunAjaran) {
        this.tahunAjaran = tahunAjaran;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKonfig != null ? idKonfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabelKonfig)) {
            return false;
        }
        TabelKonfig other = (TabelKonfig) object;
        if ((this.idKonfig == null && other.idKonfig != null) || (this.idKonfig != null && !this.idKonfig.equals(other.idKonfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.yf.kp.model.TabelKonfig[ idKonfig=" + idKonfig + " ]";
    }

}
