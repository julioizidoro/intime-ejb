package br.com.intime.managerBean.dashboard;
 
import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Feednoticia;
import br.com.intime.model.Nota;
import br.com.intime.repository.FeedNoticiaRepository;
import br.com.intime.repository.NotaRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.LocalDate;
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
public class FeedNoticiasMB implements Serializable{ 
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private Feednoticia feednoticia; 
    @EJB
    private FeedNoticiaRepository feedNoticiaRepository;
    
    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        feednoticia = (Feednoticia) session.getAttribute("feednoticia"); 
        session.removeAttribute("feednoticia");
        if(feednoticia==null){
            feednoticia = new Feednoticia();
        }
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
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
     

    public void salvarFeedNoticia() {
        feednoticia.setUsuario(usuarioLogadoMB.getUsuario());
        feednoticia.setData(LocalDate.now());
        feedNoticiaRepository.update(feednoticia); 
        RequestContext.getCurrentInstance().closeDialog(null);
    }
     
}
