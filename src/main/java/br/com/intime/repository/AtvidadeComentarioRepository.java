/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Atividadecomentario;
import javax.ejb.Stateless;

/**
 *
 * @author jizid
 */
@Stateless
public class AtvidadeComentarioRepository extends AbstractRepository<Atividadecomentario>{

    public AtvidadeComentarioRepository() {
        super(Atividadecomentario.class);
    }
    
    
    
}
