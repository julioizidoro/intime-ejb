package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Cliente;
import br.com.intime.model.Clientedepartamento;
import br.com.intime.model.Notificacao;
import br.com.intime.model.Subdepartamento;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.ClienteDepartamentoRepository;
import br.com.intime.repository.ClienteRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.repository.SubDepartamentoRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Formatacao;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class CadAtividadeMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Atividadeusuario atividadeusuario;
    private Cliente cliente;
    private Clientedepartamento clientedepartamento;
    private List<Cliente> listaCliente;
    private List<Clientedepartamento> listaClientedepartamentos;
    private List<Subdepartamento> listaSubdepartamento;
    private String nomeCliente="";
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    private List<Usuario> listaUsuarioSelecionado;
    private boolean novo=true;
    @EJB
    private ClienteRepository clienteRepository;
    @EJB
    private ClienteDepartamentoRepository clienteDepartamentoRepository;
    @EJB
    private SubDepartamentoRepository subDepartamentoRepository;
    @EJB
    private UsuarioRepository usuarioRepository;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeRepository atividadeRepository;
    @EJB
    private NotificacoesRepository notificacoesRepository;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        session.removeAttribute("atividadeusuario");
        gerarListaUsuario();
        nomeCliente = "";
        gerarListaCliente();
        if (atividadeusuario == null) {
            atividadeusuario = new Atividadeusuario();
            atividadeusuario.setAtividade(new Atividade());
            atividadeusuario.setSituacao("Play");
            atividadeusuario.setUsuario(usuarioLogadoMB.getUsuario());
            atividadeusuario.getAtividade().setDatalancamento(LocalDate.now());
            atividadeusuario.getAtividade().setNotificacaohorario(false);
            atividadeusuario.getAtividade().setPrioridade("Regular");
            atividadeusuario.getAtividade().setRotina(false);
            atividadeusuario.setTempo("00:00");
            atividadeusuario.getAtividade().setSubdepartamento(usuarioLogadoMB.getUsuario().getSubdepartamento());
            cliente = new Cliente();
            usuario = usuarioLogadoMB.getUsuario();
            clientedepartamento = new Clientedepartamento();
            gerarDadosDefaut();
        } else {
            novo=false;
            cliente = atividadeusuario.getAtividade().getCliente();
            nomeCliente = cliente.getNomefantasia();
            gerarListaDepartamento();  
            clientedepartamento = clienteDepartamentoRepository.find("Select c From Clientedepartamento c"
                    + " where c.cliente.idcliente=" + cliente.getIdcliente() + " and c.departamento.iddepartamento="
                    + atividadeusuario.getAtividade().getSubdepartamento().getDepartamento().getIddepartamento());
            gerarListaSubDepartamento();
            mostrarDataHora();
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

    public AtividadeRepository getAtividadeRepository() {
        return atividadeRepository;
    }

    public void setAtividadeRepository(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    public NotificacoesRepository getNotificacoesRepository() {
        return notificacoesRepository;
    }

    public void setNotificacoesRepository(NotificacoesRepository notificacoesRepository) {
        this.notificacoesRepository = notificacoesRepository;
    }

    public void salvar() {
        if (validarDados()) {
            atividadeusuario.getAtividade().setDataexecucao(
                    Formatacao.converterDateParaLocalDate(atividadeusuario.getAtividade().getDataexecutar()));
            if (atividadeusuario.getAtividade().getHorario() == null) {
                LocalTime hora = LocalTime.of(23, 59);
                atividadeusuario.getAtividade().setHoraexecucao(hora);
            } else {
                atividadeusuario.getAtividade().setHoraexecucao(
                        Formatacao.converterStringParaLocalTime(atividadeusuario.getAtividade().getHorario()));
            }
            if (atividadeusuario.getAtividade().getMeta() != null
                    && atividadeusuario.getAtividade().getMeta().length() > 0) {
                atividadeusuario.getAtividade().setMetatempo(
                        Formatacao.converterStringParaLocalTime(atividadeusuario.getAtividade().getMeta()));
            }
            atividadeusuario.getAtividade().setCliente(cliente);
            if (atividadeusuario.getAtividade().getIdatividade() == null) {
                atividadeusuario.getAtividade().setUsuario(usuarioLogadoMB.getUsuario());
            }
            atividadeusuario.setAtividade(atividadeRepository.update(atividadeusuario.getAtividade()));
            atividadeUsuarioRepository.update(atividadeusuario);
            if(novo){
                Notificacao notificacao;
                if (listaUsuarioSelecionado != null && listaUsuarioSelecionado.size() > 0) {
                    for (int i = 0; listaUsuarioSelecionado.size() > i; i++) {
                        Atividadeusuario atividadeUsuarioSelecionados = new Atividadeusuario();
                        atividadeUsuarioSelecionados.setAtividade(atividadeusuario.getAtividade());
                        atividadeUsuarioSelecionados.setUsuario(listaUsuarioSelecionado.get(i));
                        atividadeUsuarioSelecionados.setTempo("00:00");
                        atividadeUsuarioSelecionados.setSituacao("Play");  
                        atividadeUsuarioRepository.update(atividadeUsuarioSelecionados);
                        notificacao = new Notificacao();
                        notificacao.setLido(false);
                        notificacao.setUsuario(listaUsuarioSelecionado.get(i));
                        notificacao.setDescricao(usuarioLogadoMB.getUsuario().getNome() + " lhe encaminhou a tarefa '"
                                + atividadeusuario.getAtividade().getDescricao() + "'.");
                        notificacoesRepository.update(notificacao);
                    }
                }
                if (atividadeusuario.getUsuario() != usuarioLogadoMB.getUsuario()) {
                    notificacao = new Notificacao();
                    notificacao.setLido(false);
                    notificacao.setUsuario(atividadeusuario.getUsuario());
                    notificacao.setDescricao(usuarioLogadoMB.getUsuario().getNome() + " lhe encaminhou a tarefa '"
                            + atividadeusuario.getAtividade().getDescricao() + "'.");
                    notificacoesRepository.update(notificacao);
                }
            }
            RequestContext.getCurrentInstance().closeDialog(null);
        }
    }

    public boolean validarDados() {
        if (cliente == null || cliente.getIdcliente() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Cliente não selecionado.");
            return false;
        }
        if (clientedepartamento == null || clientedepartamento.getIdclientedepartamento() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Departamento não selecionado.");
            return false;
        }
        if (atividadeusuario.getAtividade().getSubdepartamento() == null
                || atividadeusuario.getAtividade().getSubdepartamento().getIdsubdepartamento() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Subdepartamento não selecionado.");
            return false;
        }
        if (atividadeusuario.getUsuario() == null
                || atividadeusuario.getUsuario().getIdusuario() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Usuário não selecionado.");
            return false;
        }
        if (atividadeusuario.getAtividade().getDescricao() == null
                || atividadeusuario.getAtividade().getDescricao().length() == 0) {
            Mensagem.lancarMensagemErro("Atenção!", "Título não informado.");
            return false;
        }
        if (atividadeusuario.getAtividade().getDataexecutar() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Data não informada.");
            return false;
        }
        return true;
    }
 

    public void gerarListaCliente() {
        listaCliente = clienteRepository.list("Select c from Cliente c where c.nomefantasia like '%" + nomeCliente + "%' and c.status=1 order by c.nomefantasia");
        if (listaCliente == null) {
            listaCliente = new ArrayList<Cliente>();
        }
    }

    public void gerarListaDepartamento() {
        listaClientedepartamentos = clienteDepartamentoRepository.list("Select c From Clientedepartamento c"
                + " where c.cliente.idcliente=" + cliente.getIdcliente() + " order by c.departamento.nome");
    }

    public void gerarListaSubDepartamento() {
        if (clientedepartamento != null && clientedepartamento.getIdclientedepartamento() != null) {
            listaSubdepartamento = subDepartamentoRepository.list("Select s From Subdepartamento s"
                    + " where s.departamento.iddepartamento=" + clientedepartamento.getDepartamento().getIddepartamento() + " and s.status=1 order by s.nome");
        }
    }

    public void gerarListaUsuario() {
        listaUsuario = usuarioRepository.list("select u from Usuario u where u.status=TRUE order by u.nome");
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<Usuario>();
        }
    }

    public void gerarNotificacaoHorario() {
        if (!atividadeusuario.getAtividade().isNotificacaohorario()) {
            atividadeusuario.getAtividade().setNotificacaohorario(true);
        } else {
            atividadeusuario.getAtividade().setNotificacaohorario(false);
        }
    }

    public String visualizarComentarios() {
        atividadeusuario.getAtividade().setDataexecucao(
                Formatacao.converterDateParaLocalDate(atividadeusuario.getAtividade().getDataexecutar()));
        if (atividadeusuario.getAtividade().getHorario() == null) {
            LocalTime hora = LocalTime.of(23, 59);
            atividadeusuario.getAtividade().setHoraexecucao(hora);
        } else {
            atividadeusuario.getAtividade().setHoraexecucao(
                    Formatacao.converterStringParaLocalTime(atividadeusuario.getAtividade().getHorario()));
        }
        if (atividadeusuario.getAtividade().getMeta() != null
                && atividadeusuario.getAtividade().getMeta().length() > 0) {
            atividadeusuario.getAtividade().setMetatempo(
                    Formatacao.converterStringParaLocalTime(atividadeusuario.getAtividade().getMeta()));
        }
        atividadeusuario.getAtividade().setCliente(cliente);
        if (atividadeusuario.getAtividade().getIdatividade() == null) {
            atividadeusuario.getAtividade().setUsuario(usuarioLogadoMB.getUsuario());
        }
        atividadeusuario.setAtividade(atividadeRepository.update(atividadeusuario.getAtividade()));
        atividadeusuario = atividadeUsuarioRepository.update(atividadeusuario);
        if (atividadeusuario.getIdatividadeusuario() != null) {
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("contentWidth", 500);
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.setAttribute("atividadeusuario", atividadeusuario);
            RequestContext.getCurrentInstance().openDialog("comentariosAtividades", options, null);
        }
        return "";
    }

    public String retornarCorBtnHorario() {
        if (atividadeusuario.getAtividade().getHorario() == null
                || atividadeusuario.getAtividade().getHorario().length() == 0) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public String retornarCorBtnMeta() {
        if (atividadeusuario.getAtividade().getMeta() == null
                || atividadeusuario.getAtividade().getMeta().length() == 0) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public String retornarCorBtnPrioridade() {
        if (atividadeusuario.getAtividade().getPrioridade().equalsIgnoreCase("Regular")) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public String retornarCorBtnUsuario() {
        if (atividadeusuario.getUsuario() == null) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public String retornarCorBtnListaUsuarios() {
        if (listaUsuarioSelecionado == null || listaUsuarioSelecionado.size() == 0) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public String retornarCorBtnComentario() {
        if (atividadeusuario.getAtividadecomentarioList() == null
                || atividadeusuario.getAtividadecomentarioList().size() == 0) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public String retornarCorBtnRotina() {
        if (!atividadeusuario.getAtividade().isRotina()) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public String retornarCorBtnNotificacao() {
        if (!atividadeusuario.getAtividade().isNotificacaohorario()) {
            return "#C6C6C6";
        } else {
            return "#0040FF";
        }
    }

    public void mostrarDataHora() {
        //data
        Date dataexecutar = Date.from(atividadeusuario.getAtividade().getDataexecucao().atStartOfDay(ZoneId.systemDefault()).toInstant());
        atividadeusuario.getAtividade().setDataexecutar(dataexecutar);
        //horario 
        LocalTime hora = atividadeusuario.getAtividade().getHoraexecucao();
        String horaMostrar = "";
        int ih = hora.getHour();
        int im = hora.getMinute();
        if (ih <= 9) {
            horaMostrar = "0";
        }
        horaMostrar = horaMostrar + String.valueOf(ih) + ":";
        if (im <= 9) {
            horaMostrar = horaMostrar + "0";
        }
        horaMostrar = horaMostrar + String.valueOf(im);
        atividadeusuario.getAtividade().setHorario(horaMostrar);
        //META
        if (atividadeusuario.getAtividade().getMetatempo() != null) {
            LocalTime meta = atividadeusuario.getAtividade().getMetatempo();
            String metaMostrar = "";
            ih = meta.getHour();
            im = meta.getMinute();
            if (ih <= 9) {
                metaMostrar = "0";
            }
            metaMostrar = metaMostrar + String.valueOf(ih) + ":";
            if (im <= 9) {
                metaMostrar = metaMostrar + "0";
            }
            metaMostrar = metaMostrar + String.valueOf(im);
            atividadeusuario.getAtividade().setMeta(metaMostrar);
        }
    }

    public void gerarDadosDefaut() {
        cliente = clienteRepository.find(1);
        nomeCliente = cliente.getNomefantasia();
        atividadeusuario.getAtividade().setDataexecutar(new Date());
        gerarListaDepartamento();
        if (listaClientedepartamentos != null) {
            int idDepartamento = usuarioLogadoMB.getUsuario().getSubdepartamento().getDepartamento().getIddepartamento();
            for (int i = 0; i < listaClientedepartamentos.size(); i++) {
                if (idDepartamento == listaClientedepartamentos.get(i).getDepartamento().getIddepartamento()) {
                    clientedepartamento = listaClientedepartamentos.get(i);
                    gerarListaSubDepartamento();
                    atividadeusuario.getAtividade().setSubdepartamento(usuarioLogadoMB.getUsuario().getSubdepartamento());
                    i = 10000;
                }
            }
        }
    }
    
    
    public void salvarHorario(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    
    public String retornoValor(){
        return "";
    }
}
