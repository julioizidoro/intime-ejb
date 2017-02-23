package br.com.intime.managerBean.processo;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB; 
import br.com.intime.model.Processo; 
import br.com.intime.model.Processosituacao; 
import br.com.intime.repository.ClienteRepository; 
import br.com.intime.repository.ProcessoSituacaoRepository; 
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List;
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
public class ConsProcessoSituacaoMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB; 
    @EJB
    private ProcessoSituacaoRepository processoSituacaoRepository;
    private Processo processo;
    private List<Processosituacao> listaProcessoSituacao;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        processo = (Processo) session.getAttribute("processo");
        session.removeAttribute("processo");
        if (processo != null) {
            gerarListaProcessoSituacao();
        }
    }

    public ProcessoSituacaoRepository getProcessoSituacaoRepository() {
        return processoSituacaoRepository;
    }

    public void setProcessoSituacaoRepository(ProcessoSituacaoRepository processoSituacaoRepository) {
        this.processoSituacaoRepository = processoSituacaoRepository;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    } 

    public List<Processosituacao> getListaProcessoSituacao() {
        return listaProcessoSituacao;
    }

    public void setListaProcessoSituacao(List<Processosituacao> listaProcessoSituacao) {
        this.listaProcessoSituacao = listaProcessoSituacao;
    }
    
    public void fechar() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void gerarListaProcessoSituacao(){
        listaProcessoSituacao = processoSituacaoRepository.list("select p from Processosituacao p where"
                + " p.processo.idprocesso="+processo.getIdprocesso());
        if(listaProcessoSituacao==null){
            listaProcessoSituacao = new ArrayList<Processosituacao>();
        }
    }
}
