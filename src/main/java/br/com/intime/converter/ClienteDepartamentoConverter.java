/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.converter;

import br.com.intime.model.Clientedepartamento;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "ClienteDepartamentoConverter")
public class ClienteDepartamentoConverter implements Converter {

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

        List<Clientedepartamento> listaClienteDepartamento = (List<Clientedepartamento>) arg1.getAttributes().get("listaClienteDepartamento");
        if (listaClienteDepartamento != null && listaClienteDepartamento.size()>0) {
            for (Clientedepartamento clienteDepartamento : listaClienteDepartamento) {
                if (clienteDepartamento.getDepartamento().getNome().equalsIgnoreCase(arg2)) {
                    return clienteDepartamento;
                }
            }
        } else {
            Clientedepartamento clientedepartamento = new Clientedepartamento();
            return clientedepartamento;
        }
        Clientedepartamento clientedepartamento = new Clientedepartamento();
        return clientedepartamento;
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2.toString().equalsIgnoreCase("0")) {
            return "Departamento";
        } else {
            Clientedepartamento clientedepartamento = (Clientedepartamento) arg2;
            if(clientedepartamento.getIdclientedepartamento()!=null){
                return clientedepartamento.getDepartamento().getNome();
            }else return "";
        }
    }

}
