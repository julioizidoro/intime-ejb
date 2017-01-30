package br.com.intime.managerBean.dashboard;
 
import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeaguardando;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.repository.AtividadeAguardandoRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.util.Formatacao;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct; 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped; 
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
  
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel; 
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
@ManagedBean
@ViewScoped
public class CalendarioMB implements Serializable {
 
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private ScheduleModel eventModel;
    private ScheduleModel lazyEventModel;
    private ScheduleEvent event = new DefaultScheduleEvent();
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeAguardandoRepository atividadeAguardandoRepository;
    private List<Atividadeusuario> listaAtividade;
 
    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();    
        gerarListaAtividades();
        listaAtividade = new ArrayList<Atividadeusuario>();
    }

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public List<Atividadeusuario> getListaAtividade() {
        return listaAtividade;
    }

    public void setListaAtividade(List<Atividadeusuario> listaAtividade) {
        this.listaAtividade = listaAtividade;
    }

    public AtividadeAguardandoRepository getAtividadeAguardandoRepository() {
        return atividadeAguardandoRepository;
    }

    public void setAtividadeAguardandoRepository(AtividadeAguardandoRepository atividadeAguardandoRepository) {
        this.atividadeAguardandoRepository = atividadeAguardandoRepository;
    }
     
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)));    //set random day of month
         
        return date.getTime();
    }
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);  
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return eventModel;
    }
     
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
  
    public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
     
    
    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);
         
        event = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        String sql = "SELECT a FROM Atividadeusuario a where a.situacao<>'Concluida' and a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
            + " and a.atividade.dataexecucao>= :dataInicial and a.atividade.dataexecucao<= :dataFinal order by a.atividade.dataexecucao";
        listaAtividade = atividadeUsuarioRepository.list(sql, 
                Formatacao.converterDateParaLocalDate(event.getStartDate()), 
                Formatacao.converterDateParaLocalDate(event.getStartDate()));
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }   
    
    public void gerarListaAtividades(){ 
        LocalDate data = LocalDate.now();
        LocalDate dataInicial = LocalDate.of(data.getYear(), data.getMonth(), 1);
        LocalDate dataFinal = dataInicial.plusDays(30);
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
            + " and a.atividade.dataexecucao>= :dataInicial and a.atividade.dataexecucao<= :dataFinal order by a.atividade.dataexecucao";
        List<Atividadeusuario> listaAtividades = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
        
        String sqlAguardando = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.dataretorno>= :dataInicial"
                + " ORDER BY a.dataretorno ";
        List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sqlAguardando, LocalDate.now(), null);
        if (lista != null) { 
            for (int i = 0; i < lista.size(); i++) {
                listaAtividades.remove(lista.get(i).getAtividadeusuario());
            }
        }
        if(listaAtividades!=null && listaAtividades.size()>0){
            GerarListaAtividadeCalendarioBean gerar = new GerarListaAtividadeCalendarioBean(listaAtividades);
            if(gerar.getLista()!=null){ 
                for (int i = 0; i < gerar.getLista().size(); i++) {
                    Instant instant = gerar.getLista().get(i).getData()
                            .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                    Date dataAtividade = Date.from(instant);
                    eventModel.addEvent(new DefaultScheduleEvent("("+gerar.getLista().get(i).getTotalconcluidas()+" / "
                            +gerar.getLista().get(i).getTotalAtividade()+")",
                            dataAtividade,dataAtividade));
                }
            }  
        }
    }
}