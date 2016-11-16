/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Subdepartamento;
import javax.ejb.Stateless;


@Stateless
public class SubDepartamentoRepository extends AbstractRepository<Subdepartamento>{
    
    public SubDepartamentoRepository() {
        super(Subdepartamento.class);
    }
    
}
