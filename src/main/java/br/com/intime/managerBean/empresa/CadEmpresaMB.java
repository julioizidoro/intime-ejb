/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.empresa;

import br.com.intime.model.Empresa;
import br.com.intime.model.Usuario;
import br.com.intime.repository.EmpresaRepository;
import br.com.intime.repository.UsuarioRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadEmpresaMB implements Serializable{
    
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    @EJB
    private EmpresaRepository empresaRepository;
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    @EJB
    private UsuarioRepository usuarioRepository;
    
    
    @PostConstruct
    public void init(){
        gerarListaUsuarios();
        if (empresa == null) {
            empresa = new Empresa();
        }
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
    
    
    public void gerarListaUsuarios(){
        listaUsuario = usuarioRepository.list("Select u From Usuario u");
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            listaUsuario = new ArrayList<>();
        }
    }
    
    public void salvar(){
        String mensagem = validarDados();
        if (mensagem.length() < 1) {
            empresa.setUsuariomaster(usuario.getIdusuario());
            empresaRepository.update(empresa);
            RequestContext.getCurrentInstance().closeDialog(empresa);
        }
    }
    
    public String validarDados(){
        String msg = "";
        if (usuario == null) {
            msg = msg + "Usuário master não informado \r\n";
        }
        return msg;
    }
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
}
