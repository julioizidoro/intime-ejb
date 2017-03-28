/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.usuario;

import br.com.intime.managerBean.cliente.CadClienteMB;
import br.com.intime.model.Departamento;
import br.com.intime.model.Empresa;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Subdepartamento;
import br.com.intime.model.Usuario;
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
    private String status;
    
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        usuario = (Usuario) session.getAttribute("usuario");
        session.removeAttribute("usuario");
        gerarListaEmpresa();
        gerarListaDepartamentos();
        if (usuario == null) {
            usuario = new Usuario();
            empresa = new Empresa();
        } else {
            empresa = usuario.getEmpresa();
            departamento = usuario.getSubdepartamento().getDepartamento();
            subdepartamento = usuario.getSubdepartamento();
            if (usuario.getStatus()) {
                status = "ativo";
            }else{
                status = "inativo";
            }
            nivel = usuario.getNivel();
            gerarListaSubDepartamento();
        } 
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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public List<Subdepartamento> getListaSubDepartamento() {
        return listaSubDepartamento;
    }

    public void setListaSubDepartamento(List<Subdepartamento> listaSubDepartamento) {
        this.listaSubDepartamento = listaSubDepartamento;
    }

    public DepartamentoRepository getDepartamentoRepository() {
        return departamentoRepository;
    }

    public void setDepartamentoRepository(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public SubDepartamentoRepository getSubDepartamentoRepository() {
        return subDepartamentoRepository;
    }

    public void setSubDepartamentoRepository(SubDepartamentoRepository subDepartamentoRepository) {
        this.subDepartamentoRepository = subDepartamentoRepository;
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
    
    public void cancelar(){
        RequestContext.getCurrentInstance().closeDialog(new Usuario());
    }
    
    public String validarDados(){
        String msg = "";
        if (empresa == null) {
            msg = msg + "Empresa Não selecionada \r\n";
        }
        if (usuario.getNome().length() == 0) {
            msg = msg + "Nome do Usuário não informado \r\n";
        }
        if (nivel.length() == 0) {
             msg = msg + "Nivel de usuario não informado \r\n";
        }
        if (status.length() == 0) {
            msg = msg + "Status do usuario não informado \r\n";
        }
        if (subdepartamento == null) {
            msg = msg + "Sub-Departamento não informado \r\n";
        }
        return msg;
    }
    
    public void salvar(){
        List<Usuario> listaUsuario = usuarioRepository.list("Select u from Usuario u where u.login='" + usuario.getLogin() + "' and u.status=true");
        if (listaUsuario == null || listaUsuario.isEmpty()) {
            String senha = "senha";
            try {
                usuario.setSenha(Criptografia.encript(senha));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CadUsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setStatus(true);
            String mensagem = validarDados();
            if (mensagem.length() < 1) {
                usuario.setEmpresa(empresa);
                usuario.setSubdepartamento(subdepartamento);
                usuario = usuarioRepository.update(usuario);
                RequestContext.getCurrentInstance().closeDialog(usuario);
            }
        } else if (usuario.getIdusuario() != null) {
            usuario = usuarioRepository.update(usuario);
            RequestContext.getCurrentInstance().closeDialog(usuario);
        } else {
            Mensagem.lancarMensagemErro("Atenção", "Este login possuí uma conta ativa!");
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
            if (usuario.getIdusuario() == null) {
                Empresa empresa = empresaRepository.find(1);
                usuario.setEmpresa(empresa);
                Subdepartamento subdepartamento = subDepartamentoRepository.find(1);
                usuario.setSubdepartamento(subdepartamento);
                usuario = usuarioRepository.update(usuario);
            }else{
                if (usuario.getNomefoto() != null && usuario.getNomefoto().length() >0) {
                    excluirArquivoFTP();
                }
            }
            msg = ftp.enviarArquivo(file, usuario.getIdusuario() + ".png", "/intime/fotos/usuario/");
            FacesContext context = FacesContext.getCurrentInstance();
            usuario.setNomefoto(usuario.getIdusuario() + ".png");
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
            String nomeArquivoFTP = usuario.getNomefoto();
            msg = ftp.excluirArquivo(nomeArquivoFTP, "/intime/fotos/usuario/");
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
    
    
    public void gerarListaDepartamentos(){
        listaDepartamento = departamentoRepository.list("Select d From Departamento d");
        if (listaDepartamento == null || listaDepartamento.isEmpty()) {
            listaDepartamento = new ArrayList<>();
        }
    }
    
    public void gerarListaSubDepartamento(){
        if (departamento == null) {
            listaSubDepartamento = new ArrayList<>();
        }else{
            listaSubDepartamento = departamento.getSubdepartamentoList();
        }
    }
}
