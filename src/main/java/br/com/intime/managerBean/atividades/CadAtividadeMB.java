/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Cliente;
import br.com.intime.model.Departamento;
import br.com.intime.repository.AtividadeUsuarioRepository; 
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime; 
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadAtividadeMB implements Serializable{
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    private Atividadeusuario atividadeusuario;
    private Cliente cliente;
    private Departamento departamento;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        if (atividadeusuario==null){
            atividadeusuario = new Atividadeusuario();
            atividadeusuario.setAtividade(new Atividade());
            atividadeusuario.setSituacao("execucao");
            atividadeusuario.setUsuario(usuarioLogadoMB.getUsuario());
            atividadeusuario.getAtividade().setDatalancamento(LocalDate.now());
            atividadeusuario.getAtividade().setNotificacaohorario(false);
            atividadeusuario.getAtividade().setPrioridade("normal");
            atividadeusuario.getAtividade().setRotina(false);
            atividadeusuario.getAtividade().setSubdepartamento(usuarioLogadoMB.getUsuario().getSubdepartamento());
            cliente = new Cliente();
            departamento = new Departamento();
        }else {
            cliente = atividadeusuario.getAtividade().getCliente();
            departamento = atividadeusuario.getAtividade().getSubdepartamento().getDepartamento();
        }
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    public String salvar(){
        if (atividadeusuario.getAtividade().getHoraexecucao()==null){
            LocalTime hora = LocalTime.of(23 , 59 ,00);
            atividadeusuario.getAtividade().setHoraexecucao(hora);
        }
        atividadeusuario.getAtividade().setCliente(cliente);
        atividadeUsuarioRepository.update(atividadeusuario);
        return "";
    }
    
}
