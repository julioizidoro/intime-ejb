/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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
    @Size(max = 15)
    @Column(name = "prioridade")
    private String prioridade;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "subdepartamento_idsubdepartamento", referencedColumnName = "idsubdepartamento")
    @ManyToOne(optional = false)
    private Subdepartamento subdepartamento;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @Column(name = "datalancamento")
    private LocalDate datalancamento;
    @Column(name = "dataexecucao")
    private LocalDate dataexecucao;
    @Column(name = "horaexecucao")
    private LocalTime horaexecucao;
    @Column(name = "metatempo")
    private LocalTime metatempo; 
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "atividade")
    private Rotinaatividade rotinaatividade;
    @Transient
    private String dataMostrar;
    @Transient 
    private String horaMostrar;
    @Transient 
    private Date dataexecutar;
    @Transient 
    private String horario;
    @Transient 
    private String meta;
    @Column(name = "rotina")
    private boolean rotina;
    @Column(name = "notificacaohorario")
    private boolean notificacaohorario;
    
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

 
    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
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

    public String getDataMostrar() {
        return dataMostrar;
    }

    public void setDataMostrar(String dataMostrar) {
        this.dataMostrar = dataMostrar;
    }

    public String getHoraMostrar() {
        return horaMostrar;
    }

    public void setHoraMostrar(String horaMostrar) {
        this.horaMostrar = horaMostrar;
    }

    public Date getDataexecutar() {
        return dataexecutar;
    }

    public void setDataexecutar(Date dataexecutar) {
        this.dataexecutar = dataexecutar;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDatalancamento() {
        return datalancamento;
    }

    public void setDatalancamento(LocalDate datalancamento) {
        this.datalancamento = datalancamento;
    }

    public LocalDate getDataexecucao() {
        return dataexecucao;
    }

    public void setDataexecucao(LocalDate dataexecucao) {
        this.dataexecucao = dataexecucao;
    }

    public LocalTime getHoraexecucao() {
        return horaexecucao;
    }

    public void setHoraexecucao(LocalTime horaexecucao) {
        this.horaexecucao = horaexecucao;
    }

    public LocalTime getMetatempo() {
        return metatempo;
    }

    public void setMetatempo(LocalTime metatempo) {
        this.metatempo = metatempo;
    } 

    public boolean isRotina() {
        return rotina;
    }

    public void setRotina(boolean rotina) {
        this.rotina = rotina;
    }

    public boolean isNotificacaohorario() {
        return notificacaohorario;
    }

    public void setNotificacaohorario(boolean notificacaohorario) {
        this.notificacaohorario = notificacaohorario;
    }

    public Rotinaatividade getRotinaatividade() {
        return rotinaatividade;
    }

    public void setRotinaatividade(Rotinaatividade rotinaatividade) {
        this.rotinaatividade = rotinaatividade;
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
