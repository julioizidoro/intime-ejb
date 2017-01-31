/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.bean;

import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jizid
 */
public class NotificacaoAtividadeConcluidaBean {
    
    private Atividadeusuario atividadeusuario;
    private List<Usuario> listaSelecionado;
    private List<Usuario> listaUsuario;
   
    public NotificacaoAtividadeConcluidaBean(Atividadeusuario atividadeusuario, List<Usuario> listaUsuario) {
        this.atividadeusuario = atividadeusuario;
        listaSelecionado = new ArrayList<Usuario>();
        this.listaUsuario = listaUsuario;
    }

    public List<Usuario> getListaSelecionado() {
        return listaSelecionado;
    }

    public void setListaSelecionado(List<Usuario> listaSelecionado) {
        this.listaSelecionado = listaSelecionado;
    }
    
    public void selecionarUsuario(Usuario usuario){
        int idUsuairo = atividadeusuario.getAtividade().getUsuario().getIdusuario();
        if (usuario.getNotificacaoconclusao().equalsIgnoreCase("Delegado")){
            if (idUsuairo==usuario.getIdusuario()){
                listaSelecionado.add(usuario);
            }
        }else if (usuario.getNotificacaoconclusao().equalsIgnoreCase("Nível Hierárquico")){
            if (usuario.getNivel().equalsIgnoreCase("1")){
               listaSelecionado.add(usuario);
            }else if (usuario.getNivel().equalsIgnoreCase("2")){
                int idDepartamento = atividadeusuario.getUsuario().getSubdepartamento().getDepartamento().getIddepartamento();
                if (idDepartamento==usuario.getSubdepartamento().getDepartamento().getIddepartamento()){
                    listaSelecionado.add(usuario);
                }
            }else if (usuario.getNivel().equalsIgnoreCase("3")){
                int idSubDepartamento = atividadeusuario.getUsuario().getSubdepartamento().getIdsubdepartamento();
                if (idSubDepartamento==usuario.getSubdepartamento().getIdsubdepartamento()){
                    listaSelecionado.add(usuario);
                }
            }
        }
    }
}
