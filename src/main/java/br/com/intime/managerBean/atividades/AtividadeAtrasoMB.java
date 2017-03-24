package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.bean.RotinaAtividadeBean;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeatraso;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Motivoatraso;
import br.com.intime.model.Rotinaatividade;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.AtvidadeAtrasoRepository;
import br.com.intime.repository.MotivoAtrasoRepository;
import br.com.intime.repository.ProcessoSituacaoRepository;
import br.com.intime.repository.RotinaAtividadeRepository;
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
    private AtividadeRepository atividadeRepository;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private ProcessoSituacaoRepository processoSituacaoRepository;
    @EJB
    private RotinaAtividadeRepository rotinaAtividadeRepository;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        atividadeusuario = (Atividadeusuario) session.getAttribute("atividadeusuario");
        session.removeAttribute("atividadeusuario");
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

    public AtividadeRepository getAtividadeRepository() {
        return atividadeRepository;
    }

    public void setAtividadeRepository(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    public ProcessoSituacaoRepository getProcessoSituacaoRepository() {
        return processoSituacaoRepository;
    }

    public void setProcessoSituacaoRepository(ProcessoSituacaoRepository processoSituacaoRepository) {
        this.processoSituacaoRepository = processoSituacaoRepository;
    }

    public RotinaAtividadeRepository getRotinaAtividadeRepository() {
        return rotinaAtividadeRepository;
    }

    public void setRotinaAtividadeRepository(RotinaAtividadeRepository rotinaAtividadeRepository) {
        this.rotinaAtividadeRepository = rotinaAtividadeRepository;
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
            if (atividadeusuario.getAtividade().isRotina()) {
                if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinadiaria() != null) {
                    gerarProximaAtividadeDiaria(atividadeusuario);
                } else if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinasemanal() != null) {
                    gerarProximaAtividadeSemanal(atividadeusuario);
                } else if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinamensal() != null) {
                    gerarProximaAtividadeMensal(atividadeusuario);
                } else if (atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getRotinaanual() != null) {
                    gerarProximaAtividadeAnual(atividadeusuario);
                }
            }
            Mensagem.lancarMensagemInfo("Tarefa concluída!", "");
            RequestContext.getCurrentInstance().closeDialog(null);
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
    
     public void gerarProximaAtividadeDiaria(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeDiaria(atividadeusuario, 7);
            atividade = atividadeRepository.update(atividade);
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
        }
    }
    
    public void gerarProximaAtividadeSemanal(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeSemana(atividadeusuario, 4);
            atividade = atividadeRepository.update(atividade);
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
        }
    }
    
    public void gerarProximaAtividadeMensal(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeMensal(atividadeusuario, 2);
            atividade = atividadeRepository.update(atividade);
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
        }
    }
    
    public void gerarProximaAtividadeAnual(Atividadeusuario atividadeusuario) {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        boolean termino = rab.verificarTerminoRotina(atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
        if (!termino) {
            Atividade atividade = rab.gerarProximaAtividadeAnual(atividadeusuario);
            atividade = atividadeRepository.update(atividade);
            atividadeusuario = rab.gearAtivadadeUsuario(atividade, atividadeusuario.getUsuario());
            atividadeUsuarioRepository.update(atividadeusuario);
            Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente());
            rotinaAtividadeRepository.update(rotinaatividade); 
            atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().setTotalrecorrencia(
                    atividadeusuario.getAtividade().getRotinaatividade().getRotinacliente().getTotalrecorrencia() + 1);
        }
    }
}
