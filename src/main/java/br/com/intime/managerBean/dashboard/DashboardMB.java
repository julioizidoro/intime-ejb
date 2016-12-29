package br.com.intime.managerBean.dashboard;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Ftpdados;
import br.com.intime.model.Nota;
import br.com.intime.model.Notificacao;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.FtpDadosRepository;
import br.com.intime.repository.NotaRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.util.Formatacao;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named
@SessionScoped
public class DashboardMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Atividadeusuario> listaAtividadesAtrasadas;
    private List<Atividadeusuario> listaAtividadesHoje;
    private List<Atividadeusuario> listaAtividadesSemana;
    private List<Notificacao> listaNotificacoes;
    private List<Nota> listaNotas;
    private Nota nota;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private NotificacoesRepository notificacoesRepository;
    @EJB
    private NotaRepository notaRepository;
    private Ftpdados ftpdados;
    @EJB
    private FtpDadosRepository ftpRepository;

    @PostConstruct
    public void init() {
        gerarListaAtivadadesAtraso();
        gerarListaAtivadadesHoje();
        gerarListaAtivadadesSemana();
        gerarListaNotificacoes();
        gerarListaNotas();
        nota = new Nota();
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

    public FtpDadosRepository getFtpRepository() {
        return ftpRepository;
    }

    public void setFtpRepository(FtpDadosRepository ftpRepository) {
        this.ftpRepository = ftpRepository;
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
        nota.setUsuario(usuarioLogadoMB.getUsuario());
        notaRepository.update(nota);
        gerarListaNotas();
        nota = new Nota(); 
    }

    public void editar(Nota nota) {
        this.nota = nota;
    }

    public void excluir(Nota nota) {
        notaRepository.remove(nota.getIdnota());
        gerarListaNotas();
    }
 
}
