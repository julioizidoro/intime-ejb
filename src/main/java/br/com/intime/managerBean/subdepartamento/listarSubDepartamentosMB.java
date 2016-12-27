/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.subdepartamento;

import br.com.intime.model.Departamento;
import br.com.intime.model.Subdepartamento;
import br.com.intime.repository.SubDepartamentoRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
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
public class listarSubDepartamentosMB implements Serializable{
    
    private Subdepartamento subdepartamento;
    @EJB
    private SubDepartamentoRepository subDepartamentoRepository;
    private List<Subdepartamento> listaSubDepartamento;
    private Departamento departamento;
    private String nomeSubDepartamento;
    
    
    
    @PostConstruct 
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        departamento = (Departamento) session.getAttribute("departamento");
        if (departamento != null) {
            if (departamento.getSubdepartamentoList() != null) {
                listaSubDepartamento = departamento.getSubdepartamentoList();
            }
        }
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public List<Subdepartamento> getListaSubDepartamento() {
        return listaSubDepartamento;
    }

    public void setListaSubDepartamento(List<Subdepartamento> listaSubDepartamento) {
        this.listaSubDepartamento = listaSubDepartamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNomeSubDepartamento() {
        return nomeSubDepartamento;
    }

    public void setNomeSubDepartamento(String nomeSubDepartamento) {
        this.nomeSubDepartamento = nomeSubDepartamento;
    }
    
    
    
    
    public void adicionarSubDepartamento(){
        if (nomeSubDepartamento.length() > 0) {
            Subdepartamento subdepartamento = new Subdepartamento();
            subdepartamento.setDepartamento(departamento);
            subdepartamento.setNome(nomeSubDepartamento);
            subdepartamento.setStatus(true);
            subDepartamentoRepository.update(subdepartamento);
            listaSubDepartamento.add(subdepartamento);
            nomeSubDepartamento = "";
        }else{
            Mensagem.lancarMensagemInfo("Informe um nome para este sub-departamento", "");
        }
    }
    
    
    public void excluirSubDepartamento(Subdepartamento subdepartamento){
        if (subdepartamento != null) {
            departamento.getSubdepartamentoList().remove(subdepartamento.getIdsubdepartamento());
            int id = subdepartamento.getIdsubdepartamento();
            subDepartamentoRepository.remove(id);
            listaSubDepartamento.remove(subdepartamento);
        }
    }
    
    
    public void salvarSubDepartamento(){
         RequestContext.getCurrentInstance().closeDialog("");
    }
    
}
