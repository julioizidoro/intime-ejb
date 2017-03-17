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
@Table(name = "rotinadiaria")
public class Rotinadiaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinadiaria")
    private Integer idrotinadiaria;
    @Column(name = "acadadia")
    private boolean acadadia;
    @Column(name = "numerodias")
    private Integer numerodias;
    @Column(name = "todosdias")
    private boolean todosdias;
    @JoinColumn(name = "rotinacliente_idrotinacliente", referencedColumnName = "idrotinacliente")
    @ManyToOne(optional = false)
    private Rotinacliente rotinacliente;

    public Rotinadiaria() {
    }

    public Rotinadiaria(Integer idrotinadiaria) {
        this.idrotinadiaria = idrotinadiaria;
    }

    public Integer getIdrotinadiaria() {
        return idrotinadiaria;
    }

    public void setIdrotinadiaria(Integer idrotinadiaria) {
        this.idrotinadiaria = idrotinadiaria;
    }

    public Integer getNumerodias() {
        return numerodias;
    }

    public void setNumerodias(Integer numerodias) {
        this.numerodias = numerodias;
    }

    public boolean isAcadadia() {
        return acadadia;
    }

    public void setAcadadia(boolean acadadia) {
        this.acadadia = acadadia;
    }

    public boolean isTodosdias() {
        return todosdias;
    }

    public void setTodosdias(boolean todosdias) {
        this.todosdias = todosdias;
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
        hash += (idrotinadiaria != null ? idrotinadiaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinadiaria)) {
            return false;
        }
        Rotinadiaria other = (Rotinadiaria) object;
        if ((this.idrotinadiaria == null && other.idrotinadiaria != null) || (this.idrotinadiaria != null && !this.idrotinadiaria.equals(other.idrotinadiaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Rotinadiaria[ idrotinadiaria=" + idrotinadiaria + " ]";
    }
    
}
