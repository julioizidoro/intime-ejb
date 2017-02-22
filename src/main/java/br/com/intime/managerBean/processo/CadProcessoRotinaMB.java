package br.com.intime.managerBean.processo;
  
import br.com.intime.model.Processo;
import br.com.intime.model.Processorotina; 
import br.com.intime.repository.ProcessoRotinaRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable; 
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class CadProcessoRotinaMB implements Serializable{
     
    @EJB
    private ProcessoRotinaRepository processoRotinaRepository;
    private Processorotina processorotina;
    private Processo processo;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        processorotina = (Processorotina) session.getAttribute("processorotina");
        session.removeAttribute("processorotina");
        processo = (Processo) session.getAttribute("processo");
        session.removeAttribute("processo");
        if(processorotina==null){
            processorotina = new Processorotina();
        }
    }

    public Processorotina getProcessorotina() {
        return processorotina;
    }

    public void setProcessorotina(Processorotina processorotina) {
        this.processorotina = processorotina;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public ProcessoRotinaRepository getProcessoRotinaRepository() {
        return processoRotinaRepository;
    }

    public void setProcessoRotinaRepository(ProcessoRotinaRepository processoRotinaRepository) {
        this.processoRotinaRepository = processoRotinaRepository;
    }
 
    public void fechar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void salvar(){
        if(processorotina.getDescricao()!=null && processorotina.getDescricao().length()>0){
            if(processorotina.getDiasuteis()!=null && processorotina.getDiasuteis()>0){
                processorotina.setProcesso(processo);
                processorotina = processoRotinaRepository.update(processorotina);
                Mensagem.lancarMensagemInfo("Salvo com sucesso!", "");
                RequestContext.getCurrentInstance().closeDialog(null);
            }else Mensagem.lancarMensagemErro("Campo Dias úteis não informado.", "");
        }else Mensagem.lancarMensagemErro("Descrição não informada.", "");
    }
}
