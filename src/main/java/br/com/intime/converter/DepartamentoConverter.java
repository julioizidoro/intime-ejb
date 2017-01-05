/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.converter;

import br.com.intime.model.Departamento;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "DepartamentoConverter")
public class DepartamentoConverter implements Converter {

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

        List<Departamento> listaDepartamento = (List<Departamento>) arg1.getAttributes().get("listaDepartamento");
        if (listaDepartamento != null) {
            for (Departamento departamento : listaDepartamento) {
                if (departamento.getNome().equalsIgnoreCase(arg2)) {
                    return departamento;
                }
            }
        } else {
            Departamento departamento = new Departamento();
            return departamento;
        }
        Departamento departamento = new Departamento();
        return departamento;
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2.toString().equalsIgnoreCase("0")) {
            return "Selecione";
        } else {
            Departamento departamento = (Departamento) arg2;
            return departamento.getNome();
        }
    }

}
