/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Atividadeerro;
import javax.ejb.Stateless;

/**
 *
 * @author Anderson
 */

@Stateless
public class AtividadeErroRepository extends AbstractRepository<Atividadeerro>{

    public AtividadeErroRepository() {
        super(Atividadeerro.class);
    }

    
    
}
