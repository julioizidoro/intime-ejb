package br.com.intime.managerBean.dashboard;
 
import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Nota;
import br.com.intime.repository.NotaRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.util.List;
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
public class NotaMB implements Serializable{ 
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Nota nota;
    private List<Nota> listaNotas;
    @EJB
    private NotaRepository notaRepository;
    
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        nota = (Nota) session.getAttribute("nota");
        listaNotas = (List<Nota>) session.getAttribute("listaNotas");
        session.removeAttribute("nota");
        session.removeAttribute("listaNotas");
        if(nota==null){
            nota = new Nota();
        }
    }
    
    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
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

    public void salvarNota() {
        if (listaNotas.size() == 6 && nota.getIdnota() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Você atingiu o limite maxímo de notas.");
        } else {
            nota.setUsuario(usuarioLogadoMB.getUsuario());
            notaRepository.update(nota); 
            RequestContext.getCurrentInstance().closeDialog(null);
        }
    }
    
}
