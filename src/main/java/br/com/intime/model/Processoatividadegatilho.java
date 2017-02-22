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
 * @author Wolverine
 */
@Entity
@Table(name = "processoatividadegatilho")
public class Processoatividadegatilho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocessoatividadegatilho")
    private Integer idprocessoatividadegatilho;
    @JoinColumn(name = "processoatividade_idprocessoatividade", referencedColumnName = "idprocessoatividade")
    @ManyToOne(optional = false)
    private Processoatividade processoatividade;
    @JoinColumn(name = "processogatilho_idprocessogatilho", referencedColumnName = "idprocessogatilho")
    @ManyToOne(optional = false)
    private Processogatilho processogatilho;

    public Processoatividadegatilho() {
    }

    public Processoatividadegatilho(Integer idprocessoatividadegatilho) {
        this.idprocessoatividadegatilho = idprocessoatividadegatilho;
    }

    public Integer getIdprocessoatividadegatilho() {
        return idprocessoatividadegatilho;
    }

    public void setIdprocessoatividadegatilho(Integer idprocessoatividadegatilho) {
        this.idprocessoatividadegatilho = idprocessoatividadegatilho;
    }

    public Processoatividade getProcessoatividade() {
        return processoatividade;
    }

    public void setProcessoatividade(Processoatividade processoatividade) {
        this.processoatividade = processoatividade;
    }

    public Processogatilho getProcessogatilho() {
        return processogatilho;
    }

    public void setProcessogatilho(Processogatilho processogatilho) {
        this.processogatilho = processogatilho;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocessoatividadegatilho != null ? idprocessoatividadegatilho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processoatividadegatilho)) {
            return false;
        }
        Processoatividadegatilho other = (Processoatividadegatilho) object;
        if ((this.idprocessoatividadegatilho == null && other.idprocessoatividadegatilho != null) || (this.idprocessoatividadegatilho != null && !this.idprocessoatividadegatilho.equals(other.idprocessoatividadegatilho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Processoatividadegatilho[ idprocessoatividadegatilho=" + idprocessoatividadegatilho + " ]";
    }
    
}
