/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.model.Departamento;
import br.com.intime.model.Subdepartamento;
import br.com.intime.repository.DepartamentoRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class RotinaMB implements Serializable{
    
    @EJB
    private DepartamentoRepository departamentoRepository;
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
    
    
    
    public void listarSubDepartamento(){
        if (departamento == null) {
            listaSubDepartamento = new ArrayList<>();
        }
    }
    
    public void cadastrarRotina(){
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 700);
        RequestContext.getCurrentInstance().openDialog("cadRotina", options, null);
    }
    
    public void editarRotina(){
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 700);
        RequestContext.getCurrentInstance().openDialog("cadRotina", options, null);
    }
    
    
    public void excluirRotina(){
       
    }
    
    public String agendaRotina(){
       return "consAgendaRotina";
    }
}
