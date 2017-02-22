/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Nota;
import br.com.intime.model.Processoatividade;
import javax.ejb.Stateless;

/**
 *
 * @author jizid
 */
@Stateless
public class ProcessoAtividadeRepository extends AbstractRepository<Processoatividade>{

    public ProcessoAtividadeRepository() {
        super(Processoatividade.class);
    }
}
