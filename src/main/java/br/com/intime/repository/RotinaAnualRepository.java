/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;
      
import br.com.intime.model.Rotinaanual; 
import javax.ejb.Stateless;

/**
 *
 * @author Kamila
 */
@Stateless
public class RotinaAnualRepository extends AbstractRepository<Rotinaanual>{
    
    public RotinaAnualRepository() {
        super(Rotinaanual.class);
    }
    
}
