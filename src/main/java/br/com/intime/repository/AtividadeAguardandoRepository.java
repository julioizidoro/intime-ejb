/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Atividadeaguardando;
import javax.ejb.Stateless;

/**
 *
 * @author jizid
 */
@Stateless
public class AtividadeAguardandoRepository extends AbstractRepository<Atividadeaguardando>{

    public AtividadeAguardandoRepository() {
        super(Atividadeaguardando.class);
    }
    
    
}
