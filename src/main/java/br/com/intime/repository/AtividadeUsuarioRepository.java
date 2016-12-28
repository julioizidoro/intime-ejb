/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Atividadeusuario;
import javax.ejb.Stateless;

@Stateless
public class AtividadeUsuarioRepository extends AbstractRepository<Atividadeusuario>{

    public AtividadeUsuarioRepository() {
        super(Atividadeusuario.class);
    }
    
    
    
}
