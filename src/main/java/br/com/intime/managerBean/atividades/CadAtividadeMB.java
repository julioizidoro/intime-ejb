/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Cliente;
import br.com.intime.model.Clientedepartamento;
import br.com.intime.model.Subdepartamento;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.ClienteDepartamentoRepository;
import br.com.intime.repository.ClienteRepository;
import br.com.intime.repository.SubDepartamentoRepository;
import br.com.intime.repository.UsuarioRepository;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wolverine
 */
@Named
@ViewScoped
public class CadAtividadeMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    private Atividadeusuario atividadeusuario;
    private Cliente cliente;
    private Clientedepartamento clientedepartamento;
    private List<Cliente> listaCliente;
    private List<Clientedepartamento> listaClientedepartamentos;
    private List<Subdepartamento> listaSubdepartamento;
    private String nomeCliente;
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    private List<Usuario> listaUsuarioSelecionado;
    @EJB
    private ClienteRepository clienteRepository;
    @EJB
    private ClienteDepartamentoRepository clienteDepartamentoRepository;
    @EJB
    private SubDepartamentoRepository subDepartamentoRepository;
    @EJB
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        gerarListaUsuario();
        if (atividadeusuario == null) {
            atividadeusuario = new Atividadeusuario();
            atividadeusuario.setAtividade(new Atividade());
            atividadeusuario.setSituacao("execucao");
            atividadeusuario.setUsuario(usuarioLogadoMB.getUsuario());
            atividadeusuario.getAtividade().setDatalancamento(LocalDate.now());
            atividadeusuario.getAtividade().setNotificacaohorario(false);
            atividadeusuario.getAtividade().setPrioridade("normal");
            atividadeusuario.getAtividade().setRotina(false);
            atividadeusuario.getAtividade().setSubdepartamento(usuarioLogadoMB.getUsuario().getSubdepartamento());
            cliente = new Cliente();
            usuario = usuarioLogadoMB.getUsuario();
            clientedepartamento = new Clientedepartamento();
            listaClientedepartamentos = new ArrayList<Clientedepartamento>();
            listaSubdepartamento = new ArrayList<Subdepartamento>();
        } else {
            cliente = atividadeusuario.getAtividade().getCliente();
            clientedepartamento = clienteDepartamentoRepository.find("Select c From Clientedepartamento c"
                    + " where c.cliente.idcliente=" + cliente.getIdcliente() + " c.departamento.iddepartamento=" 
                    + atividadeusuario.getAtividade().getSubdepartamento().getDepartamento());
        } 
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Clientedepartamento getClientedepartamento() {
        return clientedepartamento;
    }

    public void setClientedepartamento(Clientedepartamento clientedepartamento) {
        this.clientedepartamento = clientedepartamento;
    }
  

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Clientedepartamento> getListaClientedepartamentos() {
        return listaClientedepartamentos;
    }

    public void setListaClientedepartamentos(List<Clientedepartamento> listaClientedepartamentos) {
        this.listaClientedepartamentos = listaClientedepartamentos;
    }

    public ClienteDepartamentoRepository getClienteDepartamentoRepository() {
        return clienteDepartamentoRepository;
    }

    public void setClienteDepartamentoRepository(ClienteDepartamentoRepository clienteDepartamentoRepository) {
        this.clienteDepartamentoRepository = clienteDepartamentoRepository;
    }

    public List<Subdepartamento> getListaSubdepartamento() {
        return listaSubdepartamento;
    }

    public void setListaSubdepartamento(List<Subdepartamento> listaSubdepartamento) {
        this.listaSubdepartamento = listaSubdepartamento;
    }

    public SubDepartamentoRepository getSubDepartamentoRepository() {
        return subDepartamentoRepository;
    }

    public void setSubDepartamentoRepository(SubDepartamentoRepository subDepartamentoRepository) {
        this.subDepartamentoRepository = subDepartamentoRepository;
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

    public List<Usuario> getListaUsuarioSelecionado() {
        return listaUsuarioSelecionado;
    }

    public void setListaUsuarioSelecionado(List<Usuario> listaUsuarioSelecionado) {
        this.listaUsuarioSelecionado = listaUsuarioSelecionado;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String salvar() {
        if (atividadeusuario.getAtividade().getHoraexecucao() == null) {
            LocalTime hora = LocalTime.of(23, 59, 00);
            atividadeusuario.getAtividade().setHoraexecucao(hora);
        } 
        atividadeusuario.getAtividade().setCliente(cliente);
        atividadeUsuarioRepository.update(atividadeusuario);
        return "";
    }

    public void selecionarCliente(Cliente cliente) {
        this.cliente = cliente;
        nomeCliente = cliente.getNomefantasia();
        gerarListaDepartamento();
    }

    public void gerarListaCliente() {
        listaCliente = clienteRepository.list("Select c from Cliente c where c.nomefantasia like '" + nomeCliente + "%'");
        if (listaCliente == null) {
            listaCliente = new ArrayList<Cliente>();
        }
    }

    public void gerarListaDepartamento() {
        listaClientedepartamentos = clienteDepartamentoRepository.list("Select c From Clientedepartamento c"
                + " where c.cliente.idcliente=" + cliente.getIdcliente() + " order by c.departamento.nome");
    }

    public void gerarListaSubDepartamento() {
        if (clientedepartamento != null && clientedepartamento.getIdclientedepartamento() != null){
            listaSubdepartamento = subDepartamentoRepository.list("Select s From Subdepartamento s"
                    + " where s.departamento.iddepartamento=" + clientedepartamento.getDepartamento().getIddepartamento() + " order by s.nome");
        }
    }
    
    public void gerarListaUsuario(){
        listaUsuario = usuarioRepository.list("select u from Usuario u where u.status=true order by u.nome");
        if(listaUsuario==null){
            listaUsuario =  new ArrayList<Usuario>();
        }
    }

}
