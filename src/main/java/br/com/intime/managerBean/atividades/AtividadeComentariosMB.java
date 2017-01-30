package br.com.intime.managerBean.atividades;

import br.com.intime.model.Atividadecomentario;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.AtvidadeAtrasoRepository;
import br.com.intime.repository.AtvidadeComentarioRepository;
import br.com.intime.repository.MotivoAtrasoRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession; 

/**
 *
 * @author Kamila
 */
@Named
@ViewScoped
public class AtividadeComentariosMB implements Serializable {

    private Atividadeusuario atividadeusuario;
    private Atividadecomentario atividadecomentario;
    @EJB
    private AtvidadeComentarioRepository atvidadeComentarioRepository;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        session.removeAttribute("atividadeusuario");
        atividadecomentario = new Atividadecomentario();
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    public Atividadecomentario getAtividadecomentario() {
        return atividadecomentario;
    }

    public void setAtividadecomentario(Atividadecomentario atividadecomentario) {
        this.atividadecomentario = atividadecomentario;
    }

    public AtvidadeComentarioRepository getAtvidadeComentarioRepository() {
        return atvidadeComentarioRepository;
    }

    public void setAtvidadeComentarioRepository(AtvidadeComentarioRepository atvidadeComentarioRepository) {
        this.atvidadeComentarioRepository = atvidadeComentarioRepository;
    }

    public void salvar() {
        if(atividadecomentario.getComentario()!=null && atividadecomentario.getComentario().length()>0){
            atividadecomentario.setData(LocalDate.now());
            atividadecomentario.setAtividadeusuario(atividadeusuario);
            atividadecomentario = atvidadeComentarioRepository.update(atividadecomentario); 
            if(atividadeusuario.getAtividadecomentarioList()==null){
                atividadeusuario.setAtividadecomentarioList(new ArrayList<Atividadecomentario>());
            }
            atividadeusuario.getAtividadecomentarioList().add(atividadecomentario);
            Mensagem.lancarMensagemInfo("Comentário salvo!", "");
            atividadecomentario = new Atividadecomentario();
        }else Mensagem.lancarMensagemErro("Atenção!", "Insira um comentário.");
    }
 
    public String retornarData(LocalDate data){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM");
        String dataMostrar = data.format(formatador);
        return dataMostrar;
    }
}
