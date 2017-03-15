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
import javax.persistence.Transient;

/**
 *
 * @author jizid
 */
@Entity
@Table(name = "clientedepartamento")
public class Clientedepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclientedepartamento")
    private Integer idclientedepartamento;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne(optional = false)
    private Departamento departamento;
    @Transient
    private Rotinacliente rotinacliente;
    @Transient
    private boolean imagemcinza;
    @Transient
    private boolean imagemvermelha;
    @Transient
    private boolean imagemverde; 

    public Clientedepartamento() {
    }

    public Clientedepartamento(Integer idclientedepartamento) {
        this.idclientedepartamento = idclientedepartamento;
    }

    public Integer getIdclientedepartamento() {
        return idclientedepartamento;
    }

    public void setIdclientedepartamento(Integer idclientedepartamento) {
        this.idclientedepartamento = idclientedepartamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Rotinacliente getRotinacliente() {
        return rotinacliente;
    }

    public void setRotinacliente(Rotinacliente rotinacliente) {
        this.rotinacliente = rotinacliente;
    }

    public boolean isImagemcinza() {
        return imagemcinza;
    }

    public void setImagemcinza(boolean imagemcinza) {
        this.imagemcinza = imagemcinza;
    }

    public boolean isImagemvermelha() {
        return imagemvermelha;
    }

    public void setImagemvermelha(boolean imagemvermelha) {
        this.imagemvermelha = imagemvermelha;
    }

    public boolean isImagemverde() {
        return imagemverde;
    }

    public void setImagemverde(boolean imagemverde) {
        this.imagemverde = imagemverde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclientedepartamento != null ? idclientedepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientedepartamento)) {
            return false;
        }
        Clientedepartamento other = (Clientedepartamento) object;
        if ((this.idclientedepartamento == null && other.idclientedepartamento != null) || (this.idclientedepartamento != null && !this.idclientedepartamento.equals(other.idclientedepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Clientedepartamento[ idclientedepartamento=" + idclientedepartamento + " ]";
    }
    
}
