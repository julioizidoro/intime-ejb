package br.com.intime.managerBean.atividades;
 
import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeaguardando;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Notificacao;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AtividadeAguardandoRepository;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.NotificacoesRepository;
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

    public void visualizarConcluidas() {
        if (naoconcluidas.equalsIgnoreCase("false")) {
            naoconcluidas = "true";
            concluidas = "false";
        } else {
            naoconcluidas = "false";
            concluidas = "true";
        }
        gerarListaAtivadades();
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
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("todos")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#cba135";
            aguardando = "#E0E0E0";
            dataInicial = null;
            dataFinal = null;
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("aguardando")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#E0E0E0";
            aguardando = "#cba135";
            gerarListaAtivadadesAguardando();
        }
    }

    public String adicionarAtividades() {
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
        boolean dataminima = false;
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
            if (dataInicial != null)  {
                if (dataInicial.isEqual(LocalDate.MIN)){
                    dataInicial = LocalDate.now();
                    dataminima = true;
                }
                sqlData = " and a.dataconclusao>= :dataInicial and a.dataconclusao<= :dataFinal ";
            }
            sqlOrderBy = " ORDER BY a.dataconclusao";
        }
        sql = sql + sqlConcluida + sqlData + sqlOrderBy;
        listaAtividade = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
        removerAtivadadesAguardando();
        if ((naoconcluidas.equalsIgnoreCase("true")) && (concluidas.equalsIgnoreCase("false"))) {
            gerarDataHoraMostrarNaoConcluidas();
        } else {
            atividadeConcluida();
        }
        if (dataminima){
            dataInicial = LocalDate.MIN;
        }
    }

    public void gerarListaAtivadadesAguardando() {
        if (buscar == null) {
            buscar = "";
        }
        String sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno>= :dataInicial "
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
                listaAtividade.add(lista.get(i).getAtividadeusuario());
            }
        }
    }
    
    public void removerAtivadadesAguardando() {
        if (buscar == null) {
            buscar = "";
        }
        String sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno>= :dataInicial "
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

    public void mudarSituacaoAtividade(Atividadeusuario atividadeusuario, String situacao) {
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
                gerarListaAtivadades();
                Mensagem.lancarMensagemInfo("Tarefa concluída!", "");
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
        if (situacao.equalsIgnoreCase("Concluida")){
            gerarListaAtivadades();
        }
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
        atividadeUsuarioRepository.remove(atividadeusuario.getIdatividadeusuario());
        gerarListaAtivadades();
        Mensagem.lancarMensagemInfo("Tarefa excluída!", "");
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
            gerarListaAtivadades();
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
        novaAtividade = atividadeRepository.update(novaAtividade);
        Atividadeusuario novaAtividadeUsuario = new Atividadeusuario();
        novaAtividadeUsuario.setAtividade(novaAtividade);
        novaAtividadeUsuario.setConcluido(false);
        novaAtividadeUsuario.setSituacao("Play");
        novaAtividadeUsuario.setTempo("00:00");
        novaAtividadeUsuario.setUsuario(atividadeusuario.getUsuario());
        atividadeUsuarioRepository.update(novaAtividadeUsuario);
        gerarListaAtivadades();
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

}
