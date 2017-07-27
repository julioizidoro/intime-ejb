package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.bean.NotificacaoAtividadeConcluidaBean;
import br.com.intime.managerBean.bean.RotinaAtividadeBean;
import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeaguardando;
import br.com.intime.model.Atividadecomentario;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Notificacao;
import br.com.intime.model.Processoatividade;
import br.com.intime.model.Processoatividadegatilho;
import br.com.intime.model.Processogatilho;
import br.com.intime.model.Rotinaatividade;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AtividadeAguardandoRepository;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.AtvidadeComentarioRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.repository.ProcessoAtividadeGatilhoRepository;
import br.com.intime.repository.ProcessoAtividadeRepository;
import br.com.intime.repository.ProcessoGatilhoRepository;
import br.com.intime.repository.ProcessoSituacaoRepository;
import br.com.intime.repository.RotinaAtividadeRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Formatacao;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
public class AtividadesMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Atividadeusuario> listaAtividade;
    private List<Usuario> listaUsuario;
    private Atividadeusuario atividadeusuario;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeRepository atividadeRepository;
    @EJB
    private AtividadeAguardandoRepository atividadeAguardandoRepository;
    @EJB
    private UsuarioRepository usuarioRepository;
    @EJB
    private NotificacoesRepository notificacoesRepository;
    @EJB
    private AtvidadeComentarioRepository atividadeComentarioRepository;
    @EJB
    private ProcessoAtividadeRepository processoAtividadeRepository;
    @EJB
    private ProcessoGatilhoRepository processoGatilhoRepository;
    @EJB
    private ProcessoAtividadeGatilhoRepository processoAtividadeGatilhoRepository;
    @EJB
    private ProcessoSituacaoRepository processoSituacaoRepository;
    @EJB
    private RotinaAtividadeRepository rotinaAtividadeRepository;
    //btn visualizar concluidas
    private String concluidas = "false";
    private String naoconcluidas = "true";
    //cores botoes
    private String hoje = "#cba135";
    private String amanha = "#E0E0E0";
    private String seteDias = "#E0E0E0";
    private String todos = "#E0E0E0";
    private String aguardando = "#E0E0E0";
    //Data para consultas
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String buscar;
    private String funcao;

    @PostConstruct
    public void init() {
        mudarCoresBotoes("hoje");
    }

    public List<Atividadeusuario> getListaAtividade() {
        return listaAtividade;
    }

    public void setListaAtividade(List<Atividadeusuario> listaAtividade) {
        this.listaAtividade = listaAtividade;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public String getConcluidas() {
        return concluidas;
    }

    public void setConcluidas(String concluidas) {
        this.concluidas = concluidas;
    }

    public String getNaoconcluidas() {
        return naoconcluidas;
    }

    public void setNaoconcluidas(String naoconcluidas) {
        this.naoconcluidas = naoconcluidas;
    }

    public String getHoje() {
        return hoje;
    }

    public void setHoje(String hoje) {
        this.hoje = hoje;
    }

    public String getAmanha() {
        return amanha;
    }

    public void setAmanha(String amanha) {
        this.amanha = amanha;
    }

    public String getSeteDias() {
        return seteDias;
    }

    public void setSeteDias(String seteDias) {
        this.seteDias = seteDias;
    }

    public String getTodos() {
        return todos;
    }

    public void setTodos(String todos) {
        this.todos = todos;
    }

    public String getAguardando() {
        return aguardando;
    }

    public void setAguardando(String aguardando) {
        this.aguardando = aguardando;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public AtividadeAguardandoRepository getAtividadeAguardandoRepository() {
        return atividadeAguardandoRepository;
    }

    public void setAtividadeAguardandoRepository(AtividadeAguardandoRepository atividadeAguardandoRepository) {
        this.atividadeAguardandoRepository = atividadeAguardandoRepository;
    }

    public NotificacoesRepository getNotificacoesRepository() {
        return notificacoesRepository;
    }

    public void setNotificacoesRepository(NotificacoesRepository notificacoesRepository) {
        this.notificacoesRepository = notificacoesRepository;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public AtividadeRepository getAtividadeRepository() {
        return atividadeRepository;
    }

    public void setAtividadeRepository(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public AtvidadeComentarioRepository getAtividadeComentarioRepository() {
        return atividadeComentarioRepository;
    }

    public void setAtividadeComentarioRepository(AtvidadeComentarioRepository atividadeComentarioRepository) {
        this.atividadeComentarioRepository = atividadeComentarioRepository;
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

    public ProcessoSituacaoRepository getProcessoSituacaoRepository() {
        return processoSituacaoRepository;
    }

    public void setProcessoSituacaoRepository(ProcessoSituacaoRepository processoSituacaoRepository) {
        this.processoSituacaoRepository = processoSituacaoRepository;
    }

    public RotinaAtividadeRepository getRotinaAtividadeRepository() {
        return rotinaAtividadeRepository;
    }

    public void setRotinaAtividadeRepository(RotinaAtividadeRepository rotinaAtividadeRepository) {
        this.rotinaAtividadeRepository = rotinaAtividadeRepository;
    }

    public void visualizarConcluidas() {
        if (naoconcluidas.equalsIgnoreCase("false")) {
            naoconcluidas = "true";
            concluidas = "false";
            if (hoje.equalsIgnoreCase("#cba135")) {
                mudarCoresBotoes(funcao);
            } else if (amanha.equalsIgnoreCase("#cba135")) {
                mudarCoresBotoes("amanha");
            } else if (seteDias.equalsIgnoreCase("#cba135")) {
                mudarCoresBotoes("seteDias");
            } else if (todos.equalsIgnoreCase("#cba135")) {
                mudarCoresBotoes("todos");
            }
        } else {
            naoconcluidas = "false";
            concluidas = "true";
            gerarListaAtivadades();
        }

    }

    public void mudarCoresBotoes(String funcao) {
        if (funcao.equalsIgnoreCase("hoje")) {
            hoje = "#cba135";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#E0E0E0";
            aguardando = "#E0E0E0";
            dataInicial = LocalDate.MIN;
            dataFinal = LocalDate.now();
            this.funcao="hoje";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("amanha")) {
            hoje = "#E0E0E0";
            amanha = "#cba135";
            seteDias = "#E0E0E0";
            todos = "#E0E0E0";
            aguardando = "#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = hoje.plusDays(1);
            dataFinal = hoje.plusDays(1);
            this.funcao="amanha";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("seteDias")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#cba135";
            todos = "#E0E0E0";
            aguardando = "#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = LocalDate.now();
            dataFinal = hoje.plusDays(7);
            this.funcao="seteDias";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("todos")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#cba135";
            aguardando = "#E0E0E0";
            dataInicial = null;
            dataFinal = null;
            this.funcao="todos";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("aguardando")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#E0E0E0";
            aguardando = "#cba135";
            this.funcao="aguardando";
            gerarListaAtivadadesAguardando();
        }
    }

    public String adicionarAtividades() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = null;
        session.setAttribute("atividadeusuario", atividadeusuario);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadAtividades", options, null);
        return "";
    }

    public String visualizarComentarios(Atividadeusuario atividadeusuario) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("atividadeusuario", atividadeusuario);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("comentariosAtividades", options, null);
        return "";
    }

    public String editarAtividades(Atividadeusuario atividadeusuario) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("atividadeusuario", atividadeusuario);
        RequestContext.getCurrentInstance().openDialog("cadAtividades", options, null);
        return "";
    }

    public void gerarListaAtivadades() {
        if (buscar == null) {
            buscar = "";
        }
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.atividade.descricao like '%" + buscar + "%' ";
        String sqlConcluida = "";
        String sqlData = "";
        String sqlOrderBy = "";
        if ((naoconcluidas.equalsIgnoreCase("true")) && (concluidas.equalsIgnoreCase("false"))) {
            sqlConcluida = " and a.situacao<>'Concluida' ";
            if (dataInicial != null) {
                sqlData = " and a.atividade.dataexecucao>= :dataInicial and a.atividade.dataexecucao<= :dataFinal ";
            }
            sqlOrderBy = " ORDER BY a.atividade.dataexecucao";
        } else {
            if (dataInicial != null) {
                sqlData = " and a.dataconclusao>= :dataInicial and a.dataconclusao<= :dataFinal ";
            }
            sqlOrderBy = " ORDER BY a.dataconclusao";
        }
        sql = sql + sqlConcluida + sqlData + sqlOrderBy;
        listaAtividade = new ArrayList<>();
        listaAtividade = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
        removerAtivadadesAguardando();
        if ((naoconcluidas.equalsIgnoreCase("true")) && (concluidas.equalsIgnoreCase("false"))) {
            gerarDataHoraMostrarNaoConcluidas();
        } else {
            atividadeConcluida();
        }
    }

    public void gerarListaAtivadadesAguardando() {
        if (buscar == null) {
            buscar = "";
        }
        String sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno> :dataInicial "
                + " and a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista != null) {
            listaAtividade = new ArrayList<Atividadeusuario>();
            for (int i = 0; i < lista.size(); i++) {
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM");
                LocalDate data = lista.get(i).getDataretorno();
                String dataMostrar = data.format(formatador);
                lista.get(i).getAtividadeusuario().setDataRetorno("(Retorno " + dataMostrar + ")");
                if (!lista.get(i).getAtividadeusuario().getSituacao().equalsIgnoreCase("Concluida")) {
                    listaAtividade.add(lista.get(i).getAtividadeusuario());
                }
            }
        }
    }

    public void removerAtivadadesAguardando() {
        if (buscar == null) {
            buscar = "";
        }
        String sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno> :dataInicial "
                + " and a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                listaAtividade.remove(lista.get(i).getAtividadeusuario());
            }
        }
    }

    public void gerarDataHoraMostrarNaoConcluidas() {
        if (listaAtividade != null) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM");
            for (int i = 0; i < listaAtividade.size(); i++) {
                LocalDate data = listaAtividade.get(i).getAtividade().getDataexecucao();
                LocalTime hora = listaAtividade.get(i).getAtividade().getHoraexecucao();
                String dataMostrar = data.format(formatador);
                String horaMostrar = "";
                listaAtividade.get(i).getAtividade().setDataMostrar(dataMostrar);
                if (((hora.getHour() == 23) && (hora.getMinute() == 59))
                        || ((hora.getHour() == 00) && (hora.getMinute() == 00))) {
                    horaMostrar = "";
                } else {
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
                }
                listaAtividade.get(i).getAtividade().setHoraMostrar(horaMostrar);
            }
        }
    }

    public boolean mostrarBotaoPlay(String situacao) {
        if (situacao.equalsIgnoreCase("Play")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean mostrarBotaoPause(String situacao) {
        if (situacao.equalsIgnoreCase("Pause")) {
            return true;
        } else {
            return false;
        }
    }

    public void atividadeConcluida() {
        if (listaAtividade != null) {
            for (int i = 0; listaAtividade.size() > i; i++) {
                if (listaAtividade.get(i).getSituacao().equalsIgnoreCase("Concluida")) {
                    listaAtividade.get(i).setConcluido(true);
                } else {
                    listaAtividade.get(i).setConcluido(false);
                }
            }
        }
    }

    public String mudarSituacaoAtividade(Atividadeusuario atividadeusuario, String situacao) {
        if (situacao.equalsIgnoreCase("Pause")) {
            Long inicio = new Date().getTime();
            atividadeusuario.setInicio(BigInteger.valueOf(inicio));
            atividadeusuario.setSituacao(situacao);
        } else if (situacao.equalsIgnoreCase("Play")) {
            Long termino = new Date().getTime();
            BigInteger valorInicio = atividadeusuario.getInicio();
            Long inicio = valorInicio.longValue();
            Long resultado = termino - inicio;
            resultado = resultado / 1000;
            resultado = resultado / 60;
            int tempo = resultado.intValue();
            int tempoAtual = atividadeusuario.getTempoatual();
            tempo = tempo + tempoAtual;
            atividadeusuario.setTempoatual(tempo);
            String sHora = Formatacao.calcularHorasTotal(tempo);
            atividadeusuario.setTempo(sHora);
            atividadeusuario.setSituacao(situacao);
        } else if (situacao.equalsIgnoreCase("Concluida")) {
            LocalDate data = LocalDate.now();
            if (atividadeusuario.getAtividade().getDataexecucao().equals(data)
                    || atividadeusuario.getAtividade().getDataexecucao().isAfter(data)) {
                LocalTime hora = LocalTime.of(23, 59, 00);
                atividadeusuario.setHoraconclusao(hora);
                atividadeusuario.setDataconclusao(LocalDate.now());
                atividadeusuario.setConcluido(true);
                atividadeusuario.setSituacao("Concluida");
                verificarProcessoGatilho(atividadeusuario);
                atividadeusuario = atividadeUsuarioRepository.update(atividadeusuario);
                if (atividadeusuario.getAtividade().isRotina()) {
                    if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinadiaria() != null) {
                        gerarProximaAtividadeDiaria(atividadeusuario);
                    } else if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinasemanal() != null) {
                        gerarProximaAtividadeSemanal(atividadeusuario);
                    } else if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinamensal() != null) {
                        gerarProximaAtividadeMensal(atividadeusuario);
                    } else if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinaanual() != null) {
                        gerarProximaAtividadeAnual(atividadeusuario);
                    }
                }
                Mensagem.lancarMensagemInfo("Tarefa concluída!", "");
                listaAtividade.remove(atividadeusuario); 
            } else {
                Map<String, Object> options = new HashMap<String, Object>();
                options.put("contentWidth", 425);
                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
                session.setAttribute("atividadeusuario", atividadeusuario);
                RequestContext.getCurrentInstance().openDialog("motivoAtraso", options, null);
            }
        }
        atividadeUsuarioRepository.update(atividadeusuario);
        return "";
    }

    public boolean mostrarTrianguloUrgente(String prioridade) {
        if (prioridade.equalsIgnoreCase("Urgente")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean mostrarTrianguloImportante(String prioridade) {
        if (prioridade.equalsIgnoreCase("Importante")) {
            return true;
        } else {
            return false;
        }
    }

    public void excluir(Atividadeusuario atividadeusuario) {
        excluirAtividadesAguardando(atividadeusuario);
        excluirAtividadesComentario(atividadeusuario);
        atividadeUsuarioRepository.remove(atividadeusuario.getIdatividadeusuario());
        mudarCoresBotoes(funcao);
        Mensagem.lancarMensagemInfo("Tarefa excluída!", "");
    }

    public void excluirAtividadesAguardando(Atividadeusuario atividadeusuario) {
        List<Atividadeaguardando> listaAtividadeAguardando = atividadeAguardandoRepository.list("Select a From Atividadeaguardando a Where "
                + " a.atividadeusuario.idatividadeusuario=" + atividadeusuario.getIdatividadeusuario());
        if (listaAtividadeAguardando == null) {
            listaAtividadeAguardando = new ArrayList<>();
        }
        for (int i = 0; i < listaAtividadeAguardando.size(); i++) {
            atividadeAguardandoRepository.remove(listaAtividadeAguardando.get(i).getIdatividadeaguardando());
        }
    }

    public void excluirAtividadesComentario(Atividadeusuario atividadeusuario) {
        List<Atividadecomentario> listaAtividadeComentario = atividadeComentarioRepository.list("Select a From Atividadecomentario a Where "
                + " a.atividadeusuario.idatividadeusuario=" + atividadeusuario.getIdatividadeusuario());
        if (listaAtividadeComentario == null) {
            listaAtividadeComentario = new ArrayList<>();
        }
        for (int i = 0; i < listaAtividadeComentario.size(); i++) {
            atividadeComentarioRepository.remove(listaAtividadeComentario.get(i).getIdatividadecomentario());
        }
    }
  
    public void gerarListaUsuario() {
        listaUsuario = usuarioRepository.list("select u from Usuario u where u.status=TRUE order by u.nome");
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<Usuario>();
        }
    }

    public void selecionarAtividade(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
        Date dataexecutar = Date.from(atividadeusuario.getAtividade().getDataexecucao().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.atividadeusuario.getAtividade().setDataexecutar(dataexecutar);
        gerarListaUsuario();
    }

    public void salvarAlteracaoData() {
        if (atividadeusuario.getAtividade().getDataexecutar() != null) {
            atividadeusuario.getAtividade().setDataexecucao(
                    Formatacao.converterDateParaLocalDate(atividadeusuario.getAtividade().getDataexecutar()));
            atividadeusuario.setAtividade(
                    atividadeRepository.update(atividadeusuario.getAtividade()));
            atividadeUsuarioRepository.update(atividadeusuario);
            mudarCoresBotoes(funcao);
        }
    }

    public void salvarEncaminharAtividade() {
        atividadeUsuarioRepository.update(atividadeusuario);
        Notificacao notificacao = new Notificacao();
        notificacao.setLido(false);
        notificacao.setUsuario(atividadeusuario.getUsuario());
        notificacao.setDescricao(usuarioLogadoMB.getUsuario().getNome() + " lhe encaminhou a tarefa '"
                + atividadeusuario.getAtividade().getDescricao() + "'.");
        notificacoesRepository.update(notificacao);
        gerarListaAtivadades();
        Mensagem.lancarMensagemInfo("Tarefa encaminhada para " + atividadeusuario.getUsuario().getNome() + ".", "");
    }

    public int retornarNumComentarios(Atividadeusuario atividadeusuario) {
        if (atividadeusuario.getAtividadecomentarioList() != null
                && atividadeusuario.getAtividadecomentarioList().size() > 0) {
            return atividadeusuario.getAtividadecomentarioList().size();
        } else {
            return 0;
        }
    }

    public boolean mostrarComentarios(Atividadeusuario atividadeusuario) {
        if (atividadeusuario.getAtividadecomentarioList() == null || atividadeusuario.getAtividadecomentarioList().size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public String retornarCorAtividades(Atividadeusuario atividadeusuario) {
        LocalDate data = LocalDate.now();
        if (((atividadeusuario.getAtividade().getDataexecucao().equals(data)
                && atividadeusuario.getAtividade().getHoraexecucao().isBefore(LocalTime.now()))
                || (atividadeusuario.getAtividade().getDataexecucao().isBefore(data)))
                && !atividadeusuario.getSituacao().equalsIgnoreCase("Concluida")) {
            return "#B22222;";
        }
        if (atividadeusuario.getSituacao().equalsIgnoreCase("Concluida")) {
            return "#9C9C9C;text-decoration: line-through !important;";
        } else {
            return "#4F4F4F;";
        }
    }

    public void duplicarAtividade(Atividadeusuario atividadeusuario) {
        Atividade novaAtividade = new Atividade();
        novaAtividade.setCliente(atividadeusuario.getAtividade().getCliente());
        novaAtividade.setDataexecucao(atividadeusuario.getAtividade().getDataexecucao());
        novaAtividade.setDatalancamento(atividadeusuario.getAtividade().getDatalancamento());
        novaAtividade.setDescricao(atividadeusuario.getAtividade().getDescricao());
        novaAtividade.setHoraexecucao(atividadeusuario.getAtividade().getHoraexecucao());
        novaAtividade.setMetatempo(atividadeusuario.getAtividade().getMetatempo());
        novaAtividade.setNotificacaohorario(atividadeusuario.getAtividade().isNotificacaohorario());
        novaAtividade.setPrioridade(atividadeusuario.getAtividade().getPrioridade());
        novaAtividade.setRotina(atividadeusuario.getAtividade().isRotina());
        novaAtividade.setSubdepartamento(atividadeusuario.getAtividade().getSubdepartamento());
        novaAtividade.setUsuario(atividadeusuario.getUsuario());
        novaAtividade = atividadeRepository.update(novaAtividade);
        Atividadeusuario novaAtividadeUsuario = new Atividadeusuario();
        novaAtividadeUsuario.setAtividade(novaAtividade);
        novaAtividadeUsuario.setConcluido(false);
        novaAtividadeUsuario.setSituacao("Play");
        novaAtividadeUsuario.setTempo("00:00");
        novaAtividadeUsuario.setUsuario(atividadeusuario.getUsuario());
        atividadeUsuarioRepository.update(novaAtividadeUsuario);
        mudarCoresBotoes(funcao);
        Mensagem.lancarMensagemInfo("Tarefa duplicada!", "");
    }

    public void adicionarAtividadeAguardando(Atividadeusuario atividadeusuario) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 425);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("atividadeusuario", atividadeusuario);
        RequestContext.getCurrentInstance().openDialog("atividadeAguardando", options, null);
    }

    public void gerarNotificacaoConcluidas() {
        new Thread() {
            @Override
            public void run() {
                List<Usuario> lista = usuarioRepository.list("SELECT u FROM Usuario u where u.status=1");
                NotificacaoAtividadeConcluidaBean notificacaoAtividadeConcluidaBean = new NotificacaoAtividadeConcluidaBean(atividadeusuario, lista);
                List<Usuario> listaSelecionado = notificacaoAtividadeConcluidaBean.getListaSelecionado();
                for (int i = 0; i < listaSelecionado.size(); i++) {
                    Notificacao notificacao = new Notificacao();
                    notificacao.setLido(false);
                    notificacao.setUsuario(listaSelecionado.get(i));
                    String descricao = atividadeusuario.getAtividade().getDescricao() + " concluída em " + Formatacao.ConvercaoDataPadrao(new Date())
                            + " por " + atividadeusuario.getUsuario().getNome() + ".";
                    notificacao.setDescricao(descricao);
                    notificacoesRepository.update(notificacao);
                }
            }
        }.start();

    }

    public String retornarNumeroHoje() {
        dataInicial = LocalDate.MIN;
        dataFinal = LocalDate.now();
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao<>'Concluida' and a.atividade.dataexecucao>= :dataInicial "
                + " and a.atividade.dataexecucao<= :dataFinal";
        List<Atividadeusuario> lista = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
        sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno> :dataInicial "
                + " and a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> listaAguardando = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista != null && lista.size() > 0) {
            if (listaAguardando != null && listaAguardando.size() > 0) {
                for (int i = 0; i < listaAguardando.size(); i++) {
                    lista.remove(listaAguardando.get(i).getAtividadeusuario());
                }
            }
            return "(" + lista.size() + ")";
        }
        return "(0)";
    }

    public String retornarNumeroAmanha() {
        LocalDate hoje = LocalDate.now();
        dataInicial = hoje.plusDays(1);
        dataFinal = hoje.plusDays(1);
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao<>'Concluida' and a.atividade.dataexecucao>= :dataInicial "
                + " and a.atividade.dataexecucao<= :dataFinal";
        List<Atividadeusuario> lista = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
        sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno> :dataInicial "
                + " and a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> listaAguardando = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista != null && lista.size() > 0) {
            if (listaAguardando != null && listaAguardando.size() > 0) {
                for (int i = 0; i < listaAguardando.size(); i++) {
                    lista.remove(listaAguardando.get(i).getAtividadeusuario());
                }
            }
            return "(" + lista.size() + ")";
        }
        return "(0)";
    }

    public String retornarNumeroProx7() {
        LocalDate hoje = LocalDate.now();
        dataInicial = LocalDate.now();
        dataFinal = hoje.plusDays(7);
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao<>'Concluida' and a.atividade.dataexecucao>= :dataInicial "
                + " and a.atividade.dataexecucao<= :dataFinal";
        List<Atividadeusuario> lista = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
        sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno> :dataInicial "
                + " and a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> listaAguardando = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista != null && lista.size() > 0) {
            if (listaAguardando != null && listaAguardando.size() > 0) {
                for (int i = 0; i < listaAguardando.size(); i++) {
                    lista.remove(listaAguardando.get(i).getAtividadeusuario());
                }
            }
            return "(" + lista.size() + ")";
        }
        return "(0)";
    }

    public String retornarNumeroTodos() {
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao<>'Concluida'";
        List<Atividadeusuario> lista = atividadeUsuarioRepository.list(sql, null, null);
        sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno> :dataInicial "
                + " and a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> listaAguardando = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista != null && lista.size() > 0) {
            if (listaAguardando != null && listaAguardando.size() > 0) {
                for (int i = 0; i < listaAguardando.size(); i++) {
                    lista.remove(listaAguardando.get(i).getAtividadeusuario());
                }
            }
            return "(" + lista.size() + ")";
        }
        return "(0)";
    }

    public String retornarNumeroAguardando() {
        dataInicial = LocalDate.now();
        String sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.atividadeusuario.situacao<>'Concluida' and a.dataretorno> :dataInicial "
                + " and a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista != null && lista.size() > 0) {
            return "(" + lista.size() + ")";
        }
        return "(0)";
    }

    public void verificarProcessoGatilho(Atividadeusuario atividadeusuario) {
        Processoatividade processoatividade = processoAtividadeRepository.find("select p from Processoatividade p"
                + " where p.atividadeusuario.idatividadeusuario=" + atividadeusuario.getIdatividadeusuario());
        if (processoatividade != null) {
            Processoatividadegatilho processoatividadegatilho = processoAtividadeGatilhoRepository
                    .find("select p from Processoatividadegatilho p where p.processoatividade.idprocessoatividade="
                            + processoatividade.getIdprocessoatividade());
            if (processoatividadegatilho != null) {
                processoatividadegatilho.getProcessogatilho().setExecutado(true);
                processoatividadegatilho.setProcessogatilho(processoGatilhoRepository.update(processoatividadegatilho.getProcessogatilho()));
                List<Processogatilho> listaGatilho = processoGatilhoRepository
                        .list("select p from Processogatilho p where p.executado=false"
                                + " and p.atividadeprecedente=" + processoatividadegatilho.getProcessogatilho().getNumeroatividade()
                                + " and p.processosituacao.idprocessosituacao=" + processoatividadegatilho.getProcessogatilho().getProcessosituacao().getIdprocessosituacao());
                if (listaGatilho != null && listaGatilho.size() > 0) {
                    salvarAtividadeGatilho(listaGatilho, atividadeusuario);
                } else {
                    List<Processogatilho> listaTodosGatilho = processoGatilhoRepository
                        .list("select p from Processogatilho p where p.executado=false"
                                + " and p.processosituacao.idprocessosituacao=" + processoatividadegatilho.getProcessogatilho().getProcessosituacao().getIdprocessosituacao());
                    if(listaTodosGatilho==null || listaTodosGatilho.size()==0){
                        processoatividadegatilho.getProcessoatividade().getProcessosituacao().setSituacao("Concluído");
                        processoatividadegatilho.getProcessoatividade().getProcessosituacao().setDatatermino(new Date());
                        processoatividadegatilho.getProcessoatividade().setProcessosituacao(
                                processoSituacaoRepository.update(processoatividadegatilho.getProcessoatividade().getProcessosituacao()));
                    }
                }
            } else {
                verificarProcessoRegular(processoatividade);
            }
        }
    }
  
    public void salvarAtividadeGatilho(List<Processogatilho> listaGatilho, Atividadeusuario atividadeusuario) {
        for (int i = 0; i < listaGatilho.size(); i++) {
            Atividade atividade = new Atividade();
            atividade.setCliente(atividadeusuario.getAtividade().getCliente());
            atividade.setDataexecucao(LocalDate.now());
            atividade.setDatalancamento(LocalDate.now());
            LocalTime hora = LocalTime.of(23, 59);
            atividade.setHoraexecucao(hora);
            atividade.setDescricao(listaGatilho.get(i).getProcessorotina().getDescricao());
            atividade.setNotificacaohorario(false);
            atividade.setPrioridade("Regular");
            atividade.setRotina(false);
            atividade.setSubdepartamento(atividadeusuario.getAtividade().getSubdepartamento());
            atividade.setUsuario(usuarioLogadoMB.getUsuario());
            atividade = atividadeRepository.update(atividade);

            Atividadeusuario atdusuario = new Atividadeusuario();
            atdusuario.setAtividade(atividade);
            atdusuario.setConcluido(false);
            atdusuario.setSituacao("Play");
            atdusuario.setTempo("00:00");
            atdusuario.setUsuario(usuarioLogadoMB.getUsuario());
            atdusuario = atividadeUsuarioRepository.update(atdusuario);

            Processoatividade procAtividade = new Processoatividade();
            procAtividade.setAtividadeusuario(atdusuario);
            procAtividade.setProcessorotina(listaGatilho.get(i).getProcessorotina());
            procAtividade.setProcessosituacao(listaGatilho.get(i).getProcessosituacao());
            procAtividade = processoAtividadeRepository.update(procAtividade);

            Processoatividadegatilho procAtividadegatilho = new Processoatividadegatilho();
            procAtividadegatilho.setProcessoatividade(procAtividade);
            procAtividadegatilho.setProcessogatilho(listaGatilho.get(i));
            processoAtividadeGatilhoRepository.update(procAtividadegatilho);

            Mensagem.lancarMensagemInfo("Nova tarefa do Processo '" + listaGatilho.get(i).getProcessorotina().getProcesso().getDescricao() + "' criada.", "");
        }
    }

    public void verificarProcessoRegular(Processoatividade processoatividade) {
        List<Processoatividade> lista = processoAtividadeRepository.list(
                "select p from Processoatividade p where p.processosituacao.idprocessosituacao="
                + processoatividade.getProcessosituacao().getIdprocessosituacao()
                + " and p.atividadeusuario.situacao<>'Concluida'"
                + " and p.atividadeusuario.idatividadeusuario<>" + processoatividade.getAtividadeusuario().getIdatividadeusuario());
        if (lista == null || lista.size() == 0) {
            processoatividade.getProcessosituacao().setSituacao("Concluído");
            processoatividade.getProcessosituacao().setDatatermino(new Date());
            processoatividade.setProcessosituacao(
                    processoSituacaoRepository.update(processoatividade.getProcessosituacao()));
        }
    }

    public void gerarProximaAtividadeDiaria(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeDiaria(atividadeusuario, 7);
            atividade = atividadeRepository.update(atividade); 
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
            mudarCoresBotoes(funcao);
        }
    }
    
    public void gerarProximaAtividadeSemanal(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeSemana(atividadeusuario, 4);
            atividade = atividadeRepository.update(atividade); 
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
            mudarCoresBotoes(funcao);
        }
    }
    
    public void gerarProximaAtividadeMensal(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeMensal(atividadeusuario, 2);
            atividade = atividadeRepository.update(atividade); 
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
            mudarCoresBotoes(funcao);
        }
    }
    
    public void gerarProximaAtividadeAnual(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeAnual(atividadeusuario);
            atividade = atividadeRepository.update(atividade); 
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
            mudarCoresBotoes(funcao);
        }
    }
    
    
     public boolean retornarSituacaoAtrasado(Atividadeusuario atividadeusuario) {
        LocalDate data = LocalDate.now();
        boolean emdia = false;
        if (((atividadeusuario.getAtividade().getDataexecucao().equals(data)
                && atividadeusuario.getAtividade().getHoraexecucao().isBefore(LocalTime.now()))
                || (atividadeusuario.getAtividade().getDataexecucao().isBefore(data)))
                && !atividadeusuario.getSituacao().equalsIgnoreCase("Concluida")) {
            emdia = true;
        }
        return emdia;
    }
     
     
     public boolean retornarSituacaoEmDia(Atividadeusuario atividadeusuario) {
        LocalDate data = LocalDate.now();
        boolean emdia = true;
        if (((atividadeusuario.getAtividade().getDataexecucao().equals(data)
                && atividadeusuario.getAtividade().getHoraexecucao().isBefore(LocalTime.now()))
                || (atividadeusuario.getAtividade().getDataexecucao().isBefore(data)))
                && !atividadeusuario.getSituacao().equalsIgnoreCase("Concluida")) {
            emdia = false;
        }
        return emdia;
    }
}
