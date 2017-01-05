package br.com.intime.managerBean.atividades;

import br.com.intime.model.Atividadeaguardando;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Motivoatraso;
import br.com.intime.model.Notificacao;
import br.com.intime.repository.AtividadeAguardandoRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.AtvidadeAtrasoRepository;
import br.com.intime.repository.MotivoAtrasoRepository;
import br.com.intime.repository.NotificacoesRepository;
import br.com.intime.util.Formatacao;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class AtividadeAguardandoMB implements Serializable {

    private Atividadeusuario atividadeusuario;
    private Motivoatraso motivoatraso;
    private Atividadeaguardando atividadeaguardando;
    private List<Motivoatraso> listaMotivoAtraso;
    @EJB
    private MotivoAtrasoRepository motivoAtrasoRepository;
    @EJB
    private AtividadeAguardandoRepository atividadeAguardandoRepository;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
     @EJB
    private NotificacoesRepository notificacoesRepository;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        session.removeAttribute("atividadeusuario");
        atividadeaguardando = new Atividadeaguardando();
        gerarListaMotivo();
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    public Motivoatraso getMotivoatraso() {
        return motivoatraso;
    }

    public void setMotivoatraso(Motivoatraso motivoatraso) {
        this.motivoatraso = motivoatraso;
    }

    public List<Motivoatraso> getListaMotivoAtraso() {
        return listaMotivoAtraso;
    }

    public void setListaMotivoAtraso(List<Motivoatraso> listaMotivoAtraso) {
        this.listaMotivoAtraso = listaMotivoAtraso;
    }

    public MotivoAtrasoRepository getMotivoAtrasoRepository() {
        return motivoAtrasoRepository;
    }

    public void setMotivoAtrasoRepository(MotivoAtrasoRepository motivoAtrasoRepository) {
        this.motivoAtrasoRepository = motivoAtrasoRepository;
    }

    public Atividadeaguardando getAtividadeaguardando() {
        return atividadeaguardando;
    }

    public void setAtividadeaguardando(Atividadeaguardando atividadeaguardando) {
        this.atividadeaguardando = atividadeaguardando;
    }

    public AtividadeAguardandoRepository getAtividadeAguardandoRepository() {
        return atividadeAguardandoRepository;
    }

    public void setAtividadeAguardandoRepository(AtividadeAguardandoRepository atividadeAguardandoRepository) {
        this.atividadeAguardandoRepository = atividadeAguardandoRepository;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public void salvar() {
        if (motivoatraso != null && motivoatraso.getIdmotivoatraso() != null) {
            atividadeaguardando.setDataretorno(
                    Formatacao.converterDateParaLocalDate(atividadeaguardando.getDataretornar()));
            atividadeaguardando.setMotivoatraso(motivoatraso);
            atividadeaguardando.setAtividadeusuario(atividadeusuario);
            atividadeAguardandoRepository.update(atividadeaguardando);
            Mensagem.lancarMensagemInfo("Salvo com sucesso!", "");
            RequestContext.getCurrentInstance().closeDialog(null);
            Notificacao notificacao = new Notificacao();
            notificacao.setLido(false);
            notificacao.setDescricao(atividadeusuario.getUsuario().getNome() + " inseriu a tarefa " 
                    + atividadeusuario.getAtividade().getDescricao()
                    + " na situação 'aguardando'. Motivo: " + motivoatraso.getDescricao() + " - "
                    + atividadeaguardando.getDescricao());
            notificacao.setUsuario(atividadeusuario.getAtividade().getSubdepartamento().getDepartamento().getUsuario());
            notificacoesRepository.update(notificacao);
        } else {
            Mensagem.lancarMensagemErro("Atenção! Motivo não selecionado.", "");
        }
    }

    public void gerarListaMotivo() {
        listaMotivoAtraso = motivoAtrasoRepository.list("Select m From Motivoatraso m");
        if (listaMotivoAtraso == null) {
            listaMotivoAtraso = new ArrayList<Motivoatraso>();
        }
    }
}
