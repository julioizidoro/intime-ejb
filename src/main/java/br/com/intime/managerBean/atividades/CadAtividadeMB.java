/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.repository.AtividadeUsuarioRepository;
import java.io.Serializable;
import java.util.Date;
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
    
    @PostConstruct
    private void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        if (atividadeusuario==null){
            atividadeusuario = new Atividadeusuario();
            atividadeusuario.setAtividade(new Atividade());
            atividadeusuario.setSituacao("execucao");
            atividadeusuario.setUsuario(usuarioLogadoMB.getUsuario());
            atividadeusuario.getAtividade().setDatalancamento(new Date());
            atividadeusuario.getAtividade().setNotificacaohorario(false);
            atividadeusuario.getAtividade().setPrioridade("normal");
            atividadeusuario.getAtividade().setRotina(false);
            atividadeusuario.getAtividade().setSubdepartamento(usuarioLogadoMB.getUsuario().getSubdepartamento());
        }
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }
    
    
    
    
    
}
