package br.com.intime.managerBean.cliente;

import br.com.intime.model.Cliente;
import br.com.intime.model.Empresa;
import br.com.intime.model.Ftpdados;
import br.com.intime.repository.ClienteRepository;
import br.com.intime.repository.EmpresaRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.util.Ftp;
import br.com.intime.util.Mensagem;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

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
    @EJB
    private FtpDadosRepository ftpDadosRepository;
    private Cliente cliente;
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    private String status;
    private UploadedFile file;
    private FileUploadEvent ex;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        cliente = (Cliente) session.getAttribute("cliente");
        session.removeAttribute("cliente");
        if (cliente == null) {
            cliente = new Cliente();
            empresa = new Empresa();
        } else {
            empresa = cliente.getEmpresaIdempresa();
            if (cliente.getStatus()) {
                status = "Ativo";
            } else {
                status = "Inativo";
            }
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

    public void gerarListaEmpresa() {
        listaEmpresa = empresaRepository.list("Select e from Empresa e");
        if (listaEmpresa == null) {
            listaEmpresa = new ArrayList<Empresa>();
        }
    }

    public FileUploadEvent getEx() {
        return ex;
    }

    public void setEx(FileUploadEvent ex) {
        this.ex = ex;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String salvar() {
        if (validarDados()) {
            cliente.setEmpresaIdempresa(empresa);
            if (status.equalsIgnoreCase("Ativo")) {
                cliente.setStatus(true);
            } else {
                cliente.setStatus(false);
            }
            cliente = clienteRepository.update(cliente);
        }
        return "";
    }

    public String fechar() {
        RequestContext.getCurrentInstance().closeDialog(null);
        return "";
    }

    public boolean validarDados() {
        if (empresa == null && empresa.getIdempresa() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Empresa não selecionada.");
            return false;
        }
        if (cliente.getNomefantasia() == null && cliente.getNomefantasia().length() == 0) {
            Mensagem.lancarMensagemErro("Atenção!", "Nome fantasia não informado.");
            return false;
        }
        if (cliente.getRazaosocial() == null && cliente.getRazaosocial().length() == 0) {
            Mensagem.lancarMensagemErro("Atenção!", "Razão social não informado.");
            return false;
        }
        return true;
    }

    public boolean salvarArquivoFTP() {
        String msg = "";
        Ftpdados dadosFTP = null;
        dadosFTP = ftpDadosRepository.find("select f from Ftpdados f");
        if (dadosFTP == null) {
            return false;
        }
        Ftp ftp = new Ftp(dadosFTP.getHostupload(), dadosFTP.getUsuario(), dadosFTP.getSenha());
        try {
            if (!ftp.conectar()) {
                Mensagem.lancarMensagemErro("Erro conectar FTP", "");
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(CadClienteMB.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem.lancarMensagemErro("Erro conectar FTP", "");
        }
        try {
            msg = ftp.enviarArquivo(file, cliente.getIdcliente() + ".png", "/intime/fotos/cliente/");
            FacesContext context = FacesContext.getCurrentInstance();
            cliente.setNomefoto(cliente.getIdcliente() + ".png");
            context.addMessage(null, new FacesMessage(msg, ""));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CadClienteMB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Arquivo " + ex);
        }
        try {
            ftp.desconectar();
        } catch (IOException ex) {
            Logger.getLogger(CadClienteMB.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem.lancarMensagemErro("Erro conectar FTP", "");
        }
        return false;
    }

    public void fileUploadListener(FileUploadEvent e) {
        this.file = e.getFile();
        salvarArquivoFTP();
        String nome = e.getFile().getFileName();
        try {
            nome = new String(nome.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
