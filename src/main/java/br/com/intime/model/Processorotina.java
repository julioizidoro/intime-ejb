/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "processorotina")
public class Processorotina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocessorotina")
    private Integer idprocessorotina;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "diasuteis")
    private Integer diasuteis;
    @JoinColumn(name = "processo_idprocesso", referencedColumnName = "idprocesso")
    @ManyToOne(optional = false)
    private Processo processo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processorotina")
    private List<Processogatilho> processogatilhoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processorotina")
    private List<Processoatividade> processoatividadeList;
    @Transient
    private LocalDate data;
    @Transient
    private Date datamostrar;
    @Transient
    private Usuario usuario;
    @Transient
    private boolean selecionado;

    public Processorotina() {
    }

    public Processorotina(Integer idprocessorotina) {
        this.idprocessorotina = idprocessorotina;
    }

    public Integer getIdprocessorotina() {
        return idprocessorotina;
    }

    public void setIdprocessorotina(Integer idprocessorotina) {
        this.idprocessorotina = idprocessorotina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDiasuteis() {
        return diasuteis;
    }

    public void setDiasuteis(Integer diasuteis) {
        this.diasuteis = diasuteis;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Processogatilho> getProcessogatilhoList() {
        return processogatilhoList;
    }

    public void setProcessogatilhoList(List<Processogatilho> processogatilhoList) {
        this.processogatilhoList = processogatilhoList;
    }

    public List<Processoatividade> getProcessoatividadeList() {
        return processoatividadeList;
    }

    public void setProcessoatividadeList(List<Processoatividade> processoatividadeList) {
        this.processoatividadeList = processoatividadeList;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public Date getDatamostrar() {
        return datamostrar;
    }

    public void setDatamostrar(Date datamostrar) {
        this.datamostrar = datamostrar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocessorotina != null ? idprocessorotina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processorotina)) {
            return false;
        }
        Processorotina other = (Processorotina) object;
        if ((this.idprocessorotina == null && other.idprocessorotina != null) || (this.idprocessorotina != null && !this.idprocessorotina.equals(other.idprocessorotina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Processorotina[ idprocessorotina=" + idprocessorotina + " ]";
    }
    
}
