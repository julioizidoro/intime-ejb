/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.managerBean.bean.RotinaAtividadeBean;
import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Cliente;
import br.com.intime.model.Rotina;
import br.com.intime.model.Rotinaanual;
import br.com.intime.model.Rotinaatividade;
import br.com.intime.model.Rotinacliente;
import br.com.intime.model.Rotinadiaria;
import br.com.intime.model.Rotinamensal;
import br.com.intime.model.Rotinasemanal;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AtividadeRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.RotinaAnualRepository;
import br.com.intime.repository.RotinaAtividadeRepository;
import br.com.intime.repository.RotinaClienteRepository;
import br.com.intime.repository.RotinaDiarioRepository;
import br.com.intime.repository.RotinaMensalRepository;
import br.com.intime.repository.RotinaSemanalRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Formatacao;
import br.com.intime.util.Mensagem;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
public class CadFuncoesRotinaMB implements Serializable {

    @EJB
    private RotinaClienteRepository clienteRepository;
    @EJB
    private RotinaAtividadeRepository rotinaAtividadeRepository;
    @EJB
    private AtividadeRepository atividadeRepository;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private RotinaDiarioRepository rotinaDiarioRepository;
    @EJB
    private RotinaMensalRepository rotinaMensalRepository;
    @EJB
    private RotinaSemanalRepository rotinaSemanalRepository;
    @EJB
    private RotinaAnualRepository rotinaAnualRepository;
    @EJB
    private UsuarioRepository usuarioRepository;
    private List<Usuario> listaUsuario;
    private Usuario usuario;
    private boolean diario = false;
    private boolean semanal = false;
    private boolean mensal = false;
    private boolean anual = false;
    private boolean semInformacoes = false;
    private boolean terminaNunca = false;
    private boolean terminaApos = false;
    private boolean datafinal = false;
    private boolean hora = false;
    private boolean meta = false;
    private Cliente cliente;
    private Rotinacliente rotinacliente;
    private Rotina rotina;
    private Rotinadiaria rotinadiaria;
    private Rotinamensal rotinamensal;
    private Rotinasemanal rotinasemanal;
    private Rotinaanual rotinaanual;
    private Atividadeusuario atividadeusuario;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        rotina = (Rotina) session.getAttribute("rotina");
        cliente = (Cliente) session.getAttribute("cliente");
        session.removeAttribute("cliente");
        session.removeAttribute("rotina");
        rotinadiaria = new Rotinadiaria();
        rotinasemanal = new Rotinasemanal();
        rotinamensal = new Rotinamensal();
        rotinaanual = new Rotinaanual();
        atividadeusuario = new Atividadeusuario();
        gerarListaUsuario();
        if (rotina != null) {
            if (cliente != null) {
                rotinacliente = clienteRepository.find("select r from Rotinacliente r where"
                        + " r.rotina.idrotina=" + rotina.getIdrotina()
                        + " and r.cliente.idcliente=" + cliente.getIdcliente());
                if (rotinacliente != null) {
                    if (rotinacliente.getRotinadiaria() != null) {
                        rotinadiaria = rotinacliente.getRotinadiaria();
                        diario = true;
                    } else if (rotinacliente.getRotinasemanal() != null) {
                        rotinasemanal = rotinacliente.getRotinasemanal();
                        semanal = true;
                    } else if (rotinacliente.getRotinamensal() != null) {
                        rotinamensal = rotinacliente.getRotinamensal();
                        mensal = true;
                    } else if (rotinacliente.getRotinaanual() != null) {
                        rotinaanual = rotinacliente.getRotinaanual();
                        anual = true;
                    }
                    usuario = rotinacliente.getUsuario();
                    rotinacliente.setDatainicial(Date.from(rotinacliente.getDatainicio().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    if (rotinacliente.getHora() != null) {
                        hora = true;
                        LocalTime hora = rotinacliente.getHora();
                        String horaMostrar = "";
                        int ih = hora.getHour();
                        int im = hora.getMinute();
                        if (ih <= 9) {
                            horaMostrar = "0";
                        }
                        horaMostrar = horaMostrar + String.valueOf(ih) + ":";
                        if (im <= 9) {
                            horaMostrar = horaMostrar + "0";
                        }
                        horaMostrar = horaMostrar + String.valueOf(im);
                        rotinacliente.setHorario(horaMostrar);
                    }
                    if (rotinacliente.getMeta() != null && rotinacliente.getMeta() > 0) {
                        meta = true;
                    }
                    if (rotinacliente.getRecorrencia() != null && rotinacliente.getRecorrencia() > 0) {
                        terminaApos = true;
                    }
                    if (rotinacliente.getDatatermino() != null) {
                        datafinal = true;
                        rotinacliente.setDatafinal(Date.from(rotinacliente.getDatatermino().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    }
                } else {
                    rotinacliente = new Rotinacliente();
                    diario = true;
                }
            } else {
                rotinacliente = new Rotinacliente();
            }
        }
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isDiario() {
        return diario;
    }

    public void setDiario(boolean diario) {
        this.diario = diario;
    }

    public boolean isSemanal() {
        return semanal;
    }

    public void setSemanal(boolean semanal) {
        this.semanal = semanal;
    }

    public boolean isMensal() {
        return mensal;
    }

    public void setMensal(boolean mensal) {
        this.mensal = mensal;
    }

    public boolean isAnual() {
        return anual;
    }

    public void setAnual(boolean anual) {
        this.anual = anual;
    }

    public boolean isSemInformacoes() {
        return semInformacoes;
    }

    public void setSemInformacoes(boolean semInformacoes) {
        this.semInformacoes = semInformacoes;
    }

    public boolean isTerminaNunca() {
        return terminaNunca;
    }

    public void setTerminaNunca(boolean terminaNunca) {
        this.terminaNunca = terminaNunca;
    }

    public boolean isTerminaApos() {
        return terminaApos;
    }

    public void setTerminaApos(boolean terminaApos) {
        this.terminaApos = terminaApos;
    }

    public boolean isDatafinal() {
        return datafinal;
    }

    public void setDatafinal(boolean datafinal) {
        this.datafinal = datafinal;
    }

    public boolean isHora() {
        return hora;
    }

    public void setHora(boolean hora) {
        this.hora = hora;
    }

    public boolean isMeta() {
        return meta;
    }

    public void setMeta(boolean meta) {
        this.meta = meta;
    }

    public Rotinacliente getRotinacliente() {
        return rotinacliente;
    }

    public void setRotinacliente(Rotinacliente rotinacliente) {
        this.rotinacliente = rotinacliente;
    }

    public RotinaClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(RotinaClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
    }

    public RotinaAtividadeRepository getRotinaAtividadeRepository() {
        return rotinaAtividadeRepository;
    }

    public void setRotinaAtividadeRepository(RotinaAtividadeRepository rotinaAtividadeRepository) {
        this.rotinaAtividadeRepository = rotinaAtividadeRepository;
    }

    public AtividadeRepository getAtividadeRepository() {
        return atividadeRepository;
    }

    public void setAtividadeRepository(AtividadeRepository atividadeRepository) {
        this.atividadeRepository = atividadeRepository;
    }

    public AtividadeUsuarioRepository getAtividadeUsuarioRepository() {
        return atividadeUsuarioRepository;
    }

    public void setAtividadeUsuarioRepository(AtividadeUsuarioRepository atividadeUsuarioRepository) {
        this.atividadeUsuarioRepository = atividadeUsuarioRepository;
    }

    public Rotinadiaria getRotinadiaria() {
        return rotinadiaria;
    }

    public void setRotinadiaria(Rotinadiaria rotinadiaria) {
        this.rotinadiaria = rotinadiaria;
    }

    public Rotinamensal getRotinamensal() {
        return rotinamensal;
    }

    public void setRotinamensal(Rotinamensal rotinamensal) {
        this.rotinamensal = rotinamensal;
    }

    public Rotinasemanal getRotinasemanal() {
        return rotinasemanal;
    }

    public void setRotinasemanal(Rotinasemanal rotinasemanal) {
        this.rotinasemanal = rotinasemanal;
    }

    public Rotinaanual getRotinaanual() {
        return rotinaanual;
    }

    public void setRotinaanual(Rotinaanual rotinaanual) {
        this.rotinaanual = rotinaanual;
    }

    public RotinaDiarioRepository getRotinaDiarioRepository() {
        return rotinaDiarioRepository;
    }

    public void setRotinaDiarioRepository(RotinaDiarioRepository rotinaDiarioRepository) {
        this.rotinaDiarioRepository = rotinaDiarioRepository;
    }

    public RotinaMensalRepository getRotinaMensalRepository() {
        return rotinaMensalRepository;
    }

    public void setRotinaMensalRepository(RotinaMensalRepository rotinaMensalRepository) {
        this.rotinaMensalRepository = rotinaMensalRepository;
    }

    public RotinaSemanalRepository getRotinaSemanalRepository() {
        return rotinaSemanalRepository;
    }

    public void setRotinaSemanalRepository(RotinaSemanalRepository rotinaSemanalRepository) {
        this.rotinaSemanalRepository = rotinaSemanalRepository;
    }

    public RotinaAnualRepository getRotinaAnualRepository() {
        return rotinaAnualRepository;
    }

    public void setRotinaAnualRepository(RotinaAnualRepository rotinaAnualRepository) {
        this.rotinaAnualRepository = rotinaAnualRepository;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void verificarRecorrenciaDiaria() {
        if (diario) {
            verificarRecorrencia("diario");
        } else {
            verificarRecorrencia("desabilitarDiario");
        }
    }

    public void verificarRecorrenciaSemanal() {
        if (semanal) {
            verificarRecorrencia("semanal");
        } else {
            verificarRecorrencia("desabilitarSemanal");
        }
    }

    public void verificarRecorrenciaMensal() {
        if (mensal) {
            verificarRecorrencia("mensal");
        } else {
            verificarRecorrencia("desabilitarMensal");
        }
    }

    public void verificarRecorrenciaAnual() {
        if (anual) {
            verificarRecorrencia("anual");
        } else {
            verificarRecorrencia("desabilitarAnual");
        }
    }

    public void verificarRecorrencia(String tipoBotao) {
        if (tipoBotao.equalsIgnoreCase("diario")) {
            diario = true;
            semanal = false;
            mensal = false;
            anual = false;
            rotinadiaria.setAcadadia(false);
            rotinadiaria.setTodosdias(true);
        } else if (tipoBotao.equalsIgnoreCase("desabilitarDiario")) {
            diario = false;
            rotinadiaria.setAcadadia(false);
            rotinadiaria.setTodosdias(false);
        } else {
            diario = false;
            rotinadiaria.setAcadadia(false);
            rotinadiaria.setTodosdias(false);
        }
        if (tipoBotao.equalsIgnoreCase("semanal")) {
            diario = false;
            semanal = true;
            mensal = false;
            anual = false;
            semInformacoes = false;
        } else if (tipoBotao.equalsIgnoreCase("desabilitarSemanal")) {
            semanal = false;
        } else {
            semanal = false;
        }
        if (tipoBotao.equalsIgnoreCase("mensal")) {
            diario = false;
            semanal = false;
            mensal = true;
            anual = false;
            semInformacoes = false;
        } else if (tipoBotao.equalsIgnoreCase("desabilitarMensal")) {
            mensal = false;
        } else {
            mensal = false;
        }
        if (tipoBotao.equalsIgnoreCase("anual")) {
            diario = false;
            semanal = false;
            mensal = false;
            anual = true;
            semInformacoes = false;
        } else if (tipoBotao.equalsIgnoreCase("desabilitarAnual")) {
            anual = false;
        } else {
            anual = false;
        }

        if (!diario && !semanal && !mensal && !anual) {
            semInformacoes = true;
        }
    }

    public void verificarCheckNumDiasDiario() {
        if (rotinadiaria.isAcadadia()) {
            verificarInformacoesDiario("numDias");
        } else {
            verificarInformacoesDiario("desabilitarNumDias");
        }
    }

    public void verificarCheckTodosDiasDiario() {
        if (rotinadiaria.isTodosdias()) {
            verificarInformacoesDiario("todosDias");
        } else {
            verificarInformacoesDiario("desabilitarTodosDias");
        }
    }

    public void verificarInformacoesDiario(String tipo) {
        if (tipo.equalsIgnoreCase("numDias")) {
            rotinadiaria.setAcadadia(true);
            rotinadiaria.setTodosdias(false);
        } else {
            rotinadiaria.setAcadadia(false);
        }
        if (tipo.equalsIgnoreCase("todosDias")) {
            rotinadiaria.setAcadadia(false);
            rotinadiaria.setTodosdias(true);
        } else {
            rotinadiaria.setTodosdias(false);
        }
    }

    public void verificarCheckNumDiasMensal() {
        if (rotinamensal.isDiacadames()) {
            verificarInformacoesMensal("numDias");
        } else {
            verificarInformacoesMensal("desabilitarNumDias");
        }
    }

    public void verificarCheckNumDias2Mensal() {
        if (rotinamensal.isNocadames()) {
            verificarInformacoesMensal("numDias2");
        } else {
            verificarInformacoesMensal("desabilitarNumDias2");
        }
    }

    public void verificarInformacoesMensal(String tipo) {
        if (tipo.equalsIgnoreCase("numDias")) {
            rotinamensal.setNocadames(false);
            rotinamensal.setDiacadames(true);
        } else {
            rotinamensal.setDiacadames(false);
        }
        if (tipo.equalsIgnoreCase("numDias2")) {
            rotinamensal.setDiacadames(false);
            rotinamensal.setNocadames(true);
        } else {
            rotinamensal.setNocadames(false);
        }
    }

    public void verificarCheckNumMesAnual() {
        if (rotinaanual.isEmmesdia()) {
            verificarInformacoesAnual("numMes");
        } else {
            verificarInformacoesAnual("desabilitarNumMes");
        }

    }

    public void verificarCheckNumMes2Anual() {
        if (rotinaanual.isNodiasemanames()) {
            verificarInformacoesAnual("numMes2");
        } else {
            verificarInformacoesAnual("desabilitarNumMes2");
        }

    }

    public void verificarInformacoesAnual(String tipo) {
        if (tipo.equalsIgnoreCase("numMes")) {
            rotinaanual.setNodiasemanames(false);
            rotinaanual.setEmmesdia(true);
        } else {
            rotinaanual.setEmmesdia(false);
        }

        if (tipo.equalsIgnoreCase("numMes2")) {
            rotinaanual.setEmmesdia(false);
            rotinaanual.setNodiasemanames(true);
        } else {
            rotinaanual.setNodiasemanames(false);
        }
    }

    public void verificarCheckTerminaNunca() {
        if (terminaNunca) {
            verificarInformacoesTermina("terminaNunca");
        } else {
            verificarInformacoesTermina("desabilitarTerminaNunca");
        }

    }

    public void verificarCheckTerminaApos() {
        if (terminaApos) {
            verificarInformacoesTermina("terminaApos");
        } else {
            verificarInformacoesTermina("desabilitarTerminaApos");
        }

    }

    public void verificarCheckTerminaData() {
        if (datafinal) {
            verificarInformacoesTermina("terminaData");
        } else {
            verificarInformacoesTermina("desabilitarTerminaData");
        }

    }

    public void verificarInformacoesTermina(String tipo) {
        if (tipo.equalsIgnoreCase("terminaNunca")) {
            terminaApos = false;
            terminaNunca = true;
            datafinal = false;
        } else {
            terminaNunca = false;
        }

        if (tipo.equalsIgnoreCase("terminaApos")) {
            terminaApos = true;
            terminaNunca = false;
            datafinal = false;
        } else {
            terminaApos = false;
        }

        if (tipo.equalsIgnoreCase("terminaData")) {
            terminaApos = false;
            terminaNunca = false;
            datafinal = true;
        } else {
            datafinal = false;
        }

    }

    public boolean verificarCampoHora() {
        if (hora) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificarCampoMeta() {
        if (meta) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificarCampoDataFinal() {
        if (datafinal) {
            return false;
        } else {
            return true;
        }
    }

    public boolean verificarCampoRecorrencia() {
        if (terminaApos) {
            return false;
        } else {
            return true;
        }
    }

    public String salvar() {
        boolean ok = verificarDados();
        if (ok) {
            boolean novo = false;
            if (rotinacliente.getIdrotinacliente() == null) {
                novo = true;
            }
            rotinacliente.setCliente(cliente);
            rotinacliente.setRotina(rotina);
            rotinacliente.setUsuario(usuario);
            rotinacliente.setTotalrecorrencia(1);
            rotinacliente.setDatainicio(Formatacao.converterDateParaLocalDate(rotinacliente.getDatainicial()));
            if (rotinacliente.getDatafinal() != null) {
                rotinacliente.setDatatermino(Formatacao.converterDateParaLocalDate(rotinacliente.getDatafinal()));
            }
            if (rotinacliente.getHorario() != null) {
                rotinacliente.setHora(Formatacao.converterStringParaLocalTime(rotinacliente.getHorario()));
            } else {
                rotinacliente.setHora(LocalTime.of(23, 59));
            }
            rotinacliente = clienteRepository.update(rotinacliente);
            if (novo) {
                Atividade atividade = new Atividade();
                atividade.setCliente(cliente);
                atividade.setDataexecucao(rotinacliente.getDatainicio());
                atividade.setDatalancamento(LocalDate.now());
                atividade.setDescricao(rotina.getNome());
                atividade.setHoraexecucao(rotinacliente.getHora());
                atividade.setMeta(String.valueOf(rotinacliente.getMeta()));
                atividade.setPrioridade(rotinacliente.getPrioridade());
                atividade.setRotina(true);
                atividade.setSubdepartamento(rotina.getSubdepartamento());
                atividade.setUsuario(usuario);
                atividade.setNotificacaohorario(true);
                atividade = atividadeRepository.update(atividade);

                atividadeusuario = new Atividadeusuario();
                atividadeusuario.setAtividade(atividade);
                atividadeusuario.setSituacao("Play");
                atividadeusuario.setUsuario(usuario);
                atividadeusuario.setTempo("00:00");
                atividadeUsuarioRepository.update(atividadeusuario);

                Rotinaatividade rotinaatividade = new Rotinaatividade();
                rotinaatividade.setRotinacliente(rotinacliente);
                rotinaatividade.setAtividade(atividade);
                rotinaAtividadeRepository.update(rotinaatividade);
                this.atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
            } else {
                removerRotinasAtividade();
            }
            salvarProximasAtividades();
            RequestContext.getCurrentInstance().closeDialog(null);
            Mensagem.lancarMensagemInfo("Salvo com sucesso!", "");
        }
        return "";
    }

    public void salvarProximasAtividades() {
        if (diario) {
            salvarRotinaDiaria();
        } else if (semanal) {
            salvarRotinaSemanal();
        } else if (mensal) {
            salvarRotinaMensal();
        } else if (anual) {
            rotinaanual.setRotinacliente(rotinacliente);
            rotinaanual = rotinaAnualRepository.update(rotinaanual);
        }
        rotinacliente = clienteRepository.update(rotinacliente);
    }

    public void removerRotinasAtividade() {
        if (!diario) {
            if (rotinacliente.getRotinadiaria() != null) {
                rotinaDiarioRepository.remove(rotinacliente.getRotinadiaria().getIdrotinadiaria());
            }
        }
        if (!semanal) {
            if (rotinacliente.getRotinasemanal() != null) {
                rotinaSemanalRepository.remove(rotinacliente.getRotinasemanal().getIdrotinasemanal());
            }
        }
        if (!mensal) {
            if (rotinacliente.getRotinamensal() != null) {
                rotinaMensalRepository.remove(rotinacliente.getRotinamensal().getIdrotinamensal());
            }
        }
        if (!anual) {
            if (rotinacliente.getRotinaanual() != null) {
                rotinaAnualRepository.remove(rotinacliente.getRotinaanual().getIdrotinaanual());
            }
        }
    }

    public void gerarListaUsuario() {
        listaUsuario = usuarioRepository.list("select u from Usuario u where u.status=TRUE order by u.nome");
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<>();
        }
    }

    public boolean verificarDados() {
        if (usuario == null || usuario.getIdusuario() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Campo Responsável não preenchido.");
            return false;
        }
        if (rotinacliente.getDatainicial() == null) {
            Mensagem.lancarMensagemErro("Atenção!", "Campo Data inícial não preenchido.");
            return false;
        }
        if (rotinacliente.getDatainicial().before(new Date())) {
            Mensagem.lancarMensagemErro("Atenção!", "Data de início inválida.");
            return false;
        }
        if (diario) {
            if (rotinadiaria.isAcadadia()) {
                if (rotinadiaria.getNumerodias() == null || rotinadiaria.getNumerodias() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campo Número de dias não preenchido.");
                    return false;
                }
            }
        }
        if (semanal) {
            if (rotinasemanal.getNumerosemanas() == null || rotinasemanal.getNumerosemanas() == 0) {
                Mensagem.lancarMensagemErro("Atenção!", "Campo Número de semanas não preenchido.");
                return false;
            } else if (rotinasemanal.getNumerosemanas() > 3) {
                Mensagem.lancarMensagemErro("Atenção!", "Limite campo número de semanas atingido.");
                return false;
            }
        }
        if (mensal) {
            if (rotinamensal.isDiacadames()) {
                if (rotinamensal.getDiames() == null || rotinamensal.getDiames() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campo Dia de mês não preenchido.");
                    return false;
                }
                if (rotinamensal.getNumeromesesdia() == null || rotinamensal.getNumeromesesdia() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campo Número de meses não preenchido.");
                    return false;
                }
            }
            if (rotinamensal.isNocadames()) {
                if (rotinamensal.getNumerosemana() == null || rotinamensal.getNumerosemana().length() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
                if (rotinamensal.getDiasemana() == null || rotinamensal.getDiasemana().length() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
                if (rotinamensal.getNumeromesesno() == null || rotinamensal.getNumeromesesno() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
            }
        }
        if (anual) {
            if (rotinaanual.getNumeroano() == null || rotinaanual.getNumeroano() == 0) {
                Mensagem.lancarMensagemErro("Atenção!", "Campos número de ano(s) não preenchido.");
                return false;
            }
            if (rotinaanual.isEmmesdia()) {
                if (rotinaanual.getMesem() == null || rotinaanual.getMesem().length() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
                if (rotinaanual.getDiaem() == null || rotinaanual.getDiaem() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
            }
            if (rotinaanual.isNodiasemanames()) {
                if (rotinaanual.getNumerosemana() == null || rotinaanual.getNumerosemana().length() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
                if (rotinaanual.getDiasemana() == null || rotinaanual.getDiasemana().length() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
                if (rotinaanual.getMesem() == null || rotinaanual.getMesem().length() == 0) {
                    Mensagem.lancarMensagemErro("Atenção!", "Campos obrigatórios não preenchido.");
                    return false;
                }
            }
        }
        if (terminaApos) {
            if (rotinacliente.getRecorrencia() == null || rotinacliente.getRecorrencia() == 0) {
                Mensagem.lancarMensagemErro("Atenção!", "Campos qntd de recorrência(s) não preenchido.");
                return false;
            }
        }
        if (datafinal) {
            if (rotinacliente.getDatafinal() == null) {
                Mensagem.lancarMensagemErro("Atenção!", "Campos Data de termino não preenchido.");
                return false;
            }
        }

        return true;
    }

    public boolean diasSemanaRotinaSemanal(int numerodia) {
        LocalDate novadata = rotinacliente.getDatainicio().plusDays(numerodia);
        if (novadata.getDayOfWeek().name().equalsIgnoreCase("Monday")) {
            if (rotinasemanal.isSegunda()) {
                if (novadata.equals(rotinacliente.getDatainicio())
                        || novadata.isAfter(rotinacliente.getDatainicio())) {
                    atividadeusuario.getAtividade().setDataexecucao(novadata);
                    return true;
                }
            }
        } else if (novadata.getDayOfWeek().name().equalsIgnoreCase("Tuesday")) {
            if (rotinasemanal.isTerca()) {
                if (novadata.equals(rotinacliente.getDatainicio())
                        || novadata.isAfter(rotinacliente.getDatainicio())) {
                    atividadeusuario.getAtividade().setDataexecucao(novadata);
                    return true;
                }
            }
        } else if (novadata.getDayOfWeek().name().equalsIgnoreCase("Wednesday")) {
            if (rotinasemanal.isQuarta()) {
                if (novadata.equals(rotinacliente.getDatainicio())
                        || novadata.isAfter(rotinacliente.getDatainicio())) {
                    atividadeusuario.getAtividade().setDataexecucao(novadata);
                    return true;
                }
            }
        } else if (novadata.getDayOfWeek().name().equalsIgnoreCase("Thursday")) {
            if (rotinasemanal.isQuinta()) {
                if (novadata.equals(rotinacliente.getDatainicio())
                        || novadata.isAfter(rotinacliente.getDatainicio())) {
                    atividadeusuario.getAtividade().setDataexecucao(novadata);
                    return true;
                }
            }
        } else if (novadata.getDayOfWeek().name().equalsIgnoreCase("Friday")) {
            if (rotinasemanal.isSexta()) {
                if (novadata.equals(rotinacliente.getDatainicio())
                        || novadata.isAfter(rotinacliente.getDatainicio())) {
                    atividadeusuario.getAtividade().setDataexecucao(novadata);
                    return true;
                }
            }
        } else if (novadata.getDayOfWeek().name().equalsIgnoreCase("Saturday")) {
            if (rotinasemanal.isSabado()) {
                if (novadata.equals(rotinacliente.getDatainicio())
                        || novadata.isAfter(rotinacliente.getDatainicio())) {
                    atividadeusuario.getAtividade().setDataexecucao(novadata);
                    return true;
                }
            }
        } else if (novadata.getDayOfWeek().name().equalsIgnoreCase("Sunday")) {
            if (rotinasemanal.isDomingo()) {
                if (novadata.equals(rotinacliente.getDatainicio())
                        || novadata.isAfter(rotinacliente.getDatainicio())) {
                    atividadeusuario.getAtividade().setDataexecucao(novadata);
                    return true;
                }
            }
        }
        return false;
    }

    public void salvarRotinaDiaria() {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        rotinadiaria.setRotinacliente(rotinacliente);
        rotinadiaria = rotinaDiarioRepository.update(rotinadiaria);
        rotinacliente.setRotinadiaria(rotinadiaria);
        atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
        for (int i = 0; i < 6; i++) {
            boolean termino = rab.verificarTerminoRotina(rotinacliente);
            if (!termino) {
                Atividade atividade = rab.gerarProximaAtividadeDiaria(atividadeusuario, 1);
                atividade = atividadeRepository.update(atividade);
                atividadeusuario = rab.gearAtivadadeUsuario(atividade, usuario);
                atividadeUsuarioRepository.update(atividadeusuario);
                Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, rotinacliente);
                rotinaAtividadeRepository.update(rotinaatividade);
                atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
                rotinacliente.setRotinadiaria(rotinadiaria);
                atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
                rotinacliente.setTotalrecorrencia(rotinacliente.getTotalrecorrencia() + 1);
            }
        }
    }

    public void salvarRotinaSemanal() {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        rotinasemanal.setRotinacliente(rotinacliente);
        rotinasemanal = rotinaSemanalRepository.update(rotinasemanal);
        rotinacliente.setRotinasemanal(rotinasemanal);
        atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
        boolean primeiraatividade = true;
        for (int j = 0; j < 7; j++) {
            boolean termino = rab.verificarTerminoRotina(rotinacliente);
            if (!termino) {
                boolean diasemana = diasSemanaRotinaSemanal(j); 
                if (diasemana) {
                    if(!primeiraatividade){
                        Atividade atividade1 = rab.gerarProximaAtividadeSemana(atividadeusuario, 0);
                        atividade1 = atividadeRepository.update(atividade1);
                        atividadeusuario = rab.gearAtivadadeUsuario(atividade1, usuario);
                        atividadeUsuarioRepository.update(atividadeusuario);
                        Rotinaatividade rotinaatividade1 = rab.gerarRotinaAtividade(atividade1, rotinacliente);
                        rotinaAtividadeRepository.update(rotinaatividade1);
                        atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade1);
                        rotinacliente.setRotinasemanal(rotinasemanal);
                        atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
                        rotinacliente.setTotalrecorrencia(rotinacliente.getTotalrecorrencia() + 1);
                    }
                    
                    Atividade atividade2 = rab.gerarProximaAtividadeSemana(atividadeusuario, 1);
                    atividade2 = atividadeRepository.update(atividade2);
                    atividadeusuario = rab.gearAtivadadeUsuario(atividade2, usuario);
                    atividadeUsuarioRepository.update(atividadeusuario);
                    Rotinaatividade rotinaatividade2 = rab.gerarRotinaAtividade(atividade2, rotinacliente);
                    rotinaAtividadeRepository.update(rotinaatividade2);
                    atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade2);
                    rotinacliente.setRotinasemanal(rotinasemanal);
                    atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
                    rotinacliente.setTotalrecorrencia(rotinacliente.getTotalrecorrencia() + 1);

                    Atividade atividade3 = rab.gerarProximaAtividadeSemana(atividadeusuario, 1);
                    atividade3 = atividadeRepository.update(atividade3);
                    atividadeusuario = rab.gearAtivadadeUsuario(atividade3, usuario);
                    atividadeUsuarioRepository.update(atividadeusuario);
                    Rotinaatividade rotinaatividade3 = rab.gerarRotinaAtividade(atividade3, rotinacliente);
                    rotinaAtividadeRepository.update(rotinaatividade3);
                    atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade3);
                    rotinacliente.setRotinasemanal(rotinasemanal);
                    atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
                    rotinacliente.setTotalrecorrencia(rotinacliente.getTotalrecorrencia() + 1);

                    Atividade atividade4 = rab.gerarProximaAtividadeSemana(atividadeusuario, 1);
                    atividade4 = atividadeRepository.update(atividade4);
                    atividadeusuario = rab.gearAtivadadeUsuario(atividade4, usuario);
                    atividadeUsuarioRepository.update(atividadeusuario);
                    Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade4, rotinacliente);
                    rotinaAtividadeRepository.update(rotinaatividade);
                    atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
                    rotinacliente.setRotinasemanal(rotinasemanal);
                    atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
                    rotinacliente.setTotalrecorrencia(rotinacliente.getTotalrecorrencia() + 1);
                    
                    primeiraatividade=false;
                }
            }
        }
    }

    public void salvarRotinaMensal() {
        RotinaAtividadeBean rab = new RotinaAtividadeBean();
        rotinamensal.setRotinacliente(rotinacliente);
        rotinamensal = rotinaMensalRepository.update(rotinamensal);
        rotinacliente.setRotinamensal(rotinamensal);
        atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
        for (int i = 0; i < 1; i++) {
            boolean termino = rab.verificarTerminoRotina(rotinacliente);
            if (!termino) {
                Atividade atividade = rab.gerarProximaAtividadeSemana(atividadeusuario, 1);
                atividade = atividadeRepository.update(atividade);
                atividadeusuario = rab.gearAtivadadeUsuario(atividade, usuario);
                atividadeUsuarioRepository.update(atividadeusuario);
                Rotinaatividade rotinaatividade = rab.gerarRotinaAtividade(atividade, rotinacliente);
                rotinaAtividadeRepository.update(rotinaatividade);
                atividadeusuario.getAtividade().setRotinaatividade(rotinaatividade);
                rotinacliente.setRotinasemanal(rotinasemanal);
                atividadeusuario.getAtividade().getRotinaatividade().setRotinacliente(rotinacliente);
                rotinacliente.setTotalrecorrencia(rotinacliente.getTotalrecorrencia() + 1);
            }
        }
    }
}
