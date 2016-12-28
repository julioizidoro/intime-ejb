/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "acesso")
public class Acesso implements Serializable {
 
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idacesso")
    private Integer idacesso;
    @Column(name = "cadastrocliente")
    private Integer cadastrocliente;
    @Column(name = "cadastrorotina")
    private Integer cadastrorotina;
    @Column(name = "cadastroprocesso")
    private Integer cadastroprocesso;
    @Column(name = "cadastrousuairo")
    private Integer cadastrousuairo;

    public Acesso() {
    }

    public Acesso(Integer idacesso) {
        this.idacesso = idacesso;
    }

    public Integer getIdacesso() {
        return idacesso;
    }

    public void setIdacesso(Integer idacesso) {
        this.idacesso = idacesso;
    }

    public Integer getCadastrocliente() {
        return cadastrocliente;
    }

    public void setCadastrocliente(Integer cadastrocliente) {
        this.cadastrocliente = cadastrocliente;
    }

    public Integer getCadastrorotina() {
        return cadastrorotina;
    }

    public void setCadastrorotina(Integer cadastrorotina) {
        this.cadastrorotina = cadastrorotina;
    }

    public Integer getCadastroprocesso() {
        return cadastroprocesso;
    }

    public void setCadastroprocesso(Integer cadastroprocesso) {
        this.cadastroprocesso = cadastroprocesso;
    }

    public Integer getCadastrousuairo() {
        return cadastrousuairo;
    }

    public void setCadastrousuairo(Integer cadastrousuairo) {
        this.cadastrousuairo = cadastrousuairo;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idacesso != null ? idacesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acesso)) {
            return false;
        }
        Acesso other = (Acesso) object;
        if ((this.idacesso == null && other.idacesso != null) || (this.idacesso != null && !this.idacesso.equals(other.idacesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Acesso[ idacesso=" + idacesso + " ]";
    }     

}
