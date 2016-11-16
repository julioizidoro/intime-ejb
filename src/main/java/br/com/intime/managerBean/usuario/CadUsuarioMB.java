/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.usuario;

import br.com.intime.managerBean.cliente.CadClienteMB;
import br.com.intime.model.Acesso;
import br.com.intime.model.Cliente;
import br.com.intime.model.Departamento;
import br.com.intime.model.Empresa;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Subdepartamento;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AcessoRepository;
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.repository.EmpresaRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.repository.SubDepartamentoRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Criptografia;
import br.com.intime.util.Ftp;
import br.com.intime.util.Mensagem;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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


@Named
@ViewScoped
public class CadUsuarioMB implements Serializable{
    
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    @EJB
    private UsuarioRepository usuarioRepository;
    private Empresa empresa;
    private List<Empresa> listaEmpresa;
    @EJB
    private EmpresaRepository empresaRepository;
    private Acesso acesso;
    @EJB
    private AcessoRepository acessoRepository;
    private UploadedFile file;
    private FileUploadEvent ex;
    @EJB
    private FtpDadosRepository ftpDadosRepository;
    private String nivel;
    private Departamento departamento;
    private Subdepartamento subdepartamento;
    private List<Departamento> listaDepartamento;
    private List<Subdepartamento> listaSubDepartamento;
    @EJB
    private DepartamentoRepository departamentoRepository;
    @EJB
    private SubDepartamentoRepository subDepartamentoRepository;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        usuario = (Usuario) session.getAttribute("usuario");
        session.removeAttribute("usuario");
        if (usuario == null) {
            usuario = new Usuario();
            empresa = new Empresa();
        } else {
            empresa = usuario.getEmpresaIdempresa();
        }
        gerarListaEmpresa();
        acesso = acessoRepository.find(1);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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

    public EmpresaRepository getEmpresaRepository() {
        return empresaRepository;
    }

    public void setEmpresaRepository(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public AcessoRepository getAcessoRepository() {
        return acessoRepository;
    }

    public void setAcessoRepository(AcessoRepository acessoRepository) {
        this.acessoRepository = acessoRepository;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public FileUploadEvent getEx() {
        return ex;
    }

    public void setEx(FileUploadEvent ex) {
        this.ex = ex;
    }

    public FtpDadosRepository getFtpDadosRepository() {
        return ftpDadosRepository;
    }

    public void setFtpDadosRepository(FtpDadosRepository ftpDadosRepository) {
        this.ftpDadosRepository = ftpDadosRepository;
    }
    
    
    
    
    public void gerarListaEmpresa() {
        listaEmpresa = empresaRepository.list("Select e from Empresa e");
        if (listaEmpresa == null) {
            listaEmpresa = new ArrayList<Empresa>();
        }
    }
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public String validarDados(){
        String msg = "";
        if (empresa == null) {
            msg = msg + "Empresa Não selecionada \r\n";
        }
        if (usuario.getNome().length() == 0) {
            msg = msg + "Nome do Usuário não informado \r\n";
        }
        return msg;
    }
    
    public void salvar(){
        List<Usuario> listaUsuario = usuarioRepository.list("Select u from Usuario u where u.login='" + usuario.getLogin() + "'");
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            try {
                usuario.setSenha(Criptografia.encript(usuario.getSenha()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CadUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setStatus(true);
            String mensagem = validarDados();
            if (mensagem.length() < 1) {
                usuario.setEmpresaIdempresa(empresa);
                usuario.setAcessoIdacesso(acesso);
                usuario = usuarioRepository.update(usuario);
//                if (file != null) {
//                    salvarArquivoFTP();
//                } 
                RequestContext.getCurrentInstance().closeDialog(usuario);
            }
        } else if (usuario.getIdusuario() != null) {
            usuario = usuarioRepository.update(usuario);
            RequestContext.getCurrentInstance().closeDialog(usuario);
        } else {
            String msg = "este login ja tem uma conta existente!!";
        }
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
            Logger.getLogger(CadUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            Mensagem.lancarMensagemErro("Erro conectar FTP", "");
        }
        try {
            msg = ftp.enviarArquivo(file, 10 + ".png", "/intime/fotos/usuario/");
            FacesContext context = FacesContext.getCurrentInstance();
            usuario.setNomefoto(10+ ".png");
            context.addMessage(null, new FacesMessage(msg, ""));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CadUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro Salvar Arquivo " + ex);
        }
        try {
            ftp.desconectar();
        } catch (IOException ex) {
            Logger.getLogger(CadUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
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
