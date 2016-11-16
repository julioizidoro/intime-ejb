/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.converter;

import br.com.intime.model.Subdepartamento;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "SubDepartamentoConverter")
public class SubDepartamentoConverter implements Converter{
    
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

        List<Subdepartamento> listaDepartamento = (List<Subdepartamento>) arg1.getAttributes().get("listaSubDepartamento");
        if (listaDepartamento != null) {
            for (Subdepartamento subdepartamento : listaDepartamento) {
                if (subdepartamento.getNome().equalsIgnoreCase(arg2)) {
                    return subdepartamento;
                }
            }
        } else {
            Subdepartamento subdepartamento = new Subdepartamento();
            return subdepartamento;
        }
        Subdepartamento subdepartamento = new Subdepartamento();
        return subdepartamento;
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Subdepartamento subdepartamento = (Subdepartamento) arg2;
            return subdepartamento.getNome();
        }
    }
}
