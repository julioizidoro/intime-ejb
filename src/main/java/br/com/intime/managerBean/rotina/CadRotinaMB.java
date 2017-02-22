/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Departamento;
import br.com.intime.model.Subdepartamento;
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class CadRotinaMB implements Serializable{
    
    @EJB
    private DepartamentoRepository departamentoRepository;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Departamento> listaDepartamento;
    private Departamento departamento;
    private List<Subdepartamento> listaSubDepartamento;
    private Subdepartamento subdepartamento;
    
    
    @PostConstruct
    public void init(){
        listaDepartamento = new ArrayList<>();
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Subdepartamento> getListaSubDepartamento() {
        return listaSubDepartamento;
    }

    public void setListaSubDepartamento(List<Subdepartamento> listaSubDepartamento) {
        this.listaSubDepartamento = listaSubDepartamento;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public DepartamentoRepository getDepartamentoRepository() {
        return departamentoRepository;
    }

    public void setDepartamentoRepository(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }
     
    public void listarSubDepartamento(){
        if (departamento == null) {
            listaSubDepartamento = new ArrayList<>();
        }
    }
    
    public void cadastrarFuncoesRotina(){
        if(usuarioLogadoMB.getUsuario().getCadastrorotina()==3
            || usuarioLogadoMB.getUsuario().getCadastrorotina()==4){
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 700);
            RequestContext.getCurrentInstance().openDialog("cadFuncoesRotina", options, null);
        }else Mensagem.lancarMensagemWarn("Acesso Negado!", "");
    }
    
    public void editarRotina(){
        if(usuarioLogadoMB.getUsuario().getCadastrorotina()==3
            || usuarioLogadoMB.getUsuario().getCadastrorotina()==4){
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 700);
            RequestContext.getCurrentInstance().openDialog("cadRotina", options, null);
        }else Mensagem.lancarMensagemWarn("Acesso Negado!", "");
    }
    
    
    public void excluirRotina(){
       if(usuarioLogadoMB.getUsuario().getCadastrorotina()==4){ 
           
       }else Mensagem.lancarMensagemWarn("Acesso Negado!", "");
    }
    
    public String agendaRotina(){
       return "consAgendaRotina";
    }
}
