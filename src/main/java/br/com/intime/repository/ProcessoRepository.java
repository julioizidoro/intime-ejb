/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Nota;
import br.com.intime.model.Processo;
import javax.ejb.Stateless;

/**
 *
 * @author jizid
 */
@Stateless
public class ProcessoRepository extends AbstractRepository<Processo>{

    public ProcessoRepository() {
        super(Processo.class);
    }
}
