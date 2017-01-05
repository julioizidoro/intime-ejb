package br.com.intime.managerBean.atividades;

import br.com.intime.model.Atividadeatraso;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Motivoatraso;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.AtvidadeAtrasoRepository;
import br.com.intime.repository.MotivoAtrasoRepository;
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
public class AtividadeAtrasoMB implements Serializable {

    private Atividadeusuario atividadeusuario;
    private Motivoatraso motivoatraso;
    private Atividadeatraso atividadeatraso;
    private List<Motivoatraso> listaMotivoAtraso;
    @EJB
    private MotivoAtrasoRepository motivoAtrasoRepository;
    @EJB
    private AtvidadeAtrasoRepository atvidadeAtrasoRepository;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        atividadeatraso = new Atividadeatraso();
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

    public AtvidadeAtrasoRepository getAtvidadeAtrasoRepository() {
        return atvidadeAtrasoRepository;
    }

    public void setAtvidadeAtrasoRepository(AtvidadeAtrasoRepository atvidadeAtrasoRepository) {
        this.atvidadeAtrasoRepository = atvidadeAtrasoRepository;
    }

    public Atividadeatraso getAtividadeatraso() {
        return atividadeatraso;
    }

    public void setAtividadeatraso(Atividadeatraso atividadeatraso) {
        this.atividadeatraso = atividadeatraso;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public void salvar() {
        if (motivoatraso != null && motivoatraso.getIdmotivoatraso() != null) {
            atividadeatraso.setMotivoatraso(motivoatraso);
            atividadeatraso.setAtividadeusuario(atividadeusuario);
            atvidadeAtrasoRepository.update(atividadeatraso);
            LocalTime hora = LocalTime.of(23, 59, 00);
            atividadeusuario.setHoraconclusao(hora);
            atividadeusuario.setDataconclusao(LocalDate.now());
            atividadeusuario.setConcluido(true);
            atividadeusuario.setSituacao("Concluida");
            atividadeUsuarioRepository.update(atividadeusuario);
            Mensagem.lancarMensagemInfo("Tarefa concluída!", "");
            RequestContext.getCurrentInstance().closeDialog(null);
        } else {
            Mensagem.lancarMensagemErro("Atenção! Motivo não selecionado.", "");
        }
    }

    public void gerarListaMotivo(){
        listaMotivoAtraso = motivoAtrasoRepository.list("Select m From Motivoatraso m");
        if (listaMotivoAtraso == null) {
            listaMotivoAtraso = new ArrayList<Motivoatraso>();
        }
    }
}
