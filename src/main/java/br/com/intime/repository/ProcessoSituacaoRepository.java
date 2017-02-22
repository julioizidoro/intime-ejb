/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Nota;
import br.com.intime.model.Processosituacao;
import javax.ejb.Stateless;

/**
 *
 * @author Wolverine
 */
@Stateless
public class ProcessoSituacaoRepository extends AbstractRepository<Nota>{
    
    public ProcessoSituacaoRepository() {
        super(Processosituacao.class);
    }
    
}
