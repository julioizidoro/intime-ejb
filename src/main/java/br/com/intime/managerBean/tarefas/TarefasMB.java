package br.com.intime.managerBean.tarefas;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class TarefasMB implements Serializable{
    
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
        }else if(funcao.equalsIgnoreCase("amanha")){
            hoje="#E0E0E0";
            amanha="#cba135";
            seteDias="#E0E0E0";
            todos="#E0E0E0";
        }else if(funcao.equalsIgnoreCase("seteDias")){
            hoje="#E0E0E0";
            amanha="#E0E0E0";
            seteDias="#cba135";
            todos="#E0E0E0";
        }else if(funcao.equalsIgnoreCase("todos")){
            hoje="#E0E0E0";
            amanha="#E0E0E0";
            seteDias="#E0E0E0";
            todos="#cba135";
        }
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
    
}
