package br.com.intime.managerBean.dashboard;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeaguardando;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Feednoticia;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Nota;
import br.com.intime.model.Notificacao;
import br.com.intime.model.Processoatividade;
import br.com.intime.model.Processoatividadegatilho;
import br.com.intime.model.Processogatilho;
import br.com.intime.repository.AtividadeAguardandoRepository;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.FeedNoticiaRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.repository.NotaRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.repository.ProcessoAtividadeGatilhoRepository;
import br.com.intime.repository.ProcessoAtividadeRepository;
import br.com.intime.repository.ProcessoGatilhoRepository;
import br.com.intime.util.Formatacao;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
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
public class DashboardMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Atividadeusuario> listaAtividadesAtrasadas;
    private List<Atividadeusuario> listaAtividadesHoje;
    private List<Atividadeusuario> listaAtividadesSemana;
    private List<Notificacao> listaNotificacoes;
    private List<Nota> listaNotas;
    private List<Feednoticia> listaFeedNoticia;
    private Ftpdados ftpdados;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeRepository atividadeRepository;
    @EJB
    private NotificacoesRepository notificacoesRepository;
    @EJB
    private NotaRepository notaRepository;
    @EJB
    private FtpDadosRepository ftpRepository;
    @EJB
    private FeedNoticiaRepository feedNoticiaRepository;
    @EJB
    private AtividadeAguardandoRepository atividadeAguardandoRepository;
    @EJB
    private ProcessoAtividadeRepository processoAtividadeRepository;
    @EJB
    private ProcessoGatilhoRepository processoGatilhoRepository;
    @EJB
    private ProcessoAtividadeGatilhoRepository processoAtividadeGatilhoRepository;

    @PostConstruct
    public void init() {
        gerarListas();
        ftpdados = ftpRepository.find(1);
    }

    public List<Atividadeusuario> getListaAtividadesAtrasadas() {
        return listaAtividadesAtrasadas;
    }

    public void setListaAtividadesAtrasadas(List<Atividadeusuario> listaAtividadesAtrasadas) {
        this.listaAtividadesAtrasadas = listaAtividadesAtrasadas;
    }

    public List<Atividadeusuario> getListaAtividadesHoje() {
        return listaAtividadesHoje;
    }

    public void setListaAtividadesHoje(List<Atividadeusuario> listaAtividadesHoje) {
        this.listaAtividadesHoje = listaAtividadesHoje;
    }

    public List<Atividadeusuario> getListaAtividadesSemana() {
        return listaAtividadesSemana;
    }

    public void setListaAtividadesSemana(List<Atividadeusuario> listaAtividadesSemana) {
        this.listaAtividadesSemana = listaAtividadesSemana;
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

    public List<Notificacao> getListaNotificacoes() {
        return listaNotificacoes;
    }

    public void setListaNotificacoes(List<Notificacao> listaNotificacoes) {
        this.listaNotificacoes = listaNotificacoes;
    }

    public NotificacoesRepository getNotificacoesRepository() {
        return notificacoesRepository;
    }

    public void setNotificacoesRepository(NotificacoesRepository notificacoesRepository) {
        this.notificacoesRepository = notificacoesRepository;
    }

    public List<Nota> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<Nota> listaNotas) {
        this.listaNotas = listaNotas;
    }

    public NotaRepository getNotaRepository() {
        return notaRepository;
    }

    public void setNotaRepository(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public Ftpdados getFtpdados() {
        return ftpdados;
    }

    public void setFtpdados(Ftpdados ftpdados) {
        this.ftpdados = ftpdados;
    }

    public void setFtpRepository(FtpDadosRepository ftpRepository) {
        this.ftpRepository = ftpRepository;
    }

    public List<Feednoticia> getListaFeedNoticia() {
        return listaFeedNoticia;
    }

    public void setListaFeedNoticia(List<Feednoticia> listaFeedNoticia) {
        this.listaFeedNoticia = listaFeedNoticia;
    }

    public FeedNoticiaRepository getFeedNoticiaRepository() {
        return feedNoticiaRepository;
    }

    public void setFeedNoticiaRepository(FeedNoticiaRepository feedNoticiaRepository) {
        this.feedNoticiaRepository = feedNoticiaRepository;
    }

    public AtividadeRepository getAtividadeRepository() {
        return atividadeRepository;
    }

    public void setAtividadeRepository(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    public AtividadeAguardandoRepository getAtividadeAguardandoRepository() {
        return atividadeAguardandoRepository;
    }

    public void setAtividadeAguardandoRepository(AtividadeAguardandoRepository atividadeAguardandoRepository) {
        this.atividadeAguardandoRepository = atividadeAguardandoRepository;
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

    public void gerarListaAtivadadesSemana() {
        LocalDate dataInicial = LocalDate.now().minusDays(7);
        LocalDate dataFinal = LocalDate.now();
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao='Concluida' and a.dataconclusao>= :dataInicial "
                + " and a.dataconclusao<= :dataFinal "
                + " ORDER BY a.atividade.dataexecucao";
        listaAtividadesSemana = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
    }

    public void gerarListaAtivadadesAtraso() {
        LocalDate data = LocalDate.now();
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao<>'Concluida' and a.atividade.dataexecucao< :dataInicial "
                + " ORDER BY a.atividade.dataexecucao";
        listaAtividadesAtrasadas = atividadeUsuarioRepository.list(sql, data, null);
        
        String sqlAguardando = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno>= :dataInicial"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sqlAguardando, LocalDate.now(), null);
        if (lista != null) { 
            for (int i = 0; i < lista.size(); i++) {
                listaAtividadesAtrasadas.remove(lista.get(i).getAtividadeusuario());
            }
        }
    }

    public void gerarListaAtivadadesHoje() {
        LocalDate data = LocalDate.now();
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao<>'Concluida' and a.atividade.dataexecucao= :dataInicial "
                + " ORDER BY a.atividade.dataexecucao";
        listaAtividadesHoje = atividadeUsuarioRepository.list(sql, data, null);
        
        String sqlAguardando = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno>= :dataInicial"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sqlAguardando, LocalDate.now(), null);
        if (lista != null) { 
            for (int i = 0; i < lista.size(); i++) {
                listaAtividadesAtrasadas.remove(lista.get(i).getAtividadeusuario());
            }
        }
    }

    public int retornarAtividadesAtrasadas() {
        int numero = 0;
        if (listaAtividadesAtrasadas != null) {
            numero = listaAtividadesAtrasadas.size();
        }
        return numero;
    }

    public int retornarAtividadesHoje() {
        int numero = 0;
        if (listaAtividadesHoje != null) {
            numero = listaAtividadesHoje.size();
        }
        return numero;
    }

    public int retornarAtividadesSemana() {
        int numero = 0;
        if (listaAtividadesSemana != null) {
            numero = listaAtividadesSemana.size();
        }
        return numero;
    }

    public String retornarHora() {
        String horario = Formatacao.formatarHoraString();
        return horario;
    }

    public String retornarDataPorExtenso() {
        Date data = new Date();
        String retorno = Formatacao.diaSemanaEscrito(data);
        retorno = retorno + ", " + Formatacao.getDiaData(data) + " "
                + Formatacao.nomeMes(Formatacao.getMesData(data) + 1) + " " + Formatacao.getAnoData(data);
        return retorno;
    }

    public void gerarListaNotas() {
        String sql = "select n From Nota n where n.usuario.idusuario="
                + usuarioLogadoMB.getUsuario().getIdusuario() + " order by n.idnota DESC";
        listaNotas = notaRepository.list(sql);
    }

    public void gerarListaFeed() {
        LocalDate data = LocalDate.now();
        data = data.minusDays(5);
        String sql = "select f From Feednoticia f where f.data>= :dataInicial"
                + " and f.data<= :dataFinal order by f.idfeednoticia DESC";
        listaFeedNoticia = feedNoticiaRepository.list(sql, data, LocalDate.now());
    }

    public void gerarListaNotificacoes() {
        LocalTime horarioAtual = LocalTime.now();
        LocalTime horarioAnterior;
        LocalTime horario;
        if(horarioAtual.getMinute()!=00){
            horarioAnterior = LocalTime.of(horarioAtual.getHour(), horarioAtual.getMinute() - 1); 
        }else{
            horarioAnterior = LocalTime.of(horarioAtual.getHour(), horarioAtual.getMinute());
        }
        if(horarioAtual.getMinute()!=59){
            horario = LocalTime.of(horarioAtual.getHour(), horarioAtual.getMinute() + 1); 
        }else{
            horario = LocalTime.of(horarioAtual.getHour(), horarioAtual.getMinute());
        } 
        if (listaAtividadesHoje != null && listaAtividadesHoje.size() > 0) {
            for (int i = 0; i < listaAtividadesHoje.size(); i++) {
                if (listaAtividadesHoje.get(i).getAtividade().isNotificacaohorario()) {
                    if ((listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().equals(horarioAnterior)
                            || listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().equals(horarioAtual)
                            || listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().equals(horario))
                            && listaAtividadesHoje.get(i).getSituacao().equalsIgnoreCase("Play")) {
                        Notificacao notificacao = new Notificacao();
                        notificacao.setLido(false);
                        notificacao.setUsuario(usuarioLogadoMB.getUsuario());
                        String horaMostrar = "";
                        int ih = listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().getHour();
                        int im = listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().getMinute();
                        if (ih <= 9) {
                            horaMostrar = "0";
                        }
                        horaMostrar = horaMostrar + String.valueOf(ih) + ":";
                        if (im <= 9) {
                            horaMostrar = horaMostrar + "0";
                        }
                        horaMostrar = horaMostrar + String.valueOf(im);
                        notificacao.setDescricao("Sua tarefa '" + listaAtividadesHoje.get(i).getAtividade().getDescricao()
                                + "' deverá ser realizada as " + horaMostrar + " hrs.");
                        notificacoesRepository.create(notificacao); 
                        listaAtividadesHoje.get(i).getAtividade().setNotificacaohorario(false);
                        atividadeRepository.update(listaAtividadesHoje.get(i).getAtividade());
                    } else if ((listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().isBefore(horarioAnterior)
                            || listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().isBefore(horarioAtual)
                            || listaAtividadesHoje.get(i).getAtividade().getHoraexecucao().isBefore(horario))
                            && listaAtividadesHoje.get(i).getSituacao().equalsIgnoreCase("Play")) {
                        Notificacao notificacao = new Notificacao();
                        notificacao.setLido(false);
                        notificacao.setUsuario(usuarioLogadoMB.getUsuario());
                        notificacao.setDescricao("Atenção! Sua tarefa '" + listaAtividadesHoje.get(i).getAtividade().getDescricao()
                                + "' encontrasse atrasada.");
                        notificacoesRepository.create(notificacao);
                        listaAtividadesHoje.get(i).getAtividade().setNotificacaohorario(false);
                        atividadeRepository.update(listaAtividadesHoje.get(i).getAtividade());
                    }
                }
            }
        }
        String sql = "select n From Notificacao n where n.lido=false and n.usuario.idusuario="
                + usuarioLogadoMB.getUsuario().getIdusuario() + " order by n.descricao";
        listaNotificacoes = notificacoesRepository.list(sql);
    }

    public int retornarNumeroNotificacoes() {
        int numero = 0;
        if (listaNotificacoes != null) {
            numero = listaNotificacoes.size();
        }
        return numero;
    }

    public String visualizarNotificacoes() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("listaNotificacoes", listaNotificacoes);
        RequestContext.getCurrentInstance().openDialog("notificacoes", options, null);
        return "";
    }

    public void adicionarNota() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("listaNotas", listaNotas);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 350);
        RequestContext.getCurrentInstance().openDialog("cadNotas", options, null);
    }

    public void editar(Nota nota) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("nota", nota);
        session.setAttribute("listaNotas", listaNotas);
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 350);
        RequestContext.getCurrentInstance().openDialog("cadNotas", options, null);
    }

    public void excluir(Nota nota) {
        notaRepository.remove(nota.getIdnota());
        gerarListaNotas();
    }

    public void mudarSituacaoAtividade(Atividadeusuario atividadeusuario, String situacao) {
        atividadeusuario.setSituacao(situacao);
        if (situacao.equalsIgnoreCase("Pause")) {
            Long inicio = new Date().getTime();
            atividadeusuario.setInicio(BigInteger.valueOf(inicio));
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
        } else if (situacao.equalsIgnoreCase("Concluida")) {
            LocalTime hora = LocalTime.of(23, 59, 00);
            atividadeusuario.setHoraconclusao(hora);
            atividadeusuario.setDataconclusao(LocalDate.now());
            atividadeusuario.setConcluido(true);
            verificarProcessoGatilho(atividadeusuario);
            gerarListaAtivadadesHoje();
            gerarListaAtivadadesSemana();
        }
        atividadeUsuarioRepository.update(atividadeusuario);
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

    public void gerarListas() {
        gerarListaAtivadadesAtraso();
        gerarListaAtivadadesHoje();
        gerarListaAtivadadesSemana();
        gerarListaNotificacoes();
        gerarListaNotas();
        gerarListaFeed();
    }

    public void adicionarFeedNoticia() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 350);
        RequestContext.getCurrentInstance().openDialog("cadFeedNoticia", options, null);
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
                        .list("select p from Processogatilho p where p.processorotina.idprocessorotina="
                                + processoatividadegatilho.getProcessogatilho().getProcessorotina().getIdprocessorotina()
                                + " and p.processosituacao.idprocessosituacao=" + processoatividadegatilho.getProcessogatilho().getProcessosituacao().getIdprocessosituacao());
                if (listaGatilho != null && listaGatilho.size() > 0) {
                    for (int i = 0; i < listaGatilho.size(); i++) {
                        if (listaGatilho.get(i).getExecutado().equals(false)) {
                            Atividade atividade = new Atividade();
                            atividade.setCliente(atividadeusuario.getAtividade().getCliente());
                            atividade.setDataexecucao(listaGatilho.get(i).getProcessorotina().getData());
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
                            
                            Mensagem.lancarMensagemInfo("Nova tarefa do Processo '"+listaGatilho.get(i).getProcessorotina().getProcesso().getDescricao()+"' criada.", "");
                        }
                    }
                }
            }
        }
    }
}
