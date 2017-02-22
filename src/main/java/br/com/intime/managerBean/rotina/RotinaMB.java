/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Anderson
 */
@Named
@ViewScoped
public class RotinaMB implements Serializable{
    
    
    @PostConstruct
    public void init(){
        
    }
    
    
    
    public String cadastroRotina(){
        return "cadRotina";
    }
}
