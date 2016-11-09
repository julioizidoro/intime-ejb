/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.repository;

import br.com.intime.model.Usuario;
import javax.ejb.Stateless;

/**
 *
 * @author Julio
 */

@Stateless
public class UsuarioRepository extends AbstractRepository<Usuario>{

    public UsuarioRepository() {
        super(Usuario.class);
    }
    
    
}
