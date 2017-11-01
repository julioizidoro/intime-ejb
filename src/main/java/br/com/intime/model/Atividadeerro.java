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

/**
 *
 * @author Anderson
 */

@Entity
@Table(name = "atividadeerro")
public class Atividadeerro implements Serializable{
    
    
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatividadeerro")
    private Integer idatividadeerro;
    @Column(name = "tipoerro")
    private String tipoerro;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "atividadeusuario_idatividadeusuario", referencedColumnName = "idatividadeusuario")
    @ManyToOne(optional = false)
    private Atividadeusuario atividadeusuario;

    public Atividadeerro() {
    }

    
    
    public Integer getIdatividadeerro() {
        return idatividadeerro;
    }

    public void setIdatividadeerro(Integer idatividadeerro) {
        this.idatividadeerro = idatividadeerro;
    }

    public String getTipoerro() {
        return tipoerro;
    }

    public void setTipoerro(String tipoerro) {
        this.tipoerro = tipoerro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash += (idatividadeerro != null ? idatividadeerro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividadeusuario)) {
            return false;
        }
        Atividadeerro other = (Atividadeerro) object;
        if ((this.idatividadeerro == null && other.idatividadeerro != null) || (this.idatividadeerro != null && !this.idatividadeerro.equals(other.idatividadeerro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Atividadeerro[ idatividadeerro=" + idatividadeerro + " ]";
    }
    
}
