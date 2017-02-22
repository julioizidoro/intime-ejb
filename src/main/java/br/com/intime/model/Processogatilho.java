/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "processogatilho")
public class Processogatilho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocessogatilho")
    private Integer idprocessogatilho;
    @Column(name = "executado")
    private Boolean executado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processogatilho")
    private List<Processoatividadegatilho> processoatividadegatilhoList;
    @JoinColumn(name = "processorotina_idprocessorotina", referencedColumnName = "idprocessorotina")
    @ManyToOne(optional = false)
    private Processorotina processorotina;
    @JoinColumn(name = "processosituacao_idprocessosituacao", referencedColumnName = "idprocessosituacao")
    @ManyToOne(optional = false)
    private Processosituacao processosituacao;

    public Processogatilho() {
    }

    public Processogatilho(Integer idprocessogatilho) {
        this.idprocessogatilho = idprocessogatilho;
    }

    public Integer getIdprocessogatilho() {
        return idprocessogatilho;
    }

    public void setIdprocessogatilho(Integer idprocessogatilho) {
        this.idprocessogatilho = idprocessogatilho;
    }

    public Boolean getExecutado() {
        return executado;
    }

    public void setExecutado(Boolean executado) {
        this.executado = executado;
    }

    public List<Processoatividadegatilho> getProcessoatividadegatilhoList() {
        return processoatividadegatilhoList;
    }

    public void setProcessoatividadegatilhoList(List<Processoatividadegatilho> processoatividadegatilhoList) {
        this.processoatividadegatilhoList = processoatividadegatilhoList;
    }

    public Processorotina getProcessorotina() {
        return processorotina;
    }

    public void setProcessorotina(Processorotina processorotina) {
        this.processorotina = processorotina;
    }

    public Processosituacao getProcessosituacao() {
        return processosituacao;
    }

    public void setProcessosituacao(Processosituacao processosituacao) {
        this.processosituacao = processosituacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocessogatilho != null ? idprocessogatilho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processogatilho)) {
            return false;
        }
        Processogatilho other = (Processogatilho) object;
        if ((this.idprocessogatilho == null && other.idprocessogatilho != null) || (this.idprocessogatilho != null && !this.idprocessogatilho.equals(other.idprocessogatilho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Processogatilho[ idprocessogatilho=" + idprocessogatilho + " ]";
    }
    
}
