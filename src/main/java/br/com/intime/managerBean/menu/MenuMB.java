package br.com.intime.managerBean.menu;

import java.io.Serializable; 
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped; 
import javax.inject.Named; 
import org.primefaces.context.RequestContext;

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
    
    public String usuario() {
        return "consUsuario";
    }
    
    public String atividades() {
        return "consAtividades";
    }
    
    public String novaEmpresa(){
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 580);
        RequestContext.getCurrentInstance().openDialog("cadEmpresa", options, null);
        return "";
    }
    
    
    public String departamentos() {
        return "consDepartamento";
    }
    
    public String motivosatraso() {
        return "consMotivoAtraso";
    }

    
    public String adicionarAtividades() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadAtividades", options, null);
        return "";
    }
}
