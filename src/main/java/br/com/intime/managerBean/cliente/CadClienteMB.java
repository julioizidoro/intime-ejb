package br.com.intime.managerBean.cliente;

import br.com.intime.model.Cliente;
import br.com.intime.model.Clientedepartamento;
import br.com.intime.model.Departamento;
import br.com.intime.model.Empresa;
import br.com.intime.model.Ftpdados;
import br.com.intime.repository.ClienteDepartamentoRepository;
import br.com.intime.repository.ClienteRepository;
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.repository.EmpresaRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.util.Ftp;
import br.com.intime.util.Mensagem;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.AbstractList;
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
    private Departamento departamento;
    private List<Departamento> listaDepartamento;
    private List<Clientedepartamento> listaClienteDepartamento;
    @EJB
    private ClienteDepartamentoRepository clienteDepartamentoRepositoory;
    @EJB
    private DepartamentoRepository departamentoRepository;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        cliente = (Cliente) session.getAttribute("cliente");
        session.removeAttribute("cliente");
        if (cliente == null) {
            cliente = new Cliente();
            empresa = new Empresa();
            listaClienteDepartamento = new ArrayList<>();
        } else {
            empresa = cliente.getEmpresa();
            if (cliente.getStatus()) {
                status = "Ativo";
            } else {
                status = "Inativo";
            }
            listarClienteDepartamentos();
        }
        gerarListaEmpresa();
        listarDepartamentos();
        //verificarLista();
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Clientedepartamento> getListaClienteDepartamento() {
        return listaClienteDepartamento;
    }

    public void setListaClienteDepartamento(List<Clientedepartamento> listaClienteDepartamento) {
        this.listaClienteDepartamento = listaClienteDepartamento;
    }
    
    

    public String salvar() {
        if (validarDados()) {
            cliente.setEmpresa(empresa);
            if (status.equalsIgnoreCase("Ativo")) {
                cliente.setStatus(true);
            } else {
                cliente.setStatus(false);
            }
            cliente = clienteRepository.update(cliente);
            if (listaClienteDepartamento != null) {
                if (listaClienteDepartamento.size() > 0) {
                    for (int i = 0; i < listaClienteDepartamento.size(); i++) {
                        listaClienteDepartamento.get(i).setCliente(cliente);
                        clienteDepartamentoRepositoory.update(listaClienteDepartamento.get(i));
                    }
                }
                listaClienteDepartamento = new ArrayList<>();
            }
            RequestContext.getCurrentInstance().closeDialog(null);
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
            if(cliente.getIdcliente()==null){
                Empresa empresa = empresaRepository.find(1);
                cliente.setEmpresa(empresa);
                cliente = clienteRepository.update(cliente);
            }else{
                if(cliente.getNomefoto()!=null && cliente.getNomefoto().length()>0){
                    excluirArquivoFTP();
                }
            }
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
    
    public boolean excluirArquivoFTP() {
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
            Mensagem.lancarMensagemErro("Erro conectar FTP", "Erro");
        }
        try {
            String nomeArquivoFTP = cliente.getNomefoto();
            msg = ftp.excluirArquivo(nomeArquivoFTP, "/intime/fotos/cliente/");
            FacesContext context = FacesContext.getCurrentInstance();
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
            Mensagem.lancarMensagemErro("Erro desconectar FTP", "Erro");
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
    
    
    public void adicionarDepartamento(){
        Clientedepartamento clientedepartamento = new Clientedepartamento();
        if (departamento != null) {
            if (listaClienteDepartamento != null) {
                clientedepartamento.setCliente(cliente);
                clientedepartamento.setDepartamento(departamento);
                listaClienteDepartamento.add(clientedepartamento);
                //clienteDepartamentoRepositoory.update(clientedepartamento);
                clientedepartamento = null;
                listaDepartamento.remove(departamento);
            }
        }else{
            Mensagem.lancarMensagemInfo("Escola um departamento", "");
        }
    }
    
    
    public void excluirDepartamento(Clientedepartamento clientedepartamento){
        listaDepartamento.add(clientedepartamento.getDepartamento());
        listaClienteDepartamento.remove(clientedepartamento);
        if (clientedepartamento.getIdclientedepartamento() != null) {
            clienteDepartamentoRepositoory.remove(clientedepartamento.getIdclientedepartamento());
        }
        Mensagem.lancarMensagemInfo("Excluido com sucesso", "");
    }
    
    
    public void listarDepartamentos(){
        listaDepartamento = departamentoRepository.list("Select d From Departamento d");
        if (listaDepartamento == null || listaDepartamento.isEmpty()) {
            listaDepartamento = new ArrayList<Departamento>();
        }
    }
    
    
    public void listarClienteDepartamentos(){
        listaClienteDepartamento = clienteDepartamentoRepositoory.list("Select c From Clientedepartamento c Where c.cliente.idcliente=" + cliente.getIdcliente());
        if (listaClienteDepartamento == null || listaClienteDepartamento.isEmpty()) {
            listaClienteDepartamento = new ArrayList<>();
        }
    }
    
    
    public void verificarLista(){
        if (listaDepartamento != null && listaClienteDepartamento != null) {
            if (listaClienteDepartamento.size() > 0 && listaDepartamento.size() > 0) {
                for (int i = 0; i < listaDepartamento.size(); i++) {
                    if (i <= listaClienteDepartamento.size()) {
                        int idclienteDepartamento = listaClienteDepartamento.get(i).getIdclientedepartamento();
                        int iddepartamento = listaDepartamento.get(i).getIddepartamento();
                        if (iddepartamento == idclienteDepartamento) {
                            listaDepartamento.remove(listaClienteDepartamento.get(i));
                        }
                    }
                }
            }
        }
    }
}
