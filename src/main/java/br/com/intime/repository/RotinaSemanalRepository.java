/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;
      
import br.com.intime.model.Rotinasemanal;
import javax.ejb.Stateless;

/**
 *
 * @author Kamila
 */
@Stateless
public class RotinaSemanalRepository extends AbstractRepository<Rotinasemanal>{
    
    public RotinaSemanalRepository() {
        super(Rotinasemanal.class);
    }
    
}
