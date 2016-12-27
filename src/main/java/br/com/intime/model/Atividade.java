/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.util.Date;
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
 * @author jizid
 */
@Entity
@Table(name = "atividade")
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatividade")
    private Integer idatividade;
    @Size(max = 100)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "datalancamento", nullable = false)
    private Date datalancamento;
    @Column(name = "dataexecucao", nullable = false)
    private Date dataexecucao;
    @Size(max = 7)
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "metatempo" , nullable = false)
    private Date metatempo;
    @Column(name = "rotina")
    private Boolean rotina;
    @Column(name = "notificacaohorario")
    private Boolean notificacaohorario;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "subdepartamento_idsubdepartamento", referencedColumnName = "idsubdepartamento")
    @ManyToOne(optional = false)
    private Subdepartamento subdepartamento;
    
    public Atividade() {
    }

    public Atividade(Integer idatividade) {
        this.idatividade = idatividade;
    }

    public Integer getIdatividade() {
        return idatividade;
    }

    public void setIdatividade(Integer idatividade) {
        this.idatividade = idatividade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDatalancamento() {
        return datalancamento;
    }

    public void setDatalancamento(Date datalancamento) {
        this.datalancamento = datalancamento;
    }

    public Date getDataexecucao() {
        return dataexecucao;
    }

    public void setDataexecucao(Date dataexecucao) {
        this.dataexecucao = dataexecucao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Date getMetatempo() {
        return metatempo;
    }

    public void setMetatempo(Date metatempo) {
        this.metatempo = metatempo;
    }

    public Boolean getRotina() {
        return rotina;
    }

    public void setRotina(Boolean rotina) {
        this.rotina = rotina;
    }

    public Boolean getNotificacaohorario() {
        return notificacaohorario;
    }

    public void setNotificacaohorario(Boolean notificacaohorario) {
        this.notificacaohorario = notificacaohorario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatividade != null ? idatividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividade)) {
            return false;
        }
        Atividade other = (Atividade) object;
        if ((this.idatividade == null && other.idatividade != null) || (this.idatividade != null && !this.idatividade.equals(other.idatividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Atividade[ idatividade=" + idatividade + " ]";
    }

}
