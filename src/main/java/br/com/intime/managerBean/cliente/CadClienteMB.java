package br.com.intime.managerBean.cliente;

import br.com.intime.model.Cliente;
import br.com.intime.model.Empresa;
import br.com.intime.repository.ClienteRepository;
import br.com.intime.repository.EmpresaRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class CadClienteMB implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private ClienteRepository clienteRepository;
    @EJB
    private EmpresaRepository empresaRepository;
    private Cliente cliente;
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    private String status;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        cliente = (Cliente) session.getAttribute("cliente");
        session.removeAttribute("cliente");
        if(cliente==null){
            cliente = new Cliente();
            empresa = new Empresa();
        }else{
            empresa = cliente.getEmpresaIdempresa();
            if(cliente.getStatus()){
                status = "Ativo";
            }else status = "Inativo";
        }
        gerarListaEmpresa();
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EmpresaRepository getEmpresaRepository() {
        return empresaRepository;
    }

    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<Empresa> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public void gerarListaEmpresa(){
        listaEmpresa = empresaRepository.list("Select e from Empresa e");
        if (listaEmpresa == null) {
            listaEmpresa = new ArrayList<Empresa>();
        }
    } 
    
    public String salvar() {
        cliente.setEmpresaIdempresa(empresa);
        if(status.equalsIgnoreCase("Ativo")){
            cliente.setStatus(true);
        }else cliente.setStatus(false);
        cliente = clienteRepository.update(cliente);
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }

    public String fechar() {
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }

}
