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
public class UsuarioConsultaBean {
    
    private Usuario usuario1;
    private Usuario usuario2;
    private Usuario usuario3;
    private Usuario usuario4;
    private Usuario usuario5;

    public UsuarioConsultaBean() {
        this.usuario1 = new Usuario();
        this.usuario2 = new Usuario();
        this.usuario3 = new Usuario();
        this.usuario4 = new Usuario();
        this.usuario5 = new Usuario();
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public Usuario getUsuario3() {
        return usuario3;
    }

    public void setUsuario3(Usuario usuario3) {
        this.usuario3 = usuario3;
    }

    public Usuario getUsuario4() {
        return usuario4;
    }

    public void setUsuario4(Usuario usuario4) {
        this.usuario4 = usuario4;
    }

    public Usuario getUsuario5() {
        return usuario5;
    }

    public void setUsuario5(Usuario usuario5) {
        this.usuario5 = usuario5;
    }
    
    
    
    
    
}
