/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.model.Departamento;
import br.com.intime.model.Subdepartamento;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class AgendaRotinaMB implements Serializable{
    
    private Departamento departamento;
    private Subdepartamento subDepartamento;
    private List<Departamento> listaDepartamento;
    private List<Subdepartamento> listaSubDepartamento;
    
    
    @PostConstruct
    public void init(){
        listaDepartamento = new ArrayList<>();
        listaSubDepartamento = new ArrayList<>();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Subdepartamento getSubDepartamento() {
        return subDepartamento;
    }

    public void setSubDepartamento(Subdepartamento subDepartamento) {
        this.subDepartamento = subDepartamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Subdepartamento> getListaSubDepartamento() {
        return listaSubDepartamento;
    }

    public void setListaSubDepartamento(List<Subdepartamento> listaSubDepartamento) {
        this.listaSubDepartamento = listaSubDepartamento;
    }
    
    
    
    public void consultarRotinaAtrasada(){
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 600);
        RequestContext.getCurrentInstance().openDialog("consRotinaAtrasada", options, null);
    }
}
