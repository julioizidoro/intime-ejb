/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.departamento;

import br.com.intime.model.Departamento;
import br.com.intime.model.Usuario;
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadDepartamentoMB implements Serializable{
    
    private Departamento departamento;
    @EJB
    private DepartamentoRepository departamentoRepository;
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    @EJB
    private UsuarioRepository usuarioRepository;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        departamento = (Departamento) session.getAttribute("departamento");
        session.removeAttribute("departamento");
        if (departamento == null) {
            departamento = new Departamento();
        }else{
            usuario = departamento.getUsuario();
        }
        listarUsuario();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
    
    
    
    
    public void salvar(){
        if (validarDados()){
            departamento.setUsuario(usuario);
            departamento = departamentoRepository.update(departamento);
            RequestContext.getCurrentInstance().closeDialog(departamento);
        }
    }
    
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(new Departamento());
    }
    
    
    public void listarUsuario(){
        listaUsuario = usuarioRepository.list(" Select u From Usuario u Where u.status=true");
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<>();
        }
    }
    
    public boolean validarDados(){
        if (usuario == null) {
            Mensagem.lancarMensagemInfo("Informe um responsável para este departamento", "");
            return false;
        }
        if (departamento.getNome().equalsIgnoreCase("")) {
            Mensagem.lancarMensagemInfo("Informe um nome para este departamento", "");
            return false;
        }
        return true;
    }
}
