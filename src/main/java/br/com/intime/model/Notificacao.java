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
 * @author Kamila
 */
@Entity
@Table(name = "notificacao")
public class Notificacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnotificacao")
    private Integer idnotificacao;
    @Size(max = 200)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "lido")
    private Boolean lido;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Notificacao() {
    }

    public Notificacao(Integer idnotificacao) {
        this.idnotificacao = idnotificacao;
    }

    public Integer getIdnotificacao() {
        return idnotificacao;
    }

    public void setIdnotificacao(Integer idnotificacao) {
        this.idnotificacao = idnotificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getLido() {
        return lido;
    }

    public void setLido(Boolean lido) {
        this.lido = lido;
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
        hash += (idnotificacao != null ? idnotificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacao)) {
            return false;
        }
        Notificacao other = (Notificacao) object;
        if ((this.idnotificacao == null && other.idnotificacao != null) || (this.idnotificacao != null && !this.idnotificacao.equals(other.idnotificacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Notificacao[ idnotificacao=" + idnotificacao + " ]";
    }
    
}
