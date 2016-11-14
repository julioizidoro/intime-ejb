package br.com.intime.managerBean.cliente;
 
import java.io.Serializable;   
import java.util.HashMap;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Named; 
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class ClienteMB implements Serializable {

    private static final long serialVersionUID = 1L;

    public String novoCliente() { 
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 545);
        RequestContext.getCurrentInstance().openDialog("cadCliente", options, null); 
        return "";
    }

}
