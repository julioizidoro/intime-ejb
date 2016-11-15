/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.empresa;

import br.com.intime.model.Empresa;
import br.com.intime.model.Usuario;
import br.com.intime.repository.EmpresaRepository;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EmpresaMB implements Serializable{
    
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    @EJB
    private EmpresaRepository empresaRepository;
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    @EJB
    private EmpresaRepository empresaRepository1;
}
