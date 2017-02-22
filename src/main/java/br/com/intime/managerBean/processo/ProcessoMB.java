package br.com.intime.managerBean.processo;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Processo;
import br.com.intime.model.Processorotina;
import br.com.intime.repository.ProcessoRepository;
import br.com.intime.repository.ProcessoRotinaRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.util.ArrayList;
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

@Named
@ViewScoped
public class ProcessoMB implements Serializable {

    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    @EJB
    private ProcessoRepository processoRepository;
    @EJB
    private ProcessoRotinaRepository processoRotinaRepository;
    private List<Processo> listaProcesso;
    private Processo processo;
    private List<Processorotina> listaProcessorotina;
    private String descricao="";

    @PostConstruct
    public void init() {
        gerarListaProcesso();
    }

    public List<Processo> getListaProcesso() {
        return listaProcesso;
    }

    public void setListaProcesso(List<Processo> listaProcesso) {
        this.listaProcesso = listaProcesso;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Processorotina> getListaProcessorotina() {
        return listaProcessorotina;
    }

    public void setListaProcessorotina(List<Processorotina> listaProcessorotina) {
        this.listaProcessorotina = listaProcessorotina;
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public ProcessoRepository getProcessoRepository() {
        return processoRepository;
    }

    public void setProcessoRepository(ProcessoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }

    public ProcessoRotinaRepository getProcessoRotinaRepository() {
        return processoRotinaRepository;
    }

    public void setProcessoRotinaRepository(ProcessoRotinaRepository processoRotinaRepository) {
        this.processoRotinaRepository = processoRotinaRepository;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void cadastrarProcesso() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500); 
        RequestContext.getCurrentInstance().openDialog("cadProcesso", options, null);
    }

    public void cadastrarProcessoRotina(Processo processo) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 450);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("processo", processo);
        RequestContext.getCurrentInstance().openDialog("cadProcessoRotina", options, null);
    }

    public void editarProcessoRotina(Processorotina processorotina) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 450);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("processorotina", processorotina);
        session.setAttribute("processo", processorotina.getProcesso());
        RequestContext.getCurrentInstance().openDialog("cadProcessoRotina", options, null);
    }
    
    
    public void excluirProcessoRotina(Processorotina processorotina) {
        processoRotinaRepository.remove(processorotina.getIdprocessorotina());
        gerarListaProcessoRotina();
        Mensagem.lancarMensagemInfo("Excluído com sucesso.", "");
    }
   
    public void gerarListaProcesso() {
        descricao="";
        listaProcesso = processoRepository.list("Select p from Processo p order by p.descricao");
        if (listaProcesso == null) {
            listaProcesso = new ArrayList<Processo>();
        }
    }
    
    public void gerarListaProcessoRotina() {
        if(processo!=null && processo.getIdprocesso()!=null){
            listaProcessorotina = processoRotinaRepository.list("Select p from Processorotina p where p.processo.idprocesso="
                    +processo.getIdprocesso()+" order by p.descricao");
            if (listaProcessorotina == null) {
                listaProcessorotina = new ArrayList<Processorotina>();
            }
        }
    }
    
    public void iniciarSituacaoProcesso(Processo processo) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 700);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("processo", processo);
        RequestContext.getCurrentInstance().openDialog("cadProcessoSituacao", options, null);
    }
    
    public void visualizarProcessosIniciados(Processo processo) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 700);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("processo", processo);
        RequestContext.getCurrentInstance().openDialog("visualizarProcessosIniciados", options, null);
    }
    
    public void pesquisarProcesso() {
        listaProcesso = processoRepository.list("Select p from Processo where p.descricao like '%"+
                descricao+"%' p order by p.descricao");
        if (listaProcesso == null) {
            listaProcesso = new ArrayList<Processo>();
        }
    }
}
