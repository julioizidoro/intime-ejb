/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Clientedepartamento;
import br.com.intime.model.Departamento;
import br.com.intime.model.Rotina;
import br.com.intime.model.Rotinaatividade;
import br.com.intime.model.Rotinacliente;
import br.com.intime.model.Subdepartamento;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.ClienteDepartamentoRepository;
import br.com.intime.repository.DepartamentoRepository;
import br.com.intime.repository.RotinaAtividadeRepository;
import br.com.intime.repository.RotinaClienteRepository;
import br.com.intime.repository.RotinaRepository;
import br.com.intime.repository.SubDepartamentoRepository;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class AgendaRotinaMB implements Serializable {

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
    private Departamento departamento;
    private Subdepartamento subDepartamento;
    private List<Departamento> listaDepartamento;
    private List<Subdepartamento> listaSubDepartamento;
    private List<Rotina> listaRotina;
    private List<Clientedepartamento> listaCliente;

    @PostConstruct
    public void init() {
        gerarListaDepartamento();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Subdepartamento getSubDepartamento() {
        return subDepartamento;
    }

    public void setSubDepartamento(Subdepartamento subDepartamento) {
        this.subDepartamento = subDepartamento;
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

    public List<Rotina> getListaRotina() {
        return listaRotina;
    }

    public void setListaRotina(List<Rotina> listaRotina) {
        this.listaRotina = listaRotina;
    }

    public List<Clientedepartamento> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Clientedepartamento> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public void consultarRotinaAtrasada() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 600);
        RequestContext.getCurrentInstance().openDialog("consRotinaAtrasada", options, null);
    }

    public void gerarListaDepartamento() {
        listaDepartamento = departamentoRepository.list("Select d From Departamento d");
        if (listaDepartamento == null || listaDepartamento.isEmpty()) {
            listaDepartamento = new ArrayList<>();
        }
    }

    public void gerarListaSubDepartamento() {
        String sql = "Select s From Subdepartamento s where s.departamento.iddepartamento="
                + departamento.getIddepartamento() + " and s.status=true order by s.nome";
        listaSubDepartamento = subDepartamentoRepository.list(sql);
    }

    public void pesquisar() {
        if (departamento != null && departamento.getIddepartamento() != null) {
            String sql = "select r From Rotina r where r.subdepartamento.departamento.iddepartamento=" + departamento.getIddepartamento();
            if (subDepartamento != null && subDepartamento.getIdsubdepartamento() != null) {
                sql = sql + " and r.subdepartamento.idsubdepartamento=" + subDepartamento.getIdsubdepartamento();
            }
            sql = sql + " order by r.nome";
            listaRotina = rotinaRepository.list(sql);

            String sqlCliente = "Select c From Clientedepartamento c where c.departamento.iddepartamento=" + departamento.getIddepartamento();
            listaCliente = clienteDepartamentoRepository.list(sqlCliente);
            verificarImagensRotina();
        } else {
            Mensagem.lancarMensagemErro("Atenção!", "Departamento não informado.");
        }
    }

    public void verificarImagensRotina() {
        for (int i = 0; i < listaRotina.size(); i++) {
            for (int j = 0; j < listaCliente.size(); j++) {
                String sqlRotinaCliente = "select r From Rotinacliente r where r.rotina.idrotina=" + listaRotina.get(i).getIdrotina()
                        + " and r.cliente.idcliente=" + listaCliente.get(j).getCliente().getIdcliente();
                Rotinacliente rotinacliente = rotinaClienteRepository.find(sqlRotinaCliente);
                if (rotinacliente == null) {
                    listaRotina.get(i).setImagemcinza(true);
                    listaRotina.get(i).setImagemverde(false);
                    listaRotina.get(i).setImagemvermelha(false);
                } else {
                    String sqlRotinaAtividade = "select r Rotinaatividade r where r.rotina.idrotina=" + rotinacliente.getRotina().getIdrotina()
                            + " and r.atividade.cliente.idcliente=" + rotinacliente.getCliente().getIdcliente();
                    Rotinaatividade rotinaatividade = rotinaAtividadeRepository.find(sqlRotinaAtividade);
                    if (rotinaatividade != null) {
                        String sqlAtividadeUsuario = "select a From Atividadeusuario a where a.atividade.idatividade" + rotinaatividade.getAtividade().getIdatividade()
                                +" and a.situacao<>Concluida";
                        List<Atividadeusuario> listaAtividades = atividadeUsuarioRepository.list(sqlAtividadeUsuario);
                        if (listaAtividades != null) {
                            boolean ok = verificarAtividadeAtrasada(listaAtividades);
                            if(ok){
                                listaRotina.get(i).setImagemcinza(false);
                                listaRotina.get(i).setImagemverde(true);
                                listaRotina.get(i).setImagemvermelha(false);
                            }else{
                                listaRotina.get(i).setImagemcinza(false);
                                listaRotina.get(i).setImagemverde(false);
                                listaRotina.get(i).setImagemvermelha(true);
                            }
                        } else {
                            listaRotina.get(i).setImagemcinza(true);
                            listaRotina.get(i).setImagemverde(false);
                            listaRotina.get(i).setImagemvermelha(false);
                        }
                    } else {
                        listaRotina.get(i).setImagemcinza(true);
                        listaRotina.get(i).setImagemverde(false);
                        listaRotina.get(i).setImagemvermelha(false);
                    }
                }
            }
        }
    }

    public boolean verificarAtividadeAtrasada(List<Atividadeusuario> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getAtividade().getDataexecucao().isBefore(LocalDate.now())){
                return false;
            } else if(lista.get(i).getAtividade().getDataexecucao().equals(LocalDate.now())
                    && lista.get(i).getAtividade().getHoraexecucao()!=null 
                    && lista.get(i).getAtividade().getHoraexecucao().isBefore(LocalTime.now())){
                    return false; 
            }
        }
        return true;
    }
}
