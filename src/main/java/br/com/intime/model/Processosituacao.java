/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "processosituacao")
public class Processosituacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprocessosituacao")
    private Integer idprocessosituacao;
    @Column(name = "datainicio")
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Column(name = "datatermino")
    @Temporal(TemporalType.DATE)
    private Date datatermino;
    @Size(max = 15)
    @Column(name = "situacao")
    private String situacao;
    @JoinColumn(name = "processo_idprocesso", referencedColumnName = "idprocesso")
    @ManyToOne(optional = false)
    private Processo processo;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processosituacao")
    private List<Processogatilho> processogatilhoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "processosituacao")
    private List<Processoatividade> processoatividadeList;

    public Processosituacao() {
    }

    public Processosituacao(Integer idprocessosituacao) {
        this.idprocessosituacao = idprocessosituacao;
    }

    public Integer getIdprocessosituacao() {
        return idprocessosituacao;
    }

    public void setIdprocessosituacao(Integer idprocessosituacao) {
        this.idprocessosituacao = idprocessosituacao;
    }

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public Date getDatatermino() {
        return datatermino;
    }

    public void setDatatermino(Date datatermino) {
        this.datatermino = datatermino;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprocessosituacao != null ? idprocessosituacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processosituacao)) {
            return false;
        }
        Processosituacao other = (Processosituacao) object;
        if ((this.idprocessosituacao == null && other.idprocessosituacao != null) || (this.idprocessosituacao != null && !this.idprocessosituacao.equals(other.idprocessosituacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Processosituacao[ idprocessosituacao=" + idprocessosituacao + " ]";
    }
    
}
