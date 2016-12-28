package br.com.intime.managerBean.dashboard;

import br.com.intime.model.Atividade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Kamila
 */
@Named
@SessionScoped
public class DashboradMB implements Serializable {
    
    private List<Atividade> tarefasAtrasadas;
    
}
