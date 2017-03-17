package br.com.intime.managerBean.rotina;

import br.com.intime.model.Atividadeaguardando;
import br.com.intime.model.Atividadeusuario; 
import br.com.intime.model.Clientedepartamento;
import br.com.intime.model.Rotina;
import br.com.intime.model.Rotinaatividade;
import br.com.intime.model.Rotinacliente;
import br.com.intime.repository.AtividadeAguardandoRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.ClienteDepartamentoRepository;
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.repository.RotinaAtividadeRepository;
import br.com.intime.repository.RotinaClienteRepository;
import br.com.intime.repository.RotinaRepository;
import br.com.intime.repository.SubDepartamentoRepository;
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

@Named
@ViewScoped
public class RotinaAtividadeAtrasadasMB implements Serializable {

    @EJB
    private DepartamentoRepository departamentoRepository;
    @EJB
    private SubDepartamentoRepository subDepartamentoRepository;
    @EJB
    private RotinaRepository rotinaRepository;
    @EJB
    private RotinaClienteRepository rotinaClienteRepository;
    @EJB
    private ClienteDepartamentoRepository clienteDepartamentoRepository;
    @EJB
    private RotinaAtividadeRepository rotinaAtividadeRepository;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeAguardandoRepository atividadeAguardandoRepository;
    private Rotina rotina;
    private Clientedepartamento clientedepartamento;
    private List<Atividadeusuario> listaAtividades;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        rotina = (Rotina) session.getAttribute("rotina");
        clientedepartamento = (Clientedepartamento) session.getAttribute("clientedepartamento");
        session.removeAttribute("clientedepartamento");
        session.removeAttribute("rotina");
        gerarListaAtividades();
    }

    public DepartamentoRepository getDepartamentoRepository() {
        return departamentoRepository;
    }

    public void setDepartamentoRepository(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public SubDepartamentoRepository getSubDepartamentoRepository() {
        return subDepartamentoRepository;
    }

    public void setSubDepartamentoRepository(SubDepartamentoRepository subDepartamentoRepository) {
        this.subDepartamentoRepository = subDepartamentoRepository;
    }

    public RotinaRepository getRotinaRepository() {
        return rotinaRepository;
    }

    public void setRotinaRepository(RotinaRepository rotinaRepository) {
        this.rotinaRepository = rotinaRepository;
    }

    public RotinaClienteRepository getRotinaClienteRepository() {
        return rotinaClienteRepository;
    }

    public void setRotinaClienteRepository(RotinaClienteRepository rotinaClienteRepository) {
        this.rotinaClienteRepository = rotinaClienteRepository;
    }

    public ClienteDepartamentoRepository getClienteDepartamentoRepository() {
        return clienteDepartamentoRepository;
    }

    public void setClienteDepartamentoRepository(ClienteDepartamentoRepository clienteDepartamentoRepository) {
        this.clienteDepartamentoRepository = clienteDepartamentoRepository;
    }

    public RotinaAtividadeRepository getRotinaAtividadeRepository() {
        return rotinaAtividadeRepository;
    }

    public void setRotinaAtividadeRepository(RotinaAtividadeRepository rotinaAtividadeRepository) {
        this.rotinaAtividadeRepository = rotinaAtividadeRepository;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public Clientedepartamento getClientedepartamento() {
        return clientedepartamento;
    }

    public void setClientedepartamento(Clientedepartamento clientedepartamento) {
        this.clientedepartamento = clientedepartamento;
    }

    public List<Atividadeusuario> getListaAtividades() {
        return listaAtividades;
    }

    public void setListaAtividades(List<Atividadeusuario> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    public AtividadeAguardandoRepository getAtividadeAguardandoRepository() {
        return atividadeAguardandoRepository;
    }

    public void setAtividadeAguardandoRepository(AtividadeAguardandoRepository atividadeAguardandoRepository) {
        this.atividadeAguardandoRepository = atividadeAguardandoRepository;
    }

    public void fechar() { 
        RequestContext.getCurrentInstance().closeDialog(null);
    }

    public void gerarListaAtividades() {
        String sqlRotinaCliente = "select r From Rotinacliente r where r.rotina.idrotina=" + rotina.getIdrotina()
                + " and r.cliente.idcliente=" + clientedepartamento.getCliente().getIdcliente();
        Rotinacliente rotinacliente = rotinaClienteRepository.find(sqlRotinaCliente);
        if (rotinacliente != null) {
            String sqlRotinaAtividade = "select r From Rotinaatividade r where r.rotina.idrotinacliente=" + rotinacliente.getIdrotinacliente()
                    + " and r.atividade.cliente.idcliente=" + rotinacliente.getCliente().getIdcliente();
            Rotinaatividade rotinaatividade = rotinaAtividadeRepository.find(sqlRotinaAtividade);
            if (rotinaatividade != null) {
                String sqlAtividadeUsuario = "select a From Atividadeusuario a where a.atividade.idatividade=" + rotinaatividade.getAtividade().getIdatividade()
                        + " and a.situacao<>'Concluida'";
                List<Atividadeusuario> lista = atividadeUsuarioRepository.list(sqlAtividadeUsuario);
                if (lista != null) {
                    verificarAtividadeAtrasada(lista);
                }
            }
        }
    }

    public void verificarAtividadeAtrasada(List<Atividadeusuario> lista) {
        listaAtividades = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getAtividade().getDataexecucao().isBefore(LocalDate.now())) {
                listaAtividades.add(lista.get(i));
            } else if (lista.get(i).getAtividade().getDataexecucao().equals(LocalDate.now())
                    && lista.get(i).getAtividade().getHoraexecucao() != null
                    && lista.get(i).getAtividade().getHoraexecucao().isBefore(LocalTime.now())) {
                listaAtividades.add(lista.get(i));
            }
        }
        removerAtivadadesAguardando();
    } 
    
    public void removerAtivadadesAguardando() { 
        for (int i = 0; i < listaAtividades.size(); i++) {
            String sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.idatividadeusuario=" +listaAtividades.get(i).getIdatividadeusuario()
                    + " and a.dataretorno> :dataInicial ";
            List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
            if (lista != null && lista.size()>0) {
                for (int j = 0; j < lista.size(); j++) {
                    listaAtividades.remove(lista.get(j).getAtividadeusuario());
                }
            } 
        } 
    }
}
