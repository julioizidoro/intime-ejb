/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
 * @author jizid
 */
@Entity
@Table(name = "atividadeusuario")
public class Atividadeusuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatividadeusuario")
    private Integer idatividadeusuario;
    @Size(max = 50)
    @Column(name = "situacao")
    private String situacao;
    @Column(name = "dataconclusao")
    private LocalDate dataconclusao;
    @Column(name = "horaconclusao")
    private LocalTime horaconclusao;
    @Size(max = 5)
    @Column(name = "tempo")
    private String tempo;
    @JoinColumn(name = "atividade_idatividade", referencedColumnName = "idatividade")
    @ManyToOne(optional = false)
    private Atividade atividade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividadeusuario")
    private List<Atividadecomentario> atividadecomentarioList;
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario; 
    @Transient
    private boolean concluido;

    public Atividadeusuario() {
    }

    public Atividadeusuario(Integer idatividadeusuario) {
        this.idatividadeusuario = idatividadeusuario;
    }

    public Integer getIdatividadeusuario() {
        return idatividadeusuario;
    }

    public void setIdatividadeusuario(Integer idatividadeusuario) {
        this.idatividadeusuario = idatividadeusuario;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public LocalDate getDataconclusao() {
        return dataconclusao;
    }

    public void setDataconclusao(LocalDate dataconclusao) {
        this.dataconclusao = dataconclusao;
    }

    public LocalTime getHoraconclusao() {
        return horaconclusao;
    }

    public void setHoraconclusao(LocalTime horaconclusao) {
        this.horaconclusao = horaconclusao;
    }

    

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }

    public List<Atividadecomentario> getAtividadecomentarioList() {
        return atividadecomentarioList;
    }

    public void setAtividadecomentarioList(List<Atividadecomentario> atividadecomentarioList) {
        this.atividadecomentarioList = atividadecomentarioList;
    } 
    
    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatividadeusuario != null ? idatividadeusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividadeusuario)) {
            return false;
        }
        Atividadeusuario other = (Atividadeusuario) object;
        if ((this.idatividadeusuario == null && other.idatividadeusuario != null) || (this.idatividadeusuario != null && !this.idatividadeusuario.equals(other.idatividadeusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Atividadeusuario[ idatividadeusuario=" + idatividadeusuario + " ]";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
