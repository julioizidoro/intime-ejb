package br.com.intime.managerBean.atividades;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import br.com.intime.model.Atividadeaguardando;
import br.com.intime.model.Atividadeusuario; 
import br.com.intime.repository.AtividadeAguardandoRepository;
import br.com.intime.repository.AtividadeUsuarioRepository;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private List<Atividadeusuario> listaAtividade;
    @EJB
    private AtividadeUsuarioRepository atividadeUsuarioRepository;
    @EJB
    private AtividadeAguardandoRepository atividadeAguardandoRepository;
    
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
    private String aguardando="#E0E0E0"; 
    
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

    public String getAguardando() {
        return aguardando;
    }

    public void setAguardando(String aguardando) {
        this.aguardando = aguardando;
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
        gerarListaAtivadades();
    }
    
    public void mudarCoresBotoes(String funcao){
        if(funcao.equalsIgnoreCase("hoje")){
            hoje="#cba135";
            amanha="#E0E0E0";
            seteDias="#E0E0E0";
            todos="#E0E0E0";
            aguardando="#E0E0E0";
            dataInicial = LocalDate.now();
            dataFinal = LocalDate.now();
            gerarListaAtivadades();
        }else if(funcao.equalsIgnoreCase("amanha")){
            hoje="#E0E0E0";
            amanha="#cba135";
            seteDias="#E0E0E0";
            todos="#E0E0E0";
            aguardando="#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = hoje.plusDays(1);
            dataFinal = hoje.plusDays(1);
            gerarListaAtivadades();
        }else if(funcao.equalsIgnoreCase("seteDias")){
            hoje="#E0E0E0";  
            amanha="#E0E0E0";
            seteDias="#cba135";
            todos="#E0E0E0";
            aguardando="#E0E0E0";
            LocalDate hoje = LocalDate.now();
            dataInicial = LocalDate.now();
            dataFinal = hoje.plusDays(7);
            gerarListaAtivadades();
            gerarListaAtivadades();
        }else if(funcao.equalsIgnoreCase("todos")){
            hoje="#E0E0E0";
            amanha="#E0E0E0";
            seteDias="#E0E0E0";
            todos="#cba135";
            aguardando="#E0E0E0";
            dataInicial = null;
            dataFinal = null;
            gerarListaAtivadades();
        }else if(funcao.equalsIgnoreCase("aguardando")){
            hoje="#E0E0E0";
            amanha="#E0E0E0";
            seteDias="#E0E0E0";
            todos="#E0E0E0";
            aguardando="#cba135";
            gerarListaAtivadadesAguardando();
        }
    }
    
    public String adicionarAtividades() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadAtividades", options, null);
        return "";
    }
    
    public String visualizarComentarios() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("comentariosAtividades", options, null);
        return "";
    }
    
    public String editarAtividades() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadAtividades", options, null);
        return "";
    }
    
    public void gerarListaAtivadades(){
        if(buscar==null){
            buscar=" ";
        }
        String sql ="SELECT a FROM Atividadeusuario a where a.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario() 
                + " and a.atividade.descricao like '%" + buscar + "%' ";
        String sqlConcluida ="";
        String sqlData ="";
        String sqlOrderBy="";
        if ((naoconcluidas.equalsIgnoreCase("true")) && (concluidas.equalsIgnoreCase("false"))){
            sqlConcluida = " and a.situacao<>'Concluida' ";
            if (dataInicial!=null){
                sqlData = " and a.atividade.dataexecucao>= :dataInicial and a.atividade.dataexecucao<= :dataFinal ";
            }
            sqlOrderBy = " ORDER BY a.atividade.dataexecucao";
        }else {
            sqlConcluida = " and a.situacao='Concluida' ";
            if (dataInicial!=null){
                sqlData = " and a.dataconclusao>= :dataInicial and a.dataconclusao<= :dataFinal ";
            }
            sqlOrderBy = " ORDER BY a.dataconclusao";
        }
        sql = sql + sqlConcluida + sqlData + sqlOrderBy;
        listaAtividade = atividadeUsuarioRepository.list(sql, dataInicial, dataFinal);
        if ((naoconcluidas.equalsIgnoreCase("true")) && (concluidas.equalsIgnoreCase("false"))){
            gerarDataHoraMostrarNaoConcluidas();
        }else {
            
        }
    }
    
    public void gerarListaAtivadadesAguardando(){
        if(buscar==null){
            buscar=" ";
        }
        String sql = "SELECT a FROM Atividadeaguardando a where a.atividadeusuario.usuario.idusuario=" + usuarioLogadoMB.getUsuario().getIdusuario()
                + " a.dataretorno>= :dataInicial "
                + " a.atividadeusuario.atividade.descricao like '%" + buscar + "%'"
                + "' ORDER BY a.dataretorno ";
        List<Atividadeaguardando> lista = atividadeAguardandoRepository.list(sql, LocalDate.now(), null);
        if (lista!=null){
            listaAtividade = new ArrayList<Atividadeusuario>();
            for(int i=0;i<lista.size();i++){
                listaAtividade.add(lista.get(i).getAtividadeusuario());
            }
        }
    }
    
    public void gerarDataHoraMostrarNaoConcluidas(){
        if (listaAtividade!=null){
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM");
            for(int i=0;i<listaAtividade.size();i++){
                LocalDate data = listaAtividade.get(i).getAtividade().getDataexecucao();
                LocalTime hora = listaAtividade.get(i).getAtividade().getHoraexecucao();
                String dataMostrar = data.format(formatador);
                String horaMostrar ="";
                listaAtividade.get(i).getAtividade().setDataMostrar(dataMostrar);
                if ((hora.getHour()==23) && (hora.getMinute()==59)){
                    horaMostrar = "";
                }else {
                    int ih = hora.getHour();
                    int im = hora.getMinute();
                    if (ih<=9){
                        horaMostrar = "0";
                    }
                    horaMostrar = horaMostrar + String.valueOf(ih) + ":";
                    if (im<=9){
                        horaMostrar = horaMostrar + "0";
                    }
                    horaMostrar = horaMostrar + String.valueOf(im);
                }
                listaAtividade.get(i).getAtividade().setHoraMostrar(horaMostrar);
            }
        }
    }
    
  
    
}
