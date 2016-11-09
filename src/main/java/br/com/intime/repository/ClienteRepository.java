/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Cliente;
import javax.ejb.Stateless;

/**
 *
 * @author Julio
 */

@Stateless
public class ClienteRepository extends AbstractRepository<Cliente>{

    public ClienteRepository() {
        super(Cliente.class);
    }
    
    
}
