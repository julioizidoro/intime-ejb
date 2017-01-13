/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.dashboard;

import br.com.intime.model.Atividadeusuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jizid
 */
public class GerarListaAtividadeCalendarioBean {
    
    private List<AtividadeCalendarioBean> lista;

    public GerarListaAtividadeCalendarioBean(List<Atividadeusuario> listaAtividade) {
        this.lista = new ArrayList<AtividadeCalendarioBean>();
    }

    public List<AtividadeCalendarioBean> getLista() {
        return lista;
    }

    public void setLista(List<AtividadeCalendarioBean> lista) {
        this.lista = lista;
    }
    
    public void inicarListaCalendario(List<Atividadeusuario> listaAtividade){
        LocalDate data = listaAtividade.get(0).getAtividade().getDataexecucao();
        AtividadeCalendarioBean atividadeCalendarioBean = null;
        for (int i=0;i<listaAtividade.size();i++){
            boolean dataok=false;
            while (!dataok){
                if (data.equals(listaAtividade.get(i).getAtividade().getDataexecucao())){
                    if (atividadeCalendarioBean==null){
                        atividadeCalendarioBean = new AtividadeCalendarioBean(data);
                        atividadeCalendarioBean.setTotalAtividade(atividadeCalendarioBean.getTotalAtividade() + 1);
                        if (listaAtividade.get(i).getSituacao().equalsIgnoreCase("Concluida")){
                            atividadeCalendarioBean.setTotalconcluidas(atividadeCalendarioBean.getTotalconcluidas() + 1);
                        }
                    }
                    dataok=true;
                }else {
                    if (atividadeCalendarioBean!=null){
                        lista.add(atividadeCalendarioBean);
                        atividadeCalendarioBean = null;
                    }else {
                        atividadeCalendarioBean = new AtividadeCalendarioBean(data);
                        lista.add(atividadeCalendarioBean);
                        atividadeCalendarioBean = null;
                    }
                    data = data.plusDays(1);
                    dataok=false;
                }
            }
            
        }
    }
    
    
    
}
