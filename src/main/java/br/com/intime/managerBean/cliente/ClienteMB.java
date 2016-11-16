package br.com.intime.managerBean.cliente;

import br.com.intime.model.Cliente;
import br.com.intime.model.Ftpdados;
import br.com.intime.repository.ClienteRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.util.Ftp;
import br.com.intime.util.Mensagem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ClienteMB implements Serializable {

    private static final long serialVersionUID = 1L;
    @EJB
    private ClienteRepository clienteRepository;
    private List<Cliente> listaCliente;
    private String nomefantasia;
    private Ftpdados ftpdados;
    @EJB
    private FtpDadosRepository ftpDadosRepository;

    @PostConstruct
    public void init() {
        gerarListaCliente();
        ftpdados = ftpDadosRepository.find("select f from Ftpdados f"); 
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

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public Ftpdados getFtpdados() {
        return ftpdados;
    }

    public void setFtpdados(Ftpdados ftpdados) {
        this.ftpdados = ftpdados;
    }

    public FtpDadosRepository getFtpDadosRepository() {
        return ftpDadosRepository;
    }

    public void setFtpDadosRepository(FtpDadosRepository ftpDadosRepository) {
        this.ftpDadosRepository = ftpDadosRepository;
    }

    public String novoCliente() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 630);
        RequestContext.getCurrentInstance().openDialog("cadCliente", options, null);
        return "";
    }

    public String editar(Cliente cliente) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 630);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("cliente", cliente);
        RequestContext.getCurrentInstance().openDialog("cadCliente", options, null);
        return "";
    }

    public void gerarListaCliente() {
        listaCliente = clienteRepository.list("Select c from Cliente c");
        if (listaCliente == null) {
            listaCliente = new ArrayList<Cliente>();
        }
    }
    
    public void pesquisar(){
        listaCliente = clienteRepository.list("Select c from Cliente c where c.nomefantasia like '%"+nomefantasia+"%'");
        if (listaCliente == null) {
            listaCliente = new ArrayList<Cliente>();
        }
    }
    
    public boolean possuiImagem(Cliente cliente){
        if(cliente.getNomefoto()!=null && cliente.getNomefoto().length()>0){
            return true;
        }else return false;
    }
}
