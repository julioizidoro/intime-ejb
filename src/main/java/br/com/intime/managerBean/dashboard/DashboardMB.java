package br.com.intime.managerBean.dashboard;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Feednoticia;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Nota;
import br.com.intime.model.Notificacao;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.FeedNoticiaRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.repository.NotaRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.util.Formatacao;
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
    }

    public void gerarListaAtivadadesHoje() {
        LocalDate data = LocalDate.now();
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao<>'Concluida' and a.atividade.dataexecucao= :dataInicial "
                + " ORDER BY a.atividade.dataexecucao";
        listaAtividadesHoje = atividadeUsuarioRepository.list(sql, data, null);
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
        String sql = "select f From Feednoticia f where f.data>= :dataInicial"
                + " and f.data<= :dataFinal order by f.idfeednoticia DESC";
        listaFeedNoticia = feedNoticiaRepository.list(sql, LocalDate.now(), LocalDate.now());
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
}
