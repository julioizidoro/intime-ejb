/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.model.Rotina;
import br.com.intime.model.Rotinaatividade;
import br.com.intime.model.Rotinacliente;
import br.com.intime.repository.RotinaAtividadeRepository;
import br.com.intime.repository.RotinaClienteRepository;
import br.com.intime.repository.RotinaRepository;
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

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class RotinaMB implements Serializable{
    
    @EJB
    private RotinaRepository rotinaRepository;
    @EJB
    private RotinaClienteRepository rotinaClienteRepository;
    @EJB
    private RotinaAtividadeRepository rotinaAtividadeRepository;
    private List<Rotina> listaRotina;
    private String nomeRotina;
    
    @PostConstruct
    public void init(){
        gerarListaRotina();
    }

    public RotinaRepository getRotinaRepository() {
        return rotinaRepository;
    }

    public void setRotinaRepository(RotinaRepository rotinaRepository) {
        this.rotinaRepository = rotinaRepository;
    }

    public List<Rotina> getListaRotina() {
        return listaRotina;
    }

    public void setListaRotina(List<Rotina> listaRotina) {
        this.listaRotina = listaRotina;
    }

    public String getNomeRotina() {
        return nomeRotina;
    }

    public void setNomeRotina(String nomeRotina) {
        this.nomeRotina = nomeRotina;
    }

    public RotinaClienteRepository getRotinaClienteRepository() {
        return rotinaClienteRepository;
    }

    public void setRotinaClienteRepository(RotinaClienteRepository rotinaClienteRepository) {
        this.rotinaClienteRepository = rotinaClienteRepository;
    }

    public RotinaAtividadeRepository getRotinaAtividadeRepository() {
        return rotinaAtividadeRepository;
    }

    public void setRotinaAtividadeRepository(RotinaAtividadeRepository rotinaAtividadeRepository) {
        this.rotinaAtividadeRepository = rotinaAtividadeRepository;
    }
  
    public String cadastroRotina(){
        return "cadRotina";
    }
    
    public void gerarListaRotina(){
        listaRotina = rotinaRepository.list("select r from Rotina r order by r.nome");
        if(listaRotina==null){
            listaRotina = new ArrayList<Rotina>();
        }
    }
    
    public String editarRotina(Rotina rotina){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("rotina", rotina);
        return "cadRotina";
    }
    
    public void excluirRotina(Rotina rotina){
        List<Rotinacliente> listaCliente = rotinaClienteRepository.list("select r from Rotinacliente r"
                + " where r.rotina.idrotina="+rotina.getIdrotina());
        if(listaCliente!=null && listaCliente.size()>0){
            List<Rotinaatividade> listaAtividade;
            for (int i = 0; i < listaCliente.size(); i++) {
                listaAtividade  = rotinaAtividadeRepository.list("select r from Rotinaatividade r"
                + " where r.rotinacliente.idrotinacliente="+listaCliente.get(i).getIdrotinacliente());
                if(listaAtividade!=null && listaAtividade.size()>0){
                    for (int j = 0; j < listaAtividade.size(); j++) {
                        rotinaClienteRepository.remove(listaAtividade.get(j).getIdrotinaatividade());
                    }
                }
                rotinaClienteRepository.remove(listaCliente.get(i).getIdrotinacliente());
            }
        } 
        listaRotina.remove(rotina);
        rotinaRepository.remove(rotina.getIdrotina());
        Mensagem.lancarMensagemInfo("Excluído com sucesso!", "");
    }
    
    public void pesquisar(){
        if(nomeRotina!=null && nomeRotina.length()>0){
            listaRotina = rotinaRepository.list("select r from Rotina r where r.nome like '%"+nomeRotina
                    +"%' order by r.nome");
            if(listaRotina==null){   
                listaRotina = new ArrayList<Rotina>();
            } 
        }
    }
}
