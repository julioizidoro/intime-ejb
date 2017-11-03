package br.com.intime.managerBean.menu;

import br.com.intime.managerBean.usuario.UsuarioLogadoMB;
import java.io.Serializable; 
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.SessionScoped; 
import javax.inject.Inject;
import javax.inject.Named; 
import org.primefaces.context.RequestContext;

/**
 *
 * @author Kamila
 */
@Named
@SessionScoped
public class MenuMB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private UsuarioLogadoMB usuarioLogadoMB;

    public UsuarioLogadoMB getUsuarioLogadoMB() {
        return usuarioLogadoMB;
    }

    public void setUsuarioLogadoMB(UsuarioLogadoMB usuarioLogadoMB) {
        this.usuarioLogadoMB = usuarioLogadoMB;
    }

    public String cliente() {
        return "consCliente";
    }
    
    public String usuario() {
        return "consUsuario";
    }
    
    public String atividades() {
        return "consAtividades";
    }
    
    public String novaEmpresa(){
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 580);
        RequestContext.getCurrentInstance().openDialog("cadEmpresa", options, null);
        return "";
    }
    
    
    public String departamentos() {
        return "consDepartamento";
    }
    
    public String motivosatraso() {
        return "consMotivoAtraso";
    }
    
    public String consRotina() {
        return "consRotina";
    }
    
     public String cadastrarRotina() {
        return "cadRotina";
    }
     
    public String agendaRotina() {
        return "consAgendaRotina";
    }
    
     public String consProcesso() {
        return "consProcesso";
    }

    
    public String adicionarAtividades() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("contentWidth", 500);
        RequestContext.getCurrentInstance().openDialog("cadAtividades", options, null);
        return "";
    }
    
    public boolean acessoMenuCliente(){
        if(usuarioLogadoMB.getUsuario().getCadastroclinte()!=1){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean acessoMenuRotina(){
        if(usuarioLogadoMB.getUsuario().getCadastrorotina()!=1){
            return true;
        }else{
            return false;
        }
    } 
    
    public boolean acessoMenuUsuario(){
        if(usuarioLogadoMB.getUsuario().getCadastrusuario()!=1){
            return true;
        }else{
            return false;
        }
    }
    
    
    public String relatoriosAtividadeConcluida() {
        return "relatoriosAtividadeConcluida";
    }
}
