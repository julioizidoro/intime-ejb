/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.usuario;

import br.com.intime.model.Cliente;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Usuario;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class UsuarioMB implements Serializable{
    
    
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @EJB
    private UsuarioRepository usuarioRepository;
    private String nome;
    private Ftpdados ftpdados;
    @EJB
    private FtpDadosRepository ftpRepository;
    
    
    @PostConstruct
    public void init(){
        gerarListaUsuarios();
        ftpdados = ftpRepository.find(1);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Ftpdados getFtpdados() {
        return ftpdados;
    }

    public void setFtpdados(Ftpdados ftpdados) {
        this.ftpdados = ftpdados;
    }

    public FtpDadosRepository getFtpRepository() {
        return ftpRepository;
    }

    public void setFtpRepository(FtpDadosRepository ftpRepository) {
        this.ftpRepository = ftpRepository;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }
     
    public void gerarListaUsuarios(){
        listaUsuario = usuarioRepository.list("Select u From Usuario u");
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            listaUsuario = new ArrayList<>();
        }
    } 
    
    public void pesquisar(){
        listaUsuario = usuarioRepository.list("Select u From Usuario u Where u.nome like '% " + nome + "%'");
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            listaUsuario = new ArrayList<>();
        }
    } 
    
    public String novoUsuario() {
        if(usuarioLogadoMB.getUsuario().getCadastrusuario()==3
           || usuarioLogadoMB.getUsuario().getCadastrusuario()==4){
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 620);
            RequestContext.getCurrentInstance().openDialog("cadUsuario", options, null);
        }else Mensagem.lancarMensagemWarn("Acesso Negado!", "");
        return "";
    }

    public String editar(Usuario usuario) {
        if(usuarioLogadoMB.getUsuario().getCadastrusuario()==3
           || usuarioLogadoMB.getUsuario().getCadastrusuario()==4){
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 620);
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("usuario", usuario);
            RequestContext.getCurrentInstance().openDialog("cadUsuario", options, null);
        }else Mensagem.lancarMensagemWarn("Acesso Negado!", "");
        return "";
    }
}
