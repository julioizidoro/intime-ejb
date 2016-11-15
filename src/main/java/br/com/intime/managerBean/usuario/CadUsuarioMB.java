/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.usuario;

import br.com.intime.model.Acesso;
import br.com.intime.model.Cliente;
import br.com.intime.model.Empresa;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AcessoRepository;
import br.com.intime.repository.EmpresaRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Criptografia;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class CadUsuarioMB implements Serializable{
    
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    @EJB
    private UsuarioRepository usuarioRepository;
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    @EJB
    private EmpresaRepository empresaRepository;
    private Acesso acesso;
    @EJB
    private AcessoRepository acessoRepository;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        usuario = (Usuario) session.getAttribute("usuario");
        session.removeAttribute("usuario");
        if (usuario == null) {
            usuario = new Usuario();
            empresa = new Empresa();
        } else {
            empresa = usuario.getEmpresaIdempresa();
        }
        gerarListaEmpresa();
        acesso = acessoRepository.find(1);
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public EmpresaRepository getEmpresaRepository() {
        return empresaRepository;
    }

    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }
    
    public void gerarListaEmpresa() {
        listaEmpresa = empresaRepository.list("Select e from Empresa e");
        if (listaEmpresa == null) {
            listaEmpresa = new ArrayList<Empresa>();
        }
    }
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public String validarDados(){
        String msg = "";
        if (empresa == null) {
            msg = msg + "Empresa Não selecionada \r\n";
        }
        if (usuario.getNome().length() == 0) {
            msg = msg + "Nome do Usuário não informado \r\n";
        }
        return msg;
    }
    
    public void salvar(){
        List<Usuario> listaUsuario = usuarioRepository.list("Select u from Usuario u where u.login='" + usuario.getLogin() + "'");
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            try {
                usuario.setSenha(Criptografia.encript(usuario.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CadUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setStatus(true);
            String mensagem = validarDados();
            if (mensagem.length() < 1) {
                usuario.setEmpresaIdempresa(empresa);
                usuario.setAcessoIdacesso(acesso);
                usuario = usuarioRepository.update(usuario);
                RequestContext.getCurrentInstance().closeDialog(usuario);
            }
        } else if (usuario.getIdusuario() != null) {
            usuario = usuarioRepository.update(usuario);
            RequestContext.getCurrentInstance().closeDialog(usuario);
        } else {
            String msg = "este login ja tem uma conta existente!!";
        }
    }
}
