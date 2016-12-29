/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;
 
import br.com.intime.model.Feednoticia;
import javax.ejb.Stateless;

@Stateless
public class FeedNoticiaRepository extends AbstractRepository<Feednoticia>{

    public FeedNoticiaRepository() {
        super(Feednoticia.class);
    }
}
