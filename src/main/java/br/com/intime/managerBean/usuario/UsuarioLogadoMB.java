/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.usuario;

import br.com.intime.model.Usuario;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Criptografia;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Wolverine
 */
@Named
@SessionScoped
public class UsuarioLogadoMB  implements Serializable{
    
    @EJB
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    private String senha;
    private String login; 
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String validarUsuario() {
        if ((login== null || login.length()==0) || (senha == null || senha.length()==0)) {
            Mensagem.lancarMensagemErro("Erro!", "Login Invalido.");
        } else {
            String senhaCrip = "";
            try {
                senhaCrip = Criptografia.encript(this.senha);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
                FacesMessage mensagem = new FacesMessage("Erro: " + ex);
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            usuario = usuarioRepository.find("Select u from Usuario u where u.login='" + login + "' and u.senha='" + senhaCrip + "'");
            if (usuario == null) {
                Mensagem.lancarMensagemInfo("", "Acesso negado!!");
            } else {
                return "inicial";
            }
        }
        usuario  = new Usuario();
        return "";
    }
    
    public String paginainicial(){
        return "inicial";
    }
    
    public String deslogar(){
         Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
         sessionMap.clear();  
         return "index";
     }
}
