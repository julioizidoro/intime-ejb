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
@Table(name = "rotinasemanal")
public class Rotinasemanal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinasemanal")
    private Integer idrotinasemanal;
    @Column(name = "numerosemanas")
    private Integer numerosemanas;
    @Column(name = "domingo")
    private boolean domingo;
    @Column(name = "segunda")
    private boolean segunda;
    @Column(name = "terca")
    private boolean terca;
    @Column(name = "quarta")
    private boolean quarta;
    @Column(name = "quinta")
    private boolean quinta;
    @Column(name = "sexta")
    private boolean sexta;
    @Column(name = "sabado")
    private boolean sabado;
    @JoinColumn(name = "rotinacliente_idrotinacliente", referencedColumnName = "idrotinacliente")
    @ManyToOne(optional = false)
    private Rotinacliente rotinacliente;

    public Rotinasemanal() {
    }

    public Rotinasemanal(Integer idrotinasemanal) {
        this.idrotinasemanal = idrotinasemanal;
    }

    public Integer getIdrotinasemanal() {
        return idrotinasemanal;
    }

    public void setIdrotinasemanal(Integer idrotinasemanal) {
        this.idrotinasemanal = idrotinasemanal;
    }

    public Integer getNumerosemanas() {
        return numerosemanas;
    }

    public void setNumerosemanas(Integer numerosemanas) {
        this.numerosemanas = numerosemanas;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public boolean isSegunda() {
        return segunda;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    public boolean isTerca() {
        return terca;
    }

    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    public boolean isQuarta() {
        return quarta;
    }

    public void setQuarta(boolean quarta) {
        this.quarta = quarta;
    }

    public boolean isQuinta() {
        return quinta;
    }

    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    public boolean isSexta() {
        return sexta;
    }

    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
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
        hash += (idrotinasemanal != null ? idrotinasemanal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinasemanal)) {
            return false;
        }
        Rotinasemanal other = (Rotinasemanal) object;
        if ((this.idrotinasemanal == null && other.idrotinasemanal != null) || (this.idrotinasemanal != null && !this.idrotinasemanal.equals(other.idrotinasemanal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Rotinasemanal[ idrotinasemanal=" + idrotinasemanal + " ]";
    }
    
}
