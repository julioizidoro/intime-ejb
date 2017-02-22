package br.com.intime.managerBean.processo;
   
import br.com.intime.model.Departamento;
import br.com.intime.model.Processo; 
import br.com.intime.model.Subdepartamento; 
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.repository.ProcessoRepository; 
import br.com.intime.repository.SubDepartamentoRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable; 
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;


@Named
@ViewScoped
public class CadProcessoMB implements Serializable{
     
    @EJB
    private ProcessoRepository processoRepository; 
    @EJB
    private SubDepartamentoRepository subDepartamentoRepository;
    @EJB
    private DepartamentoRepository departamentoRepository;
    private Processo processo;
    private Departamento departamento;
    private List<Departamento> listaDepartamento;
    private List<Subdepartamento> listaSubDepartamento;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false); 
        processo = (Processo) session.getAttribute("processo");
        session.removeAttribute("processo");
        if(processo==null){
            processo = new Processo();
        }
        gerarListaDepartamento();
    }

    public ProcessoRepository getProcessoRepository() {
        return processoRepository;
    }

    public void setProcessoRepository(ProcessoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    } 

    public SubDepartamentoRepository getSubDepartamentoRepository() {
        return subDepartamentoRepository;
    }

    public void setSubDepartamentoRepository(SubDepartamentoRepository subDepartamentoRepository) {
        this.subDepartamentoRepository = subDepartamentoRepository;
    }

    public DepartamentoRepository getDepartamentoRepository() {
        return departamentoRepository;
    }

    public void setDepartamentoRepository(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Departamento> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(List<Departamento> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }
 
    public List<Subdepartamento> getListaSubDepartamento() {
        return listaSubDepartamento;
    }

    public void setListaSubDepartamento(List<Subdepartamento> listaSubDepartamento) {
        this.listaSubDepartamento = listaSubDepartamento;
    }
 
    public void fechar(){
        RequestContext.getCurrentInstance().closeDialog(null);
    }  
    
    public void salvar(){ 
        if(processo.getSubdepartamento()!=null && processo.getSubdepartamento().getIdsubdepartamento()!=null){
            if(processo.getDescricao()!=null && processo.getDescricao().length()>0){
                processo = processoRepository.update(processo);
                Mensagem.lancarMensagemInfo("Salvo com sucesso!", "");
                RequestContext.getCurrentInstance().closeDialog(null);
            }else Mensagem.lancarMensagemErro("Informe uma descrição.", "");
        }else Mensagem.lancarMensagemErro("Selecione um Subdepartamento", "");
    }
    
    public void gerarListaDepartamento() {
        listaDepartamento = departamentoRepository.list("Select c From Departamento c order by c.nome");
    }

    public void gerarListaSubDepartamento() {
        if (departamento != null && departamento.getIddepartamento() != null) {
            listaSubDepartamento = subDepartamentoRepository.list("Select s From Subdepartamento s"
                    + " where s.departamento.iddepartamento=" + departamento.getIddepartamento() + " and s.status=1 order by s.nome");
        }
    }
}
