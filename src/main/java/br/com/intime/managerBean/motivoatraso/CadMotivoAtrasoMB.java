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
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;



@Named
@ViewScoped
public class CadMotivoAtrasoMB implements Serializable{
    
    private Motivoatraso motivoatraso;
    @EJB
    private MotivoAtrasoRepository motivoAtrasoRepository;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        motivoatraso = (Motivoatraso) session.getAttribute("motivoatraso");
        session.removeAttribute("motivoatraso");
        if (motivoatraso == null) {
            motivoatraso = new Motivoatraso();
        }
    }

    public Motivoatraso getMotivoatraso() {
        return motivoatraso;
    }

    public void setMotivoatraso(Motivoatraso motivoatraso) {
        this.motivoatraso = motivoatraso;
    }
    
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(new Motivoatraso());
    }
    
    
    public void salvar(){
        if (motivoatraso.getDescricao().length() > 0) {
            motivoatraso = motivoAtrasoRepository.update(motivoatraso);
            RequestContext.getCurrentInstance().closeDialog(motivoatraso);
        }else{
            Mensagem.lancarMensagemInfo("Informe a descriçao", "");
        }
    }
}
