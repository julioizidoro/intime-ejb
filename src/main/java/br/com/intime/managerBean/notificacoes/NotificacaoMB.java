/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.notificacoes;

import br.com.intime.model.Cliente;
import br.com.intime.model.Notificacao;
import br.com.intime.repository.NotificacoesRepository;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class NotificacaoMB implements Serializable{
     
    private List<Notificacao> listaNotificacoes;
    @EJB
    private NotificacoesRepository notificacoesRepository;
    
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        listaNotificacoes = (List<Notificacao>) session.getAttribute("listaNotificacoes");
        session.removeAttribute("listaNotificacoes");
    }

    public List<Notificacao> getListaNotificacoes() {
        return listaNotificacoes;
    }

    public void setListaNotificacoes(List<Notificacao> listaNotificacoes) {
        this.listaNotificacoes = listaNotificacoes;
    }

    public NotificacoesRepository getNotificacoesRepository() {
        return notificacoesRepository;
    }

    public void setNotificacoesRepository(NotificacoesRepository notificacoesRepository) {
        this.notificacoesRepository = notificacoesRepository;
    }
    
    public void visualizarNotificacao(Notificacao notificacao){
        notificacao.setLido(true);
        notificacoesRepository.update(notificacao);
        listaNotificacoes.remove(notificacao);
    }
    
    public void fechar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
}
