/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Clientedepartamento;
import javax.ejb.Stateless;


@Stateless
public class ClienteDepartamentoRepository extends AbstractRepository<Clientedepartamento>{
    
    public ClienteDepartamentoRepository() {
        super(Clientedepartamento.class);
    }
    
}
