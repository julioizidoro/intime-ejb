/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jizid
 */
@Entity
@Table(name = "motivoatraso")
public class Motivoatraso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmotivoatraso")
    private Integer idmotivoatraso;
    @Size(max = 50)
    @Column(name = "descricao")
    private String descricao;

    public Motivoatraso() {
    }

    public Motivoatraso(Integer idmotivoatraso) {
        this.idmotivoatraso = idmotivoatraso;
    }

    public Integer getIdmotivoatraso() {
        return idmotivoatraso;
    }

    public void setIdmotivoatraso(Integer idmotivoatraso) {
        this.idmotivoatraso = idmotivoatraso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmotivoatraso != null ? idmotivoatraso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motivoatraso)) {
            return false;
        }
        Motivoatraso other = (Motivoatraso) object;
        if ((this.idmotivoatraso == null && other.idmotivoatraso != null) || (this.idmotivoatraso != null && !this.idmotivoatraso.equals(other.idmotivoatraso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Motivoatraso[ idmotivoatraso=" + idmotivoatraso + " ]";
    }
    
}
