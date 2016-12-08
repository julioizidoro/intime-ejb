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
    
    private String play="true";
    private String pause="false";
    
    @PostConstruct
    public void init(){
        
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
    
    public void modificarStatusTarefa(){
        if(play.equalsIgnoreCase("false")){
            play="true";
            pause="false";
        }else{
            play="false";
            pause="true";
        }
    }
    
    public String adicionarTarefa() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadTarefas", options, null);
        return "";
    }
    
}
