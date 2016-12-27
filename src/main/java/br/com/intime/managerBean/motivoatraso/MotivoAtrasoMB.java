/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.motivoatraso;

import br.com.intime.model.Motivoatraso;
import br.com.intime.repository.MotivoAtrasoRepository;
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
public class MotivoAtrasoMB implements Serializable{
    
    private Motivoatraso motivoatraso;
    @EJB
    private MotivoAtrasoRepository motivoAtrasoRepository;
    private List<Motivoatraso> listaMotivoAtraso;
    private String descricao;
    
    
    @PostConstruct
    public void init(){
        listarMotivoAtraso();
    }

    public Motivoatraso getMotivoatraso() {
        return motivoatraso;
    }

    public void setMotivoatraso(Motivoatraso motivoatraso) {
        this.motivoatraso = motivoatraso;
    }

    public List<Motivoatraso> getListaMotivoAtraso() {
        return listaMotivoAtraso;
    }

    public void setListaMotivoAtraso(List<Motivoatraso> listaMotivoAtraso) {
        this.listaMotivoAtraso = listaMotivoAtraso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    public void listarMotivoAtraso(){
        listaMotivoAtraso = motivoAtrasoRepository.list("Select m From Motivoatraso m");
        if (listaMotivoAtraso == null) {
            listaMotivoAtraso = new ArrayList<>();
        }
    }
    
    
    public void pesquisar(){
        listaMotivoAtraso = motivoAtrasoRepository.list("Select m from Motivoatraso m where m.descricao like '%"+descricao+"%'");
        if (listaMotivoAtraso == null) {
            listaMotivoAtraso = new ArrayList<>();
        }
    }
    
    public String editar(Motivoatraso motivoatraso) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        session.setAttribute("motivoatraso", motivoatraso);
        RequestContext.getCurrentInstance().openDialog("cadMotivoAtraso", options, null);
        return "";
    }
     
     
      public String novoDepartamento() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadMotivoAtraso", options, null);
        return "";
    }
      
      
       public void retornoDialogNovo(SelectEvent event){
        Motivoatraso motivoatraso = (Motivoatraso) event.getObject();
        if (motivoatraso.getIdmotivoatraso() != null) {
            Mensagem.lancarMensagemInfo("Salvo com sucesso", "");
            listarMotivoAtraso();
        }
    }
}
