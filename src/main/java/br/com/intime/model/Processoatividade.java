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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "processoatividade")
public class Processoatividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocessoatividade")
    private Integer idprocessoatividade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processoatividade")
    private List<Processoatividadegatilho> processoatividadegatilhoList;
    @JoinColumn(name = "processorotina_idprocessorotina", referencedColumnName = "idprocessorotina")
    @ManyToOne(optional = false)
    private Processorotina processorotina;
    @JoinColumn(name = "processosituacao_idprocessosituacao", referencedColumnName = "idprocessosituacao")
    @ManyToOne(optional = false)
    private Processosituacao processosituacao;
    @JoinColumn(name = "atividadeusuario_idatividadeusuario", referencedColumnName = "idatividadeusuario")
    @OneToOne(optional = false)
    private Atividadeusuario atividadeusuario;

    public Processoatividade() {
    }

    public Processoatividade(Integer idprocessoatividade) {
        this.idprocessoatividade = idprocessoatividade;
    }

    public Integer getIdprocessoatividade() {
        return idprocessoatividade;
    }

    public void setIdprocessoatividade(Integer idprocessoatividade) {
        this.idprocessoatividade = idprocessoatividade;
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

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocessoatividade != null ? idprocessoatividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processoatividade)) {
            return false;
        }
        Processoatividade other = (Processoatividade) object;
        if ((this.idprocessoatividade == null && other.idprocessoatividade != null) || (this.idprocessoatividade != null && !this.idprocessoatividade.equals(other.idprocessoatividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Processoatividade[ idprocessoatividade=" + idprocessoatividade + " ]";
    }
    
}
