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
import javax.validation.constraints.Size;

/**
 *
 * @author Wolverine
 */
@Entity
@Table(name = "subdepartamento")
public class Subdepartamento implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subdepartamento")
    private List<Rotina> rotinaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subdepartamento")
    private List<Atividade> atividadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subdepartamento")
    private List<Processo> processoList;


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubdepartamento")
    private Integer idsubdepartamento;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne(optional = false)
    private Departamento departamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subdepartamento")
    private List<Usuario> usuarioList;

    public Subdepartamento() {
        setStatus(true);
    }

    public Subdepartamento(Integer idsubdepartamento) {
        this.idsubdepartamento = idsubdepartamento;
    }

    public Integer getIdsubdepartamento() {
        return idsubdepartamento;
    }

    public void setIdsubdepartamento(Integer idsubdepartamento) {
        this.idsubdepartamento = idsubdepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubdepartamento != null ? idsubdepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subdepartamento)) {
            return false;
        }
        Subdepartamento other = (Subdepartamento) object;
        if ((this.idsubdepartamento == null && other.idsubdepartamento != null) || (this.idsubdepartamento != null && !this.idsubdepartamento.equals(other.idsubdepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Subdepartamento[ idsubdepartamento=" + idsubdepartamento + " ]";
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<Atividade> getAtividadeList() {
        return atividadeList;
    }

    public void setAtividadeList(List<Atividade> atividadeList) {
        this.atividadeList = atividadeList;
    }

    public List<Processo> getProcessoList() {
        return processoList;
    }

    public void setProcessoList(List<Processo> processoList) {
        this.processoList = processoList;
    }

    public List<Rotina> getRotinaList() {
        return rotinaList;
    }

    public void setRotinaList(List<Rotina> rotinaList) {
        this.rotinaList = rotinaList;
    }

}
