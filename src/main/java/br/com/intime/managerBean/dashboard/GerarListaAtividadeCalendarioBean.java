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
        inicarListaCalendario(listaAtividade);
    }

    public List<AtividadeCalendarioBean> getLista() {
        return lista;
    }

    public void setLista(List<AtividadeCalendarioBean> lista) {
        this.lista = lista;
    }

    public void inicarListaCalendario(List<Atividadeusuario> listaAtividade) {
        LocalDate data = listaAtividade.get(0).getAtividade().getDataexecucao();
        AtividadeCalendarioBean atividadeCalendarioBean = null;
        for (int i = 0; i < listaAtividade.size(); i++) {
            if (!data.equals(listaAtividade.get(i).getAtividade().getDataexecucao())) {
                data = listaAtividade.get(i).getAtividade().getDataexecucao();
                if (atividadeCalendarioBean != null) {
                    lista.add(atividadeCalendarioBean);
                    atividadeCalendarioBean = null;
                }
            }
            if (data.equals(listaAtividade.get(i).getAtividade().getDataexecucao())) {
                if (atividadeCalendarioBean == null) {
                    atividadeCalendarioBean = new AtividadeCalendarioBean(data);
                    atividadeCalendarioBean.setTotalAtividade(atividadeCalendarioBean.getTotalAtividade() + 1);
                    if (listaAtividade.get(i).getSituacao().equalsIgnoreCase("Concluida")) {
                        atividadeCalendarioBean.setTotalconcluidas(atividadeCalendarioBean.getTotalconcluidas() + 1);
                    }
                } else {
                    atividadeCalendarioBean.setTotalAtividade(atividadeCalendarioBean.getTotalAtividade() + 1);
                    if (listaAtividade.get(i).getSituacao().equalsIgnoreCase("Concluida")) {
                        atividadeCalendarioBean.setTotalconcluidas(atividadeCalendarioBean.getTotalconcluidas() + 1);
                    }
                }
                if ((i + 1) == listaAtividade.size()) {
                    if (atividadeCalendarioBean != null) {
                        lista.add(atividadeCalendarioBean); 
                    }
                }
            }
        }
    }
}
