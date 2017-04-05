/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.dashboard; 

import br.com.intime.managerBean.cliente.CadClienteMB;
import br.com.intime.managerBean.usuario.CadUsuarioMB;
import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Empresa;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Subdepartamento;
import br.com.intime.model.Usuario;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.util.Ftp;
import br.com.intime.util.Mensagem;
import java.io.IOException;
import java.io.Serializable; 
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB; 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped; 
import javax.inject.Inject;
import javax.inject.Named;  
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class EditarImagemUsuarioMB implements Serializable{
    
    /**
    * 
    */
    private static final long serialVersionUID = 1L; 
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private UploadedFile file;
    private FileUploadEvent ex;
    @EJB
    private FtpDadosRepository ftpDadosRepository; 
    private Usuario usuario;
    
    @PostConstruct
    public void init() { 
        usuario = usuarioLogadoMB.getUsuario();
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

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
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
            if (usuario.getNomefoto() != null && usuario.getNomefoto().length() > 0) {
                excluirArquivoFTP();
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
    
    public void fechar(){
        RequestContext.getCurrentInstance().closeDialog(usuario);
    }
}
