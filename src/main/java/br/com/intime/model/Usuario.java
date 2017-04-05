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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author jizid
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable { 

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idusuario")
    private Integer idusuario;
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;
    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    private Date datanascimento;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 20)
    @Column(name = "fone")
    private String fone;
    @Size(max = 20)
    @Column(name = "nivel")
    private String nivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "acesso_idacesso")
    private int acessoIdacesso;
    @Column(name = "status")
    private Boolean status;
    @Size(max = 30)
    @Column(name = "login")
    private String login;
    @Size(max = 100)
    @Column(name = "senha")
    private String senha;
    @Size(max = 20)
    @Column(name = "nomefoto")
    private String nomefoto;
    @Column(name = "alterarprazorotina")
    private Boolean alterarprazorotina;
    @Size(max = 20)
    @Column(name = "notificacaoconclusao")
    private String notificacaoconclusao;
    @Size(max = 20)
    @Column(name = "notificacaoatraso")
    private String notificacaoatraso;
    @Column(name = "cadastroclinte")
    private Integer cadastroclinte;
    @Column(name = "cadastrorotina")
    private Integer cadastrorotina;
    @Column(name = "cadastrusuario")
    private Integer cadastrusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Departamento> departamentoList;
    @JoinColumn(name = "empresa_idempresa", referencedColumnName = "idempresa")
    @ManyToOne(optional = false)
    private Empresa empresa;
    @JoinColumn(name = "subdepartamento_idsubdepartamento", referencedColumnName = "idsubdepartamento")
    @ManyToOne(optional = false)
    private Subdepartamento subdepartamento; 

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, int acessoIdacesso) {
        this.idusuario = idusuario;
        this.acessoIdacesso = acessoIdacesso;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getAcessoIdacesso() {
        return acessoIdacesso;
    }

    public void setAcessoIdacesso(int acessoIdacesso) {
        this.acessoIdacesso = acessoIdacesso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomefoto() {
        return nomefoto;
    }

    public void setNomefoto(String nomefoto) {
        this.nomefoto = nomefoto;
    }

    public Boolean getAlterarprazorotina() {
        return alterarprazorotina;
    }

    public void setAlterarprazorotina(Boolean alterarprazorotina) {
        this.alterarprazorotina = alterarprazorotina;
    }

    public String getNotificacaoconclusao() {
        return notificacaoconclusao;
    }

    public void setNotificacaoconclusao(String notificacaoconclusao) {
        this.notificacaoconclusao = notificacaoconclusao;
    }

    public String getNotificacaoatraso() {
        return notificacaoatraso;
    }

    public void setNotificacaoatraso(String notificacaoatraso) {
        this.notificacaoatraso = notificacaoatraso;
    }

    public Integer getCadastroclinte() {
        return cadastroclinte;
    }

    public void setCadastroclinte(Integer cadastroclinte) {
        this.cadastroclinte = cadastroclinte;
    }

    public Integer getCadastrorotina() {
        return cadastrorotina;
    }

    public void setCadastrorotina(Integer cadastrorotina) {
        this.cadastrorotina = cadastrorotina;
    }

    public Integer getCadastrusuario() {
        return cadastrusuario;
    }

    public void setCadastrusuario(Integer cadastrusuario) {
        this.cadastrusuario = cadastrusuario;
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Usuario[ idusuario=" + idusuario + " ]";
    } 
}
