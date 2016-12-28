package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.repository.AtividadeUsuarioRepository;
import br.com.intime.util.Formatacao;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class AtividadesMB implements Serializable{
    
    @Inject 
    private UsuarioLogadoMB usuarioLogadoMB;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    private List<Atividadeusuario> listaAtividade;
    
    //btn executar atividade
    private String play="true";
    private String pause="false";
    //btn visualizar concluidas
    private String concluidas="false";
    private String naoconcluidas="true";
    //cores botoes
    private String hoje="#cba135";
    private String amanha="#E0E0E0";
    private String seteDias="#E0E0E0";
    private String todos="#E0E0E0"; 
    
    //Data para consultas
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    
    private String buscar;
    
    @PostConstruct
    public void init(){
        mudarCoresBotoes("hoje");
    }

    public List<Atividadeusuario> getListaAtividade() {
        return listaAtividade;
    }

    public void setListaAtividade(List<Atividadeusuario> listaAtividade) {
        this.listaAtividade = listaAtividade;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }
     
    
    
    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getPause() {
        return pause;
    }

    public void setPause(String pause) {
        this.pause = pause;
    } 

    public String getConcluidas() {
        return concluidas;
    }

    public void setConcluidas(String concluidas) {
        this.concluidas = concluidas;
    }

    public String getNaoconcluidas() {
        return naoconcluidas;
    }

    public void setNaoconcluidas(String naoconcluidas) {
        this.naoconcluidas = naoconcluidas;
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
    
    public void modificarStatusTarefa(){
        if(play.equalsIgnoreCase("false")){
            play="true";
            pause="false";
        }else{
            play="false";
            pause="true";
        }
    }
    
    public void visualizarConcluidas(){
        if(naoconcluidas.equalsIgnoreCase("false")){
            naoconcluidas="true";
            concluidas="false";
        }else{
            naoconcluidas="false";
            concluidas="true";
        }
    }
    
    public void mudarCoresBotoes(String funcao){
        if(funcao.equalsIgnoreCase("hoje")){
            hoje="#cba135";
            amanha="#E0E0E0";
            seteDias="#E0E0E0";
            todos="#E0E0E0";
            dataInicial = LocalDate.now();
            dataFinal = LocalDate.now();
        }else if(funcao.equalsIgnoreCase("amanha")){
            hoje="#E0E0E0";
            amanha="#cba135";
            seteDias="#E0E0E0";
            todos="#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = hoje.plusDays(1);
            dataFinal = hoje.plusDays(1);
        }else if(funcao.equalsIgnoreCase("seteDias")){
            hoje="#E0E0E0";
            amanha="#E0E0E0";
            seteDias="#cba135";
            todos="#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = hoje;
            dataFinal = hoje.plusDays(7);
        }else if(funcao.equalsIgnoreCase("todos")){
            hoje="#E0E0E0";
            amanha="#E0E0E0";
            seteDias="#E0E0E0";
            todos="#cba135";
            dataInicial = null;
            dataFinal = null;
        }
        gerarListaAtivadades();
    }
    
    public String adicionarTarefa() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadTarefas", options, null);
        return "";
    }
    
    public String visualizarComentarios() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("comentariosTarefa", options, null);
        return "";
    }
    
    public String editarTarefa() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadTarefas", options, null);
        return "";
    }
    
    public void gerarListaAtivadades(){
        String sql ="SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario() 
                + " and a.atividade.descricao like '%" + buscar + "' ";
        String sqlConcluida ="";
        String sqlData ="";
        String sqlOrderBy="";
        if ((naoconcluidas.equalsIgnoreCase("true")) && (concluidas.equalsIgnoreCase("false"))){
            sqlConcluida = " and a.situacao<>'Concluida' ";
            if (dataInicial!=null){
                sqlData = " and a.atividade.dataexecucao>='" + dataInicial.toString() + "' and a.atividade.dataexecucao<='" + dataFinal.toString() + "' ";
            }
            sqlOrderBy = " ORDER BY a.atividade.dataexecucao";
        }else {
            sqlConcluida = " and a.situacao='Concluida' ";
            if (dataInicial!=null){
                sqlData = " and a.dataconclusao>='" + dataInicial.toString() + "' and a.dataconclusao<='" + dataFinal.toString() + "' ";
            }
            sqlOrderBy = " ORDER BY a.dataconclusao";
        }
        sql = sql + sqlConcluida + sqlData + sqlOrderBy;
        listaAtividade = atividadeUsuarioRepository.list(sql);
    }
    
  
    
}
