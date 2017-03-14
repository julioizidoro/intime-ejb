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
@Table(name = "rotinamensal")
public class Rotinamensal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinamensal")
    private Integer idrotinamensal;
    @Column(name = "diacadames")
    private boolean diacadames;
    @Column(name = "diames")
    private Integer diames;
    @Column(name = "numeromesesdia")
    private Integer numeromesesdia;
    @Column(name = "nocadames")
    private boolean nocadames;
    @Size(max = 12)
    @Column(name = "numerosemana")
    private String numerosemana;
    @Size(max = 45)
    @Column(name = "diasemana")
    private String diasemana; 
    @Column(name = "numeromesesno")
    private Integer numeromesesno;
    @JoinColumn(name = "rotinacliente_idrotinacliente", referencedColumnName = "idrotinacliente")
    @ManyToOne(optional = false)
    private Rotinacliente rotinacliente;

    public Rotinamensal() {
    }

    public Rotinamensal(Integer idrotinamensal) {
        this.idrotinamensal = idrotinamensal;
    }

    public Integer getIdrotinamensal() {
        return idrotinamensal;
    }

    public void setIdrotinamensal(Integer idrotinamensal) {
        this.idrotinamensal = idrotinamensal;
    }

    public boolean isDiacadames() {
        return diacadames;
    }

    public void setDiacadames(boolean diacadames) {
        this.diacadames = diacadames;
    }

    public Integer getDiames() {
        return diames;
    }

    public void setDiames(Integer diames) {
        this.diames = diames;
    }

    public Integer getNumeromesesdia() {
        return numeromesesdia;
    }

    public void setNumeromesesdia(Integer numeromesesdia) {
        this.numeromesesdia = numeromesesdia;
    }

    public boolean isNocadames() {
        return nocadames;
    }

    public void setNocadames(boolean nocadames) {
        this.nocadames = nocadames;
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

    public Integer getNumeromesesno() {
        return numeromesesno;
    }

    public void setNumeromesesno(Integer numeromesesno) {
        this.numeromesesno = numeromesesno;
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
        hash += (idrotinamensal != null ? idrotinamensal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinamensal)) {
            return false;
        }
        Rotinamensal other = (Rotinamensal) object;
        if ((this.idrotinamensal == null && other.idrotinamensal != null) || (this.idrotinamensal != null && !this.idrotinamensal.equals(other.idrotinamensal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Rotinamensal[ idrotinamensal=" + idrotinamensal + " ]";
    }
    
}
