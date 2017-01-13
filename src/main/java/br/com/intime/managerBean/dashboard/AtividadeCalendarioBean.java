/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.dashboard;

import java.time.LocalDate;

/**
 *
 * @author jizid
 */
public class AtividadeCalendarioBean {
    
    private LocalDate data;
    private int totalAtividade;
    private  int totalconcluidas;

    public AtividadeCalendarioBean(LocalDate data) {
        this.data = data;
        this.totalAtividade = 0;
        this.totalconcluidas = 0;
    }
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getTotalAtividade() {
        return totalAtividade;
    }

    public void setTotalAtividade(int totalAtividade) {
        this.totalAtividade = totalAtividade;
    }

    public int getTotalconcluidas() {
        return totalconcluidas;
    }

    public void setTotalconcluidas(int totalconcluidas) {
        this.totalconcluidas = totalconcluidas;
    }
    
    
    
}
