/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name = "atividadecomentario")
public class Atividadecomentario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatividadecomentario")
    private Integer idatividadecomentario;
    @Column(name = "data")
    private LocalDate data;
    @Size(max = 16777215)
    @Column(name = "comentario")
    private String comentario;
    @JoinColumn(name = "atividadeusuario_idatividadeusuario", referencedColumnName = "idatividadeusuario")
    @ManyToOne(optional = false)
    private Atividadeusuario atividadeusuario;

    public Atividadecomentario() {
    }

    public Atividadecomentario(Integer idatividadecomentario) {
        this.idatividadecomentario = idatividadecomentario;
    }

    public Integer getIdatividadecomentario() {
        return idatividadecomentario;
    }

    public void setIdatividadecomentario(Integer idatividadecomentario) {
        this.idatividadecomentario = idatividadecomentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatividadecomentario != null ? idatividadecomentario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atividadecomentario)) {
            return false;
        }
        Atividadecomentario other = (Atividadecomentario) object;
        if ((this.idatividadecomentario == null && other.idatividadecomentario != null) || (this.idatividadecomentario != null && !this.idatividadecomentario.equals(other.idatividadecomentario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Atividadecomentario[ idatividadecomentario=" + idatividadecomentario + " ]";
    }
    
}
