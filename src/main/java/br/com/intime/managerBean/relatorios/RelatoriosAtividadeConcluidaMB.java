/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.relatorios;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.repository.AtividadeUsuarioRepository;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Anderson
 */

@Named
@ViewScoped
public class RelatoriosAtividadeConcluidaMB implements Serializable{
    
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Atividadeusuario> listaAtividade;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    
    
    @PostConstruct
    public void init(){
        gerarListaAtivadades();
    }

    public List<Atividadeusuario> getListaAtividade() {
        return listaAtividade;
    }

    public void setListaAtividade(List<Atividadeusuario> listaAtividade) {
        this.listaAtividade = listaAtividade;
    }
    
    
    
     public void gerarListaAtivadades() {
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao='Concluida'";
        listaAtividade = new ArrayList<>();
        listaAtividade = atividadeUsuarioRepository.list(sql);
    }
}
