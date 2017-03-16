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
 * @author Kamila
 */
@Entity
@Table(name = "rotinacliente") 
public class Rotinacliente implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrotinacliente")
    private Integer idrotinacliente;
    @Column(name = "datainicio") 
    private LocalDate datainicio;
    @Column(name = "hora") 
    private LocalTime hora;
    @Size(max = 7)
    @Column(name = "padraorecorrencia")
    private String padraorecorrencia;
    @Size(max = 15)
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "meta")
    private Integer meta; 
    @Column(name = "termina")
    private String termina; 
    @Column(name = "recorrencia")
    private Integer recorrencia; 
    @Column(name = "datatermino") 
    private LocalDate datatermino;  
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "rotina_idrotina", referencedColumnName = "idrotina")
    @ManyToOne(optional = false)   
    private Rotina rotina; 
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;  
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotina")
    private List<Rotinaatividade> rotinaatividadeList;    
    @Transient
    private Date datainicial;
    @Transient
    private Date datafinal;
    @Transient
    private String horario;  

    public Rotinacliente() {
    }

    public Rotinacliente(Integer idrotinacliente) {
        this.idrotinacliente = idrotinacliente;
    }

    public Integer getIdrotinacliente() {
        return idrotinacliente;
    }

    public void setIdrotinacliente(Integer idrotinacliente) {
        this.idrotinacliente = idrotinacliente;
    }

    public LocalDate getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(LocalDate datainicio) {
        this.datainicio = datainicio;
    }


    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public Integer getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(Integer recorrencia) {
        this.recorrencia = recorrencia;
    }  

    public String getTermina() {
        return termina;
    }

    public void setTermina(String termina) {
        this.termina = termina;
    }

    public LocalDate getDatatermino() {
        return datatermino;
    }

    public void setDatatermino(LocalDate datatermino) {
        this.datatermino = datatermino;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }
 
    public List<Rotinaatividade> getRotinaatividadeList() {
        return rotinaatividadeList;
    }

    public void setRotinaatividadeList(List<Rotinaatividade> rotinaatividadeList) {
        this.rotinaatividadeList = rotinaatividadeList;
    }
    
    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getPadraorecorrencia() {
        return padraorecorrencia;
    }

    public void setPadraorecorrencia(String padraorecorrencia) {
        this.padraorecorrencia = padraorecorrencia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Date getDatainicial() {
        return datainicial;
    }

    public void setDatainicial(Date datainicial) {
        this.datainicial = datainicial;
    }

    public Date getDatafinal() {
        return datafinal;
    }

    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
 
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrotinacliente != null ? idrotinacliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rotinacliente)) {
            return false;
        }
        Rotinacliente other = (Rotinacliente) object;
        if ((this.idrotinacliente == null && other.idrotinacliente != null) || (this.idrotinacliente != null && !this.idrotinacliente.equals(other.idrotinacliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intime.model.Rotinacliente[ idrotinacliente=" + idrotinacliente + " ]";
    }

}
