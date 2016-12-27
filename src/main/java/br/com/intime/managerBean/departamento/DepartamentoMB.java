/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.departamento;

import br.com.intime.model.Departamento;
import br.com.intime.repository.DepartamentoRepository;
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
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;


@Named
@ViewScoped
public class DepartamentoMB implements Serializable{
    
    private Departamento departamento;
    @EJB
    private DepartamentoRepository departamentoRepository;
    private List<Departamento> listaDepartamento;
    private String nome;
    
    
    @PostConstruct
    public void init(){
        listarDepartamentos();
    }
    
    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    public void listarDepartamentos(){
        listaDepartamento = departamentoRepository.list("Select d From Departamento d");
        if (listaDepartamento == null || listaDepartamento.isEmpty()) {
            listaDepartamento = new ArrayList<>();
        }
    }
    
    
    public String listarSubDepartamentos(Departamento departamento) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 580);
        session.setAttribute("departamento", departamento);
        RequestContext.getCurrentInstance().openDialog("listarSubDepartamentos", options, null);
        return "";
    }
    
    
    public void retornoDialogSubDepartamento(SelectEvent event){
        String msg = (String) event.getObject();
        if (msg.length() > 0) {
            Mensagem.lancarMensagemInfo("", msg);
        }
    }
    
    
     public String editar(Departamento departamento) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 580);
        session.setAttribute("departamento", departamento);
        RequestContext.getCurrentInstance().openDialog("cadDepartamento", options, null);
        return "";
    }
     
     
      public String novoDepartamento() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 580);
        RequestContext.getCurrentInstance().openDialog("cadDepartamento", options, null);
        return "";
    }
      
      
       public void retornoDialogNovo(SelectEvent event){
        Departamento departamento = (Departamento) event.getObject();
        if (departamento.getIddepartamento() != null) {
            Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
            listarDepartamentos();
        }
    }
       
       public void pesquisar(){
        listaDepartamento = departamentoRepository.list("Select d from Departamento d where d.nome like '%"+nome+"%'");
            if (listaDepartamento == null) {
                listaDepartamento = new ArrayList<>();
            }
        }
    
}
