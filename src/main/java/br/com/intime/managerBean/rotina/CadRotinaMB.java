/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Clientedepartamento;
import br.com.intime.model.Departamento;
import br.com.intime.model.Rotina;
import br.com.intime.model.Rotinacliente;
import br.com.intime.model.Subdepartamento;
import br.com.intime.repository.ClienteDepartamentoRepository;
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.repository.RotinaAnualRepository;
import br.com.intime.repository.RotinaClienteRepository;
import br.com.intime.repository.RotinaDiarioRepository;
import br.com.intime.repository.RotinaMensalRepository;
import br.com.intime.repository.RotinaRepository;
import br.com.intime.repository.RotinaSemanalRepository;
import br.com.intime.repository.SubDepartamentoRepository; 
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class CadRotinaMB implements Serializable {

    @EJB
    private DepartamentoRepository departamentoRepository;
    @EJB
    private SubDepartamentoRepository subDepartamentoRepository;
    @EJB
    private RotinaRepository rotinaRepository;
    @EJB
    private RotinaClienteRepository rotinaClienteRepository;
    @EJB
    private ClienteDepartamentoRepository clienteDepartamentoRepository;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @EJB
    private RotinaDiarioRepository rotinaDiarioRepository;
    @EJB
    private RotinaMensalRepository rotinaMensalRepository;
    @EJB
    private RotinaSemanalRepository rotinaSemanalRepository;
    @EJB
    private RotinaAnualRepository rotinaAnualRepository;
    private List<Departamento> listaDepartamento;
    private Departamento departamento;
    private List<Subdepartamento> listaSubDepartamento;
    private Subdepartamento subdepartamento;
    private Rotina rotina;
    private List<Clientedepartamento> listaCliente;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        rotina = (Rotina) session.getAttribute("rotina");
        session.removeAttribute("rotina");
        gerarListaDepartamento();
        if (rotina != null) {
            gerarListaClientes();
            departamento = rotina.getSubdepartamento().getDepartamento();
            listarSubDepartamento();
            subdepartamento = rotina.getSubdepartamento();
        }else{
            rotina = new Rotina();
        }
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Subdepartamento> getListaSubDepartamento() {
        return listaSubDepartamento;
    }

    public void setListaSubDepartamento(List<Subdepartamento> listaSubDepartamento) {
        this.listaSubDepartamento = listaSubDepartamento;
    }

    public Subdepartamento getSubdepartamento() {
        return subdepartamento;
    }

    public void setSubdepartamento(Subdepartamento subdepartamento) {
        this.subdepartamento = subdepartamento;
    }

    public DepartamentoRepository getDepartamentoRepository() {
        return departamentoRepository;
    }

    public void setDepartamentoRepository(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public SubDepartamentoRepository getSubDepartamentoRepository() {
        return subDepartamentoRepository;
    }

    public void setSubDepartamentoRepository(SubDepartamentoRepository subDepartamentoRepository) {
        this.subDepartamentoRepository = subDepartamentoRepository;
    }

    public RotinaRepository getRotinaRepository() {
        return rotinaRepository;
    }

    public void setRotinaRepository(RotinaRepository rotinaRepository) {
        this.rotinaRepository = rotinaRepository;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public ClienteDepartamentoRepository getClienteDepartamentoRepository() {
        return clienteDepartamentoRepository;
    }

    public void setClienteDepartamentoRepository(ClienteDepartamentoRepository clienteDepartamentoRepository) {
        this.clienteDepartamentoRepository = clienteDepartamentoRepository;
    }

    public List<Clientedepartamento> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Clientedepartamento> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public RotinaClienteRepository getRotinaClienteRepository() {
        return rotinaClienteRepository;
    }

    public void setRotinaClienteRepository(RotinaClienteRepository rotinaClienteRepository) {
        this.rotinaClienteRepository = rotinaClienteRepository;
    }

    public RotinaDiarioRepository getRotinaDiarioRepository() {
        return rotinaDiarioRepository;
    }

    public void setRotinaDiarioRepository(RotinaDiarioRepository rotinaDiarioRepository) {
        this.rotinaDiarioRepository = rotinaDiarioRepository;
    }

    public RotinaMensalRepository getRotinaMensalRepository() {
        return rotinaMensalRepository;
    }

    public void setRotinaMensalRepository(RotinaMensalRepository rotinaMensalRepository) {
        this.rotinaMensalRepository = rotinaMensalRepository;
    }

    public RotinaSemanalRepository getRotinaSemanalRepository() {
        return rotinaSemanalRepository;
    }

    public void setRotinaSemanalRepository(RotinaSemanalRepository rotinaSemanalRepository) {
        this.rotinaSemanalRepository = rotinaSemanalRepository;
    }

    public RotinaAnualRepository getRotinaAnualRepository() {
        return rotinaAnualRepository;
    }

    public void setRotinaAnualRepository(RotinaAnualRepository rotinaAnualRepository) {
        this.rotinaAnualRepository = rotinaAnualRepository;
    }

    public void listarSubDepartamento() {
        if (departamento == null) {
            listaSubDepartamento = new ArrayList<>();
        } else {
            String sql = "Select s From Subdepartamento s where s.departamento.iddepartamento="
                    + departamento.getIddepartamento() + " and s.status=true order by s.nome";
            listaSubDepartamento = subDepartamentoRepository.list(sql);
        }
    }

    public void cadastrarFuncoesRotina(Clientedepartamento clientedepartamento) {
        if (usuarioLogadoMB.getUsuario().getCadastrorotina() == 3
                || usuarioLogadoMB.getUsuario().getCadastrorotina() == 4) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 700);
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("rotina", rotina);
            session.setAttribute("cliente", clientedepartamento.getCliente());
            RequestContext.getCurrentInstance().openDialog("cadFuncoesRotina", options, null);
        } else {
            Mensagem.lancarMensagemWarn("Acesso Negado!", "");
        }
    }

    public void editarRotina(Clientedepartamento clientedepartamento) {
        if (usuarioLogadoMB.getUsuario().getCadastrorotina() == 3
                || usuarioLogadoMB.getUsuario().getCadastrorotina() == 4) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 700);
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("rotina", rotina);
            session.setAttribute("cliente", clientedepartamento.getCliente());
            RequestContext.getCurrentInstance().openDialog("cadFuncoesRotina", options, null);
        } else {
            Mensagem.lancarMensagemWarn("Acesso Negado!", "");
        }
    }

    public void excluirRotina(Clientedepartamento clientedepartamento) {
        if (usuarioLogadoMB.getUsuario().getCadastrorotina() == 4) {
            Rotinacliente rotinacliente = rotinaClienteRepository.find(
                "select r from Rotinacliente r where r.rotina.idrotina="+rotina.getIdrotina()
                +" and r.cliente.idcliente="+clientedepartamento.getCliente().getIdcliente());
            if(rotinacliente!=null){
                rotinaClienteRepository.remove(rotinacliente.getIdrotinacliente());
                Mensagem.lancarMensagemInfo("Excluído com sucesso.", "");
                gerarListaClientes();
            }
        } else {
            Mensagem.lancarMensagemWarn("Acesso Negado!", "");
        }
    }

    public String agendaRotina() {
        return "consAgendaRotina";
    }

    public void gerarListaDepartamento() {
        listaDepartamento = departamentoRepository.list("Select d From Departamento d");
        if (listaDepartamento == null || listaDepartamento.isEmpty()) {
            listaDepartamento = new ArrayList<Departamento>();
        }
    }

    public void salvarRotina() {
        if (departamento != null && departamento.getIddepartamento() != null) {
            if (subdepartamento != null && subdepartamento.getIdsubdepartamento() != null) {
                rotina.setSubdepartamento(subdepartamento);
                rotina = rotinaRepository.update(rotina);
                gerarListaClientes();
                Mensagem.lancarMensagemInfo("Salvo com sucesso!", "");
            } else {
                Mensagem.lancarMensagemErro("Atenção!", "Sub-Departamento não informado.");
            }
        } else {
            Mensagem.lancarMensagemErro("Atenção!", "Departamento não informado.");
        }
    }

    public void gerarListaClientes() {
        listaCliente = clienteDepartamentoRepository.list(
                "select c from Clientedepartamento c where c.departamento.iddepartamento="
                + rotina.getSubdepartamento().getDepartamento().getIddepartamento()
                + " order by c.cliente.apelido");
        if (listaCliente == null) {
            listaCliente = new ArrayList<Clientedepartamento>();
        }else{
            for (int i = 0; i < listaCliente.size(); i++) {
                Rotinacliente rotinacliente = rotinaClienteRepository.find(
                    "select r from Rotinacliente r where r.rotina.idrotina="+rotina.getIdrotina()
                    +" and r.cliente.idcliente="+listaCliente.get(i).getCliente().getIdcliente());
                if(rotinacliente!=null){
                    rotinacliente.setDatainicial(Date.from(rotinacliente.getDatainicio().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    if(rotinacliente.getDatatermino()!=null){
                        rotinacliente.setDatafinal(Date.from(rotinacliente.getDatatermino().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    }
                    listaCliente.get(i).setRotinacliente(rotinacliente);
                }else{
                    rotinacliente = new Rotinacliente();
                    listaCliente.get(i).setRotinacliente(rotinacliente);
                }
            }
        }
    }
    
    public String cancelar(){
        return "consRotina";
    }
    
    public String corPrioridade(Clientedepartamento clientedepartamento){
        if(clientedepartamento.getRotinacliente()!=null && clientedepartamento.getRotinacliente().getIdrotinacliente()!=null){
            if(clientedepartamento.getRotinacliente().getPrioridade().equalsIgnoreCase("Urgente")){
                return "red";
            }else if(clientedepartamento.getRotinacliente().getPrioridade().equalsIgnoreCase("Importante")){
                return "#ffdd1d";
            }else{
                return "transparent";
            }
        }
        return "transparent";
    }
    
    public boolean desabilitarEditarExcluir(Clientedepartamento clientedepartamento){
        if(clientedepartamento.getRotinacliente()!=null && clientedepartamento.getRotinacliente().getIdrotinacliente()!=null){
            return false;
        }else return true;
    }
    
    public boolean desabilitarNovo(Clientedepartamento clientedepartamento){
        if(clientedepartamento.getRotinacliente()!=null && clientedepartamento.getRotinacliente().getIdrotinacliente()!=null){
            return true;
        }else return false;
    }
}
