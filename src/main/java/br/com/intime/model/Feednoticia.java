/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author jizid
 */
@Entity
@Table(name = "feednoticia")
public class Feednoticia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfeednoticia")
    private Integer idfeednoticia;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "data", nullable = false)
    private Date data;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Feednoticia() {
    }

    public Feednoticia(Integer idfeednoticia) {
        this.idfeednoticia = idfeednoticia;
    }

    public Integer getIdfeednoticia() {
        return idfeednoticia;
    }

    public void setIdfeednoticia(Integer idfeednoticia) {
        this.idfeednoticia = idfeednoticia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfeednoticia != null ? idfeednoticia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feednoticia)) {
            return false;
        }
        Feednoticia other = (Feednoticia) object;
        if ((this.idfeednoticia == null && other.idfeednoticia != null) || (this.idfeednoticia != null && !this.idfeednoticia.equals(other.idfeednoticia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Feednoticia[ idfeednoticia=" + idfeednoticia + " ]";
    }
    
}
