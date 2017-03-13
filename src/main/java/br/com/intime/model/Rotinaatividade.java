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
 * @author Kamila
 */
@Entity
@Table(name = "rotinaatividade")
public class Rotinaatividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinaatividade")
    private Integer idrotinaatividade;
    @JoinColumn(name = "atividade_idatividade", referencedColumnName = "idatividade")
    @ManyToOne(optional = false)
    private Atividade atividade;
    @JoinColumn(name = "rotina_idrotina", referencedColumnName = "idrotinacliente")
    @ManyToOne(optional = false)
    private Rotinacliente rotina;

    public Rotinaatividade() {
    }

    public Rotinaatividade(Integer idrotinaatividade) {
        this.idrotinaatividade = idrotinaatividade;
    }

    public Integer getIdrotinaatividade() {
        return idrotinaatividade;
    }

    public void setIdrotinaatividade(Integer idrotinaatividade) {
        this.idrotinaatividade = idrotinaatividade;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividadeIdatividade) {
        this.atividade = atividadeIdatividade;
    }

    public Rotinacliente getRotina() {
        return rotina;
    }

    public void setRotina(Rotinacliente rotina) {
        this.rotina = rotina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrotinaatividade != null ? idrotinaatividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinaatividade)) {
            return false;
        }
        Rotinaatividade other = (Rotinaatividade) object;
        if ((this.idrotinaatividade == null && other.idrotinaatividade != null) || (this.idrotinaatividade != null && !this.idrotinaatividade.equals(other.idrotinaatividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Rotinaatividade[ idrotinaatividade=" + idrotinaatividade + " ]";
    }
    
}
