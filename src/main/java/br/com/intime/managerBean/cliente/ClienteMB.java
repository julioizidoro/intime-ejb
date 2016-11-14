package br.com.intime.managerBean.cliente;

import br.com.intime.model.Cliente;
import br.com.intime.repository.ClienteRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
    @EJB
    private ClienteRepository clienteRepository;
    private List<Cliente> listaCliente;

    @PostConstruct
    public void init() {
        gerarListaCliente();
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public String novoCliente() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 545);
        RequestContext.getCurrentInstance().openDialog("cadCliente", options, null);
        return "";
    }

    public void gerarListaCliente() {
        listaCliente = clienteRepository.list("Select c from Cliente c");
        if (listaCliente == null) {
            listaCliente = new ArrayList<Cliente>();
        }
    }
}
