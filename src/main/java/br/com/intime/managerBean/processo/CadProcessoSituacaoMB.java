package br.com.intime.managerBean.processo;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Cliente;
import br.com.intime.model.Processo;
import br.com.intime.model.Processoatividade;
import br.com.intime.model.Processoatividadegatilho;
import br.com.intime.model.Processogatilho;
import br.com.intime.model.Processorotina;
import br.com.intime.model.Processosituacao;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.ClienteRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.repository.ProcessoAtividadeGatilhoRepository;
import br.com.intime.repository.ProcessoAtividadeRepository;
import br.com.intime.repository.ProcessoGatilhoRepository;
import br.com.intime.repository.ProcessoRotinaRepository;
import br.com.intime.repository.ProcessoSituacaoRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Formatacao;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class CadProcessoSituacaoMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @EJB
    private ClienteRepository clienteRepository;
    @EJB
    private ProcessoSituacaoRepository processoSituacaoRepository;
    @EJB
    private ProcessoRotinaRepository processoRotinaRepository;
    @EJB
    private ProcessoAtividadeRepository processoAtividadeRepository;
    @EJB
    private ProcessoGatilhoRepository processoGatilhoRepository;
    @EJB
    private ProcessoAtividadeGatilhoRepository processoAtividadeGatilhoRepository;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeRepository atividadeRepository;
    @EJB
    private UsuarioRepository usuarioRepository;
    @EJB
    private NotificacoesRepository notificacoesRepository;
    private Processosituacao processosituacao;
    private Processo processo;
    private Cliente cliente;
    private List<Cliente> listaCliente;
    private List<Processorotina> listaProcessoRotina;
    private List<Usuario> listaUsuario;
    private List<String> listaNumeroAtividades;
    private boolean habilitarGatilho=false;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        processo = (Processo) session.getAttribute("processo");
        session.removeAttribute("processo");
        if (processo != null) {  
            processosituacao = new Processosituacao();
            processosituacao.setDatainicio(new Date()); 
            gerarListaProcessoRotina();
            if(processo.getTipoprocesso().equalsIgnoreCase("Gatilho")){
                habilitarGatilho=true;
            }
        }
        gerarListaCliente();
        gerarListaUsuario();
    }

    public ProcessoSituacaoRepository getProcessoSituacaoRepository() {
        return processoSituacaoRepository;
    }

    public void setProcessoSituacaoRepository(ProcessoSituacaoRepository processoSituacaoRepository) {
        this.processoSituacaoRepository = processoSituacaoRepository;
    }

    public Processosituacao getProcessosituacao() {
        return processosituacao;
    }

    public void setProcessosituacao(Processosituacao processosituacao) {
        this.processosituacao = processosituacao;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
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

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public List<Processorotina> getListaProcessoRotina() {
        return listaProcessoRotina;
    }

    public void setListaProcessoRotina(List<Processorotina> listaProcessoRotina) {
        this.listaProcessoRotina = listaProcessoRotina;
    }

    public ProcessoRotinaRepository getProcessoRotinaRepository() {
        return processoRotinaRepository;
    }

    public void setProcessoRotinaRepository(ProcessoRotinaRepository processoRotinaRepository) {
        this.processoRotinaRepository = processoRotinaRepository;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
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

    public ProcessoAtividadeRepository getProcessoAtividadeRepository() {
        return processoAtividadeRepository;
    }

    public void setProcessoAtividadeRepository(ProcessoAtividadeRepository processoAtividadeRepository) {
        this.processoAtividadeRepository = processoAtividadeRepository;
    }

    public ProcessoGatilhoRepository getProcessoGatilhoRepository() {
        return processoGatilhoRepository;
    }

    public void setProcessoGatilhoRepository(ProcessoGatilhoRepository processoGatilhoRepository) {
        this.processoGatilhoRepository = processoGatilhoRepository;
    }

    public ProcessoAtividadeGatilhoRepository getProcessoAtividadeGatilhoRepository() {
        return processoAtividadeGatilhoRepository;
    }

    public void setProcessoAtividadeGatilhoRepository(ProcessoAtividadeGatilhoRepository processoAtividadeGatilhoRepository) {
        this.processoAtividadeGatilhoRepository = processoAtividadeGatilhoRepository;
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

    public List<String> getListaNumeroAtividades() {
        return listaNumeroAtividades;
    }

    public void setListaNumeroAtividades(List<String> listaNumeroAtividades) {
        this.listaNumeroAtividades = listaNumeroAtividades;
    }

    public boolean isHabilitarGatilho() {
        return habilitarGatilho;
    }

    public void setHabilitarGatilho(boolean habilitarGatilho) {
        this.habilitarGatilho = habilitarGatilho;
    }

    public void fechar() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void salvar() {
        if (processosituacao.getDatainicio() != null && cliente != null && cliente.getIdcliente() != null) {
            processosituacao.setProcesso(processo);
            processosituacao.setCliente(cliente);
            processosituacao.setUsuario(usuarioLogadoMB.getUsuario());
            processosituacao.setSituacao("Aberto");
            processosituacao = processoSituacaoRepository.update(processosituacao);

            for (int i = 0; i < listaProcessoRotina.size(); i++) {
                if (listaProcessoRotina.get(i).isSelecionado()) {
                    if (processo.getTipoprocesso().equalsIgnoreCase("Gatilho")) {
                        Processogatilho processogatilho = new Processogatilho();
                        processogatilho.setProcessorotina(listaProcessoRotina.get(i));
                        processogatilho.setProcessosituacao(processosituacao);
                        processogatilho.setExecutado(false);
                        processogatilho.setNumeroatividade(listaProcessoRotina.get(i).getNumeroatividade());
                        processogatilho.setAtividadeprecedente(listaProcessoRotina.get(i).getAtividadeprecedente());
                        processogatilho = processoGatilhoRepository.update(processogatilho);
                            
                        if (i == 0) {
                            Atividade atividade = new Atividade();
                            atividade.setCliente(cliente);
                            atividade.setDataexecucao(
                                    Formatacao.converterDateParaLocalDate(listaProcessoRotina.get(i).getDatamostrar()));
                            atividade.setDatalancamento(LocalDate.now());
                            LocalTime hora = LocalTime.of(23, 59);
                            atividade.setHoraexecucao(hora);
                            atividade.setDescricao(listaProcessoRotina.get(i).getDescricao());
                            atividade.setNotificacaohorario(false);
                            atividade.setPrioridade("Regular");
                            atividade.setRotina(false);
                            atividade.setSubdepartamento(processo.getSubdepartamento());
                            atividade.setUsuario(usuarioLogadoMB.getUsuario());
                            atividade = atividadeRepository.update(atividade);

                            Atividadeusuario atividadeusuario = new Atividadeusuario();
                            atividadeusuario.setAtividade(atividade);
                            atividadeusuario.setConcluido(false);
                            atividadeusuario.setSituacao("Play");
                            atividadeusuario.setTempo("00:00");
                            atividadeusuario.setUsuario(usuarioLogadoMB.getUsuario());
                            atividadeusuario = atividadeUsuarioRepository.update(atividadeusuario);

                            Processoatividade processoatividade = new Processoatividade();
                            processoatividade.setAtividadeusuario(atividadeusuario);
                            processoatividade.setProcessorotina(listaProcessoRotina.get(i));
                            processoatividade.setProcessosituacao(processosituacao);
                            processoatividade = processoAtividadeRepository.update(processoatividade);
                            
                            Processoatividadegatilho processoatividadegatilho = new Processoatividadegatilho();
                            processoatividadegatilho.setProcessoatividade(processoatividade);
                            processoatividadegatilho.setProcessogatilho(processogatilho);
                            processoAtividadeGatilhoRepository.update(processoatividadegatilho);
                        }
                    } else {
                        Atividade atividade = new Atividade();
                        atividade.setCliente(cliente);
                        atividade.setDataexecucao(
                                Formatacao.converterDateParaLocalDate(listaProcessoRotina.get(i).getDatamostrar()));
                        atividade.setDatalancamento(LocalDate.now());
                        LocalTime hora = LocalTime.of(23, 59);
                        atividade.setHoraexecucao(hora);
                        atividade.setDescricao(listaProcessoRotina.get(i).getDescricao());
                        atividade.setNotificacaohorario(false);
                        atividade.setPrioridade("Regular");
                        atividade.setRotina(false);
                        atividade.setSubdepartamento(processo.getSubdepartamento());
                        atividade.setUsuario(usuarioLogadoMB.getUsuario());
                        atividade = atividadeRepository.update(atividade);

                        Atividadeusuario atividadeusuario = new Atividadeusuario();
                        atividadeusuario.setAtividade(atividade);
                        atividadeusuario.setConcluido(false);
                        atividadeusuario.setSituacao("Play");
                        atividadeusuario.setTempo("00:00");
                        atividadeusuario.setUsuario(usuarioLogadoMB.getUsuario());
                        atividadeusuario = atividadeUsuarioRepository.update(atividadeusuario);

                        Processoatividade processoatividade = new Processoatividade();
                        processoatividade.setAtividadeusuario(atividadeusuario);
                        processoatividade.setProcessorotina(listaProcessoRotina.get(i));
                        processoatividade.setProcessosituacao(processosituacao);
                        processoatividade = processoAtividadeRepository.update(processoatividade);
                    }
                }
            }
            Mensagem.lancarMensagemInfo("Salvo com sucesso!", "");
            RequestContext.getCurrentInstance().closeDialog(null);
        } else {
            Mensagem.lancarMensagemErro("Campos obrigatórios não preenchido.", "");
        }
    }

    public void gerarListaCliente() {
        listaCliente = clienteRepository.list("Select c from Cliente c where c.status=1 order by c.nomefantasia");
        if (listaCliente == null) {
            listaCliente = new ArrayList<Cliente>();
        }
    }

    public void gerarListaProcessoRotina() {
        listaProcessoRotina = processoRotinaRepository.list("Select p from Processorotina p where p.processo.idprocesso="
                + processo.getIdprocesso() + " order by p.descricao");
        if (listaProcessoRotina == null) {
            listaProcessoRotina = new ArrayList<Processorotina>();
        }
        listaNumeroAtividades = new ArrayList<String>();
        int numeroAtividade=1;
        for (int i = 0; i < listaProcessoRotina.size(); i++) {
            listaProcessoRotina.get(i).setUsuario(usuarioLogadoMB.getUsuario());
            LocalDate data = LocalDate.now();
            if (listaProcessoRotina.get(i).getDiasuteis() > 0) {
                data = data.plusDays(listaProcessoRotina.get(i).getDiasuteis());
            }
            listaProcessoRotina.get(i).setDatamostrar(Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            listaProcessoRotina.get(i).setData(Formatacao.converterDateParaLocalDate(listaProcessoRotina.get(i).getDatamostrar()));
            listaProcessoRotina.get(i).setNumeroatividade(numeroAtividade);
            listaNumeroAtividades.add(String.valueOf(numeroAtividade));
            numeroAtividade++;
        }
    }

    public void gerarListaUsuario() {
        listaUsuario = usuarioRepository.list("select u from Usuario u where u.status=TRUE order by u.nome");
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<Usuario>();
        }
    }
}
