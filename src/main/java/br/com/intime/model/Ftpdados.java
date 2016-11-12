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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jizid
 */
@Entity
@Table(name = "ftpdados")
public class Ftpdados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idftpdados")
    private Integer idftpdados;
    @Size(max = 100)
    @Column(name = "hostdonwload")
    private String hostdonwload;
    @Size(max = 100)
    @Column(name = "hostupload")
    private String hostupload;
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 50)
    @Column(name = "senha")
    private String senha;
    @JoinColumn(name = "empresa_idempresa", referencedColumnName = "idempresa")
    @OneToOne(optional = false)
    private Empresa empresa;

    public Ftpdados() {
    }

    public Ftpdados(Integer idftpdados) {
        this.idftpdados = idftpdados;
    }

    public Integer getIdftpdados() {
        return idftpdados;
    }

    public void setIdftpdados(Integer idftpdados) {
        this.idftpdados = idftpdados;
    }

    public String getHostdonwload() {
        return hostdonwload;
    }

    public void setHostdonwload(String hostdonwload) {
        this.hostdonwload = hostdonwload;
    }

    public String getHostupload() {
        return hostupload;
    }

    public void setHostupload(String hostupload) {
        this.hostupload = hostupload;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idftpdados != null ? idftpdados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ftpdados)) {
            return false;
        }
        Ftpdados other = (Ftpdados) object;
        if ((this.idftpdados == null && other.idftpdados != null) || (this.idftpdados != null && !this.idftpdados.equals(other.idftpdados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Ftpdados[ idftpdados=" + idftpdados + " ]";
    }
    
}
