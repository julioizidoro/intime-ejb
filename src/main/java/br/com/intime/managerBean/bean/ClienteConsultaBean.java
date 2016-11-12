/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.bean;

import br.com.intime.model.Cliente;

/**
 *
 * @author jizid
 */
public class ClienteConsultaBean {
    
    private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;
    private Cliente cliente4;
    private Cliente cliente5;

    public ClienteConsultaBean() {
        this.cliente1 = new Cliente();
        this.cliente2 = new Cliente();
        this.cliente3 = new Cliente();
        this.cliente4 = new Cliente();
        this.cliente5 = new Cliente();
    }

    public Cliente getCliente1() {
        return cliente1;
    }

    public void setCliente1(Cliente cliente1) {
        this.cliente1 = cliente1;
    }

    public Cliente getCliente2() {
        return cliente2;
    }

    public void setCliente2(Cliente cliente2) {
        this.cliente2 = cliente2;
    }

    public Cliente getCliente3() {
        return cliente3;
    }

    public void setCliente3(Cliente cliente3) {
        this.cliente3 = cliente3;
    }

    public Cliente getCliente4() {
        return cliente4;
    }

    public void setCliente4(Cliente cliente4) {
        this.cliente4 = cliente4;
    }

    public Cliente getCliente5() {
        return cliente5;
    }

    public void setCliente5(Cliente cliente5) {
        this.cliente5 = cliente5;
    }
    
    
    
}
