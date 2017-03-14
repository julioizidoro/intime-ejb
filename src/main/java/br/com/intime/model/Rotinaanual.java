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
@Table(name = "rotinaanual") 
public class Rotinaanual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinaanual")
    private Integer idrotinaanual;
    @Column(name = "numeroano")
    private Integer numeroano;
    @Column(name = "emmesdia")
    private boolean emmesdia;
    @Size(max = 10)
    @Column(name = "mesem")
    private String mesem;
    @Column(name = "diaem")
    private Integer diaem;
    @Column(name = "nodiasemanames")
    private boolean nodiasemanames;
    @Size(max = 12)
    @Column(name = "numerosemana")
    private String numerosemana;
    @Size(max = 15)
    @Column(name = "diasemana")
    private String diasemana;
    @Size(max = 10)
    @Column(name = "mesno")
    private String mesno;
    @JoinColumn(name = "rotinacliente_idrotinacliente", referencedColumnName = "idrotinacliente")
    @ManyToOne(optional = false)
    private Rotinacliente rotinacliente;

    public Rotinaanual() {
    }

    public Rotinaanual(Integer idrotinaanual) {
        this.idrotinaanual = idrotinaanual;
    }

    public Integer getIdrotinaanual() {
        return idrotinaanual;
    }

    public void setIdrotinaanual(Integer idrotinaanual) {
        this.idrotinaanual = idrotinaanual;
    }

    public Integer getNumeroano() {
        return numeroano;
    }

    public void setNumeroano(Integer numeroano) {
        this.numeroano = numeroano;
    }

    public boolean isEmmesdia() {
        return emmesdia;
    }

    public void setEmmesdia(boolean emmesdia) {
        this.emmesdia = emmesdia;
    }

    public String getMesem() {
        return mesem;
    }

    public void setMesem(String mesem) {
        this.mesem = mesem;
    }

    public Integer getDiaem() {
        return diaem;
    }

    public void setDiaem(Integer diaem) {
        this.diaem = diaem;
    }

    public boolean isNodiasemanames() {
        return nodiasemanames;
    }

    public void setNodiasemanames(boolean nodiasemanames) {
        this.nodiasemanames = nodiasemanames;
    }

    public String getNumerosemana() {
        return numerosemana;
    }

    public void setNumerosemana(String numerosemana) {
        this.numerosemana = numerosemana;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public String getMesno() {
        return mesno;
    }

    public void setMesno(String mesno) {
        this.mesno = mesno;
    }

    public Rotinacliente getRotinacliente() {
        return rotinacliente;
    }

    public void setRotinacliente(Rotinacliente rotinacliente) {
        this.rotinacliente = rotinacliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrotinaanual != null ? idrotinaanual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinaanual)) {
            return false;
        }
        Rotinaanual other = (Rotinaanual) object;
        if ((this.idrotinaanual == null && other.idrotinaanual != null) || (this.idrotinaanual != null && !this.idrotinaanual.equals(other.idrotinaanual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Rotinaanual[ idrotinaanual=" + idrotinaanual + " ]";
    }
    
}
