package br.com.intime.managerBean.menu;

import java.io.Serializable; 
import javax.enterprise.context.SessionScoped; 
import javax.inject.Named; 

/**
 *
 * @author Kamila
 */
@Named
@SessionScoped
public class MenuMB implements Serializable {

    private static final long serialVersionUID = 1L;

    public String cliente() {
        return "consCliente";
    }

}
