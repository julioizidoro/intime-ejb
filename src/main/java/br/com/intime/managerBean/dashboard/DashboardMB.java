package br.com.intime.managerBean.dashboard;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Feednoticia;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Nota;
import br.com.intime.model.Notificacao;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.FeedNoticiaRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.repository.NotaRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.util.Formatacao;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
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
    private Nota nota;
    private Ftpdados ftpdados;
    private Feednoticia feednoticia;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
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

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
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

    public Feednoticia getFeednoticia() {
        return feednoticia;
    }

    public void setFeednoticia(Feednoticia feednoticia) {
        this.feednoticia = feednoticia;
    }

    public FeedNoticiaRepository getFeedNoticiaRepository() {
        return feedNoticiaRepository;
    }

    public void setFeedNoticiaRepository(FeedNoticiaRepository feedNoticiaRepository) {
        this.feedNoticiaRepository = feedNoticiaRepository;
    }

    public void gerarListaAtivadadesSemana() {
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = LocalDate.now().plusDays(7);
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao='Concluida' and a.atividade.dataexecucao>= :dataInicial "
                + " and a.atividade.dataexecucao<= :dataFinal "
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
        listaFeedNoticia = feedNoticiaRepository.list(sql, LocalDate.now(),LocalDate.now());
    }
  
    public void gerarListaNotificacoes() {
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
        nota = new Nota();
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 350);
        RequestContext.getCurrentInstance().openDialog("cadNotas", options, null);
    }

    public void salvarNota() {
        if (listaNotas.size() == 6 && nota.getIdnota() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Você atingiu o limite maxímo de notas.");
        } else {
            nota.setUsuario(usuarioLogadoMB.getUsuario());
            notaRepository.update(nota);
            gerarListaNotas();
            RequestContext.getCurrentInstance().closeDialog(null);
        }
    }

    public void editar(Nota nota) {
        this.nota = nota;
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
        if (atividadeusuario.getSituacao().equalsIgnoreCase("Concluida")) {
            LocalTime hora = LocalTime.of(23 , 59 ,00);
            atividadeusuario.setHoraconclusao(hora);
            atividadeusuario.setDataconclusao(LocalDate.now());
            atividadeusuario.setConcluido(true);  
            gerarListaAtivadadesHoje();
        }
        atividadeUsuarioRepository.update(atividadeusuario);
    }
    
    public boolean mostrarBotaoPlay(String situacao){
        if(situacao.equalsIgnoreCase("Play")){
            return false;
        }else return true;
    }
    
    public boolean mostrarBotaoPause(String situacao){
        if(situacao.equalsIgnoreCase("Pause")){
            return false;
        }else return true;
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
        feednoticia = new Feednoticia();
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 350);
        RequestContext.getCurrentInstance().openDialog("cadFeedNoticia", options, null);
    }

    public void salvarFeedNoticia() {
        feednoticia.setUsuario(usuarioLogadoMB.getUsuario());
        feednoticia.setData(LocalDate.now());
        feedNoticiaRepository.update(feednoticia);
        gerarListaFeed();
        RequestContext.getCurrentInstance().closeDialog(null);
    }

}
