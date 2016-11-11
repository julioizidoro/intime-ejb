/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.bean;

import br.com.intime.model.Usuario;

/**
 *
 * @author jizid
 */
public class UsuarioBean {
    
    private Object lista;

    public UsuarioBean() {
        this.lista = new Object[]{new Usuario(), new Usuario(), new Usuario(), new Usuario(), new Usuario()};
    }

    public Object getLista() {
        return lista;
    }

    public void setLista(Object lista) {
        this.lista = lista;
    }
    
    
}
