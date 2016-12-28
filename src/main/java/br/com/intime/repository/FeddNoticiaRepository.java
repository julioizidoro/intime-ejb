/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Feednoticia;
import javax.ejb.Stateless;

/**
 *
 * @author jizid
 */
@Stateless
public class FeddNoticiaRepository extends AbstractRepository<Feednoticia>{

    public FeddNoticiaRepository() {
        super(Feednoticia.class);
    }
    
    
    
}
