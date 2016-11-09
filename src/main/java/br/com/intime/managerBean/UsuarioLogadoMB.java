/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean;

import br.com.intime.model.Usuario;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Criptografia;
import br.com.intime.util.Mensagem;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Wolverine
 */
public class UsuarioLogadoMB {
    
    @EJB
    private UsuarioRepository usuarioRepository;
    private Usuario usuario;
    
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
    
    public String validarUsuario() {
        if ((usuario.getLogin() != null) && (usuario.getSenha() == null)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Login Invalido."));
        } else {
            String senha = "";
            try {
                senha = Criptografia.encript(usuario.getSenha());
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioLogadoMB.class.getName()).log(Level.SEVERE, null, ex);
                FacesMessage mensagem = new FacesMessage("Erro: " + ex);
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
            usuario = usuarioRepository.find("Select u from Usuario u where u.login='" + usuario.getLogin() + "' and u.senha='" + senha + "'");
            if (usuario == null) {
                Mensagem.lancarMensagemInfo("", "Acesso negado!!");
            } else {
                return "incial";
            }
        }
        usuario  = new Usuario();
        return "";
    }
    
    public String deslogar(){
         Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
         sessionMap.clear();  
         return "index";
     }
}
