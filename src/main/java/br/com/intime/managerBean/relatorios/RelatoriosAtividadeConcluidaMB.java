/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.relatorios;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeerro;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Usuario;
import br.com.intime.repository.AtividadeErroRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.repository.UsuarioRepository;
import br.com.intime.util.Formatacao;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

/**
 *
 * @author Anderson
 */

@Named
@ViewScoped
public class RelatoriosAtividadeConcluidaMB implements Serializable{
    
    
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;
    private List<Atividadeusuario> listaAtividade;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeErroRepository atividadeErroRepository;
    private Integer nErros;
    private int nAtividadesConcluidas;
    private String hoje;
    private String amanha;
    private String seteDias;
    private String todos;
    private String aguardando;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private String funcao;
    private List<Usuario> listaUsuario;
    private Atividadeusuario atividadeusuario;
    @EJB
    private UsuarioRepository usuarioRepository;
    private int tempoUtilizado;
    private String mostrarTempoUtilizado;
    private String nomeMes;
    private int ano;
    private String mesAno;
    private int mes;
    
    
    @PostConstruct
    public void init(){
        gerarListaAtivadades();
    }

    public List<Atividadeusuario> getListaAtividade() {
        return listaAtividade;
    }

    public void setListaAtividade(List<Atividadeusuario> listaAtividade) {
        this.listaAtividade = listaAtividade;
    }

    public Integer getnErros() {
        return nErros;
    }

    public void setnErros(Integer nErros) {
        this.nErros = nErros;
    }

    public int getnAtividadesConcluidas() {
        return nAtividadesConcluidas;
    }

    public void setnAtividadesConcluidas(int nAtividadesConcluidas) {
        this.nAtividadesConcluidas = nAtividadesConcluidas;
    }
    
    

    public String getHoje() {
        return hoje;
    }

    public void setHoje(String hoje) {
        this.hoje = hoje;
    }

    public String getAmanha() {
        return amanha;
    }

    public void setAmanha(String amanha) {
        this.amanha = amanha;
    }

    public String getSeteDias() {
        return seteDias;
    }

    public void setSeteDias(String seteDias) {
        this.seteDias = seteDias;
    }

    public String getTodos() {
        return todos;
    }

    public void setTodos(String todos) {
        this.todos = todos;
    }

    public String getAguardando() {
        return aguardando;
    }

    public void setAguardando(String aguardando) {
        this.aguardando = aguardando;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Atividadeusuario getAtividadeusuario() {
        return atividadeusuario;
    }

    public void setAtividadeusuario(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
    }

    public int getTempoUtilizado() {
        return tempoUtilizado;
    }

    public void setTempoUtilizado(int tempoUtilizado) {
        this.tempoUtilizado = tempoUtilizado;
    }

    public String getMostrarTempoUtilizado() {
        return mostrarTempoUtilizado;
    }

    public void setMostrarTempoUtilizado(String mostrarTempoUtilizado) {
        this.mostrarTempoUtilizado = mostrarTempoUtilizado;
    }

    public String getNomeMes() {
        return nomeMes;
    }

    public void setNomeMes(String nomeMes) {
        this.nomeMes = nomeMes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMesAno() {
        return mesAno;
    }

    public void setMesAno(String mesAno) {
        this.mesAno = mesAno;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    
    
     public void gerarListaAtivadades() {
        LocalDate hoje = LocalDate.now();
        dataInicial = LocalDate.of(hoje.getYear(), hoje.getMonth(), 1);
        dataFinal = hoje.plusDays(30);
        mes = hoje.getMonthValue();
        nomeMes = Formatacao.nomeMes(hoje.getMonthValue());
        ano = hoje.getYear(); 
        mesAno = nomeMes + " | " + ano;
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao='Concluida' and a.dataconclusao>= :dataInicial and a.dataconclusao<= :dataFinal";
        listaAtividade = new ArrayList<>();
        listaAtividade = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
         if (listaAtividade == null) {
             listaAtividade = new ArrayList<>();
         }
         nAtividadesConcluidas = 0;
         nErros = 0;
         tempoUtilizado = 0;
         for (int i = 0; i < listaAtividade.size(); i++) {
             nAtividadesConcluidas = nAtividadesConcluidas + 1;
             tempoUtilizado = tempoUtilizado + listaAtividade.get(i).getTempoatual();
             List<Atividadeerro> listaAtividadeerro = atividadeErroRepository.list("SELECT a FROM Atividadeerro a WHERE a.atividadeusuario.idatividadeusuario="
                + listaAtividade.get(i).getIdatividadeusuario());
             if (listaAtividadeerro != null && listaAtividadeerro.size() > 0) {
                 nErros = nErros + 1;
             }
         }
         mostrarTempoUtilizado = Formatacao.converterMinutosHora(tempoUtilizado);
    }
     
     
      public String retornarCorAtividades(Atividadeusuario atividadeusuario) {
        LocalDate data = LocalDate.now();
        if (((atividadeusuario.getAtividade().getDataexecucao().equals(data)
                && atividadeusuario.getAtividade().getHoraexecucao().isBefore(LocalTime.now()))
                || (atividadeusuario.getAtividade().getDataexecucao().isBefore(data)))
                && !atividadeusuario.getSituacao().equalsIgnoreCase("Concluida")) {
            return "#B22222;";
        }
        if (atividadeusuario.getSituacao().equalsIgnoreCase("Concluida")) {
            return "#9C9C9C;";
        } else {
            return "#4F4F4F;";
        }
    }
      
       public void mudarCoresBotoes(String funcao) {
        if (funcao.equalsIgnoreCase("hoje")) {
            hoje = "#cba135";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#E0E0E0";
            aguardando = "#E0E0E0";
            dataInicial = LocalDate.MIN;
            dataFinal = LocalDate.now();
            this.funcao="hoje";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("amanha")) {
            hoje = "#E0E0E0";
            amanha = "#cba135";
            seteDias = "#E0E0E0";
            todos = "#E0E0E0";
            aguardando = "#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = hoje.plusDays(1);
            dataFinal = hoje.plusDays(1);
            this.funcao="amanha";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("seteDias")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#cba135";
            todos = "#E0E0E0";
            aguardando = "#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = LocalDate.now();
            dataFinal = hoje.plusDays(7);
            this.funcao="seteDias";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("todos")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#cba135";
            aguardando = "#E0E0E0";
            dataInicial = null;
            dataFinal = null;
            this.funcao="todos";
            gerarListaAtivadades();
        } else if (funcao.equalsIgnoreCase("aguardando")) {
            hoje = "#E0E0E0";
            amanha = "#E0E0E0";
            seteDias = "#E0E0E0";
            todos = "#E0E0E0";
            aguardando = "#cba135";
            this.funcao="aguardando";
        }
    }
    
    public boolean mostrarComentarios(Atividadeusuario atividadeusuario) {
        if (atividadeusuario.getAtividadecomentarioList() == null || atividadeusuario.getAtividadecomentarioList().size() == 0) {
            return false;
        } else {
            return true;
        }
    }
        
        public int retornarNumComentarios(Atividadeusuario atividadeusuario) {
        if (atividadeusuario.getAtividadecomentarioList() != null
                && atividadeusuario.getAtividadecomentarioList().size() > 0) {
            return atividadeusuario.getAtividadecomentarioList().size();
        } else {
            return 0;
        }
    }
        
        
         public String visualizarComentarios(Atividadeusuario atividadeusuario) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.setAttribute("atividadeusuario", atividadeusuario);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("comentariosAtividades", options, null);
        return "";
    }
         
         
         public boolean mostrarTrianguloImportante(String prioridade) {
        if (prioridade.equalsIgnoreCase("Importante")) {
            return true;
        } else {
            return false;
        }
    }
         
         
         public boolean mostrarTrianguloUrgente(String prioridade) {
        if (prioridade.equalsIgnoreCase("Urgente")) {
            return true;
        } else {
            return false;
        }
    }
         
         
         public void selecionarAtividade(Atividadeusuario atividadeusuario) {
        this.atividadeusuario = atividadeusuario;
        Date dataexecutar = Date.from(atividadeusuario.getAtividade().getDataexecucao().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.atividadeusuario.getAtividade().setDataexecutar(dataexecutar);
        gerarListaUsuario();
    }
         
         
         public void gerarListaUsuario() {
        listaUsuario = usuarioRepository.list("select u from Usuario u where u.status=TRUE order by u.nome");
        if (listaUsuario == null) {
            listaUsuario = new ArrayList<Usuario>();
        }
    }
    
         
    public int retornarMes(){
        LocalDate hoje = LocalDate.now();
        int mes = hoje.getMonthValue();
        int diaFinal = Formatacao.getUltimoDiaMes(hoje.getDayOfYear(), hoje.getMonthValue(), 1);
        return diaFinal;
    }
    
    
    public void pesquisar(String tipo) {
        if (tipo.equalsIgnoreCase("mais")) {
            mes = mes + 1;
            if (mes > 12) {
                mes = 1;
                ano = ano + 1;
            }
        }else if(tipo.equalsIgnoreCase("menos")){
            mes = mes - 1;
            if (mes < 1) {
                mes = 12;
                ano = ano - 1;
            }
        }
        LocalDate hoje = LocalDate.now();
        dataInicial = LocalDate.of(ano, mes, 1);
        dataFinal = dataInicial.plusDays(30);
        nomeMes = Formatacao.nomeMes(mes);
        mesAno = nomeMes + " | " + ano;
        String sql = "SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " and a.situacao='Concluida' and a.dataconclusao>= :dataInicial and a.dataconclusao<= :dataFinal";
        listaAtividade = new ArrayList<>();
        listaAtividade = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
         if (listaAtividade == null) {
             listaAtividade = new ArrayList<>();
         }
         nAtividadesConcluidas = 0;
         nErros = 0;
         tempoUtilizado = 0;
         for (int i = 0; i < listaAtividade.size(); i++) {
             nAtividadesConcluidas = nAtividadesConcluidas + 1;
             tempoUtilizado = tempoUtilizado + listaAtividade.get(i).getTempoatual();
             List<Atividadeerro> listaAtividadeerro = atividadeErroRepository.list("SELECT a FROM Atividadeerro a WHERE a.atividadeusuario.idatividadeusuario="
                + listaAtividade.get(i).getIdatividadeusuario());
             if (listaAtividadeerro != null && listaAtividadeerro.size() > 0) {
                 nErros = nErros + 1;
             }
         }
         mostrarTempoUtilizado = Formatacao.converterMinutosHora(tempoUtilizado);
    }

}
