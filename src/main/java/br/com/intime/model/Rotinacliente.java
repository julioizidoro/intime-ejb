/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.model;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @Temporal(TemporalType.DATE)
    private Date datainicio;
    @Column(name = "hora") 
    private LocalTime hora;
    @Size(max = 15)
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "meta")
    private Integer meta;
    @Size(max = 7)
    @Column(name = "recorrencia")
    private String recorrencia;
    @Column(name = "acadarecorrencia")
    private Integer acadarecorrencia;
    @Column(name = "acadadiarecorrencia")
    private Integer acadadiarecorrencia;
    @Column(name = "domingo")
    private Boolean domingo;
    @Column(name = "segunda")
    private Boolean segunda;
    @Column(name = "terca")
    private Boolean terca;
    @Column(name = "quarta")
    private Boolean quarta;
    @Column(name = "quita")
    private Boolean quita;
    @Column(name = "sexta")
    private Boolean sexta;
    @Column(name = "sabado")
    private Boolean sabado;
    @Column(name = "numerosemana")
    private Integer numerosemana;
    @Size(max = 20)
    @Column(name = "diadasemana")
    private String diadasemana;
    @Size(max = 20)
    @Column(name = "mesano")
    private String mesano;
    @Column(name = "diaano")
    private Integer diaano;
    @Size(max = 10)
    @Column(name = "termina")
    private String termina;
    @Column(name = "datatermino")
    @Temporal(TemporalType.DATE)
    private Date datatermino;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "rotina_idrotina", referencedColumnName = "idrotina")
    @ManyToOne(optional = false)
    private Rotina rotina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rotina")
    private List<Rotinaatividade> rotinaatividadeList;

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

    public Date getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
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

    public String getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(String recorrencia) {
        this.recorrencia = recorrencia;
    }

    public Integer getAcadarecorrencia() {
        return acadarecorrencia;
    }

    public void setAcadarecorrencia(Integer acadarecorrencia) {
        this.acadarecorrencia = acadarecorrencia;
    }

    public Integer getAcadadiarecorrencia() {
        return acadadiarecorrencia;
    }

    public void setAcadadiarecorrencia(Integer acadadiarecorrencia) {
        this.acadadiarecorrencia = acadadiarecorrencia;
    }

    public Boolean getDomingo() {
        return domingo;
    }

    public void setDomingo(Boolean domingo) {
        this.domingo = domingo;
    }

    public Boolean getSegunda() {
        return segunda;
    }

    public void setSegunda(Boolean segunda) {
        this.segunda = segunda;
    }

    public Boolean getTerca() {
        return terca;
    }

    public void setTerca(Boolean terca) {
        this.terca = terca;
    }

    public Boolean getQuarta() {
        return quarta;
    }

    public void setQuarta(Boolean quarta) {
        this.quarta = quarta;
    }

    public Boolean getQuita() {
        return quita;
    }

    public void setQuita(Boolean quita) {
        this.quita = quita;
    }

    public Boolean getSexta() {
        return sexta;
    }

    public void setSexta(Boolean sexta) {
        this.sexta = sexta;
    }

    public Boolean getSabado() {
        return sabado;
    }

    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
    }

    public Integer getNumerosemana() {
        return numerosemana;
    }

    public void setNumerosemana(Integer numerosemana) {
        this.numerosemana = numerosemana;
    }

    public String getDiadasemana() {
        return diadasemana;
    }

    public void setDiadasemana(String diadasemana) {
        this.diadasemana = diadasemana;
    }

    public String getMesano() {
        return mesano;
    }

    public void setMesano(String mesano) {
        this.mesano = mesano;
    }

    public Integer getDiaano() {
        return diaano;
    }

    public void setDiaano(Integer diaano) {
        this.diaano = diaano;
    }

    public String getTermina() {
        return termina;
    }

    public void setTermina(String termina) {
        this.termina = termina;
    }

    public Date getDatatermino() {
        return datatermino;
    }

    public void setDatatermino(Date datatermino) {
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
