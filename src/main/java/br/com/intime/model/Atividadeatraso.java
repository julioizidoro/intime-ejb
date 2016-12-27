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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jizid
 */
@Entity
@Table(name = "atividadeatraso")
public class Atividadeatraso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatividadeatraso")
    private Integer idatividadeatraso;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "motivoatraso_idmotivoatraso", referencedColumnName = "idmotivoatraso")
    @ManyToOne(optional = false)
    private Motivoatraso motivoatraso;
    @JoinColumn(name = "atividadeusuario_idatividadeusuario", referencedColumnName = "idatividadeusuario")
    @ManyToOne(optional = false)
    private Atividadeusuario atividadeusuario;

    public Atividadeatraso() {
    }

    public Atividadeatraso(Integer idatividadeatraso) {
        this.idatividadeatraso = idatividadeatraso;
    }

    public Integer getIdatividadeatraso() {
        return idatividadeatraso;
    }

    public void setIdatividadeatraso(Integer idatividadeatraso) {
        this.idatividadeatraso = idatividadeatraso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Motivoatraso getMotivoatraso() {
        return motivoatraso;
    }

    public void setMotivoatraso(Motivoatraso motivoatraso) {
        this.motivoatraso = motivoatraso;
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatividadeatraso != null ? idatividadeatraso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividadeatraso)) {
            return false;
        }
        Atividadeatraso other = (Atividadeatraso) object;
        if ((this.idatividadeatraso == null && other.idatividadeatraso != null) || (this.idatividadeatraso != null && !this.idatividadeatraso.equals(other.idatividadeatraso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Atividadeatraso[ idatividadeatraso=" + idatividadeatraso + " ]";
    }
    
}
