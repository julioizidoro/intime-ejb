/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.bean;

import br.com.intime.model.Atividade;
import br.com.intime.model.Atividadeusuario;
import br.com.intime.model.Rotinaatividade;
import br.com.intime.model.Rotinacliente;
import br.com.intime.model.Usuario;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Wolverine
 */
public class RotinaAtividadeBean {
    
    
    public boolean verificarTerminoRotina(Rotinacliente rotinaCliente){
        if (rotinaCliente.getRecorrencia()>0){
            if ((rotinaCliente.getTotalrecorrencia()+1)>rotinaCliente.getRecorrencia()){
                return true;
            }else {
                return false;
            }
        }else if (rotinaCliente.getTermina()!=null){
             if (rotinaCliente.getTermina().equalsIgnoreCase("nunca")){
                 return false;
             }
        }else if (rotinaCliente.getDatatermino()!=null){
            LocalDate dataAtual = LocalDate.now();
            if (rotinaCliente.getDatatermino().isAfter(dataAtual)){
                return false;
            }else return true;
        }
        return false;
    }
    
    public Atividade gerarProximaAtividadeDiaria(Atividadeusuario atividadeusuario, int multiplicador){
        int numeroDias=0;
        if (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinadiaria().isAcadadia()){
            numeroDias =atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinadiaria().getNumerodias()  * multiplicador;
        }else if (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinadiaria().isTodosdias()){
            numeroDias = multiplicador;
        }
        Atividade novaAtividade = new Atividade();
        novaAtividade.setCliente(atividadeusuario.getAtividade().getCliente());
        LocalDate novaData = atividadeusuario.getAtividade().getDataexecucao();
        novaData.plusDays(numeroDias);
        novaAtividade.setDataexecucao(novaData);
        novaAtividade.setDatalancamento(LocalDate.now());
        novaAtividade.setDescricao(atividadeusuario.getAtividade().getDescricao());
        novaAtividade.setHoraexecucao(atividadeusuario.getAtividade().getHoraexecucao());
        novaAtividade.setNotificacaohorario(atividadeusuario.getAtividade().isNotificacaohorario());
        novaAtividade.setPrioridade(atividadeusuario.getAtividade().getPrioridade());
        novaAtividade.setRotina(atividadeusuario.getAtividade().isRotina());
        novaAtividade.setSubdepartamento(atividadeusuario.getAtividade().getSubdepartamento());
        novaAtividade.setUsuario(atividadeusuario.getAtividade().getUsuario());
        return novaAtividade;
    }
    
    public Atividade gerarProximaAtividadeSemana(Atividadeusuario atividadeusuario, int multiplicador){
        int numeroSemanas = atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinasemanal().getNumerosemanas() * multiplicador;        
        Atividade novaAtividade = new Atividade();
        novaAtividade.setCliente(atividadeusuario.getAtividade().getCliente());
        LocalDate novaData = atividadeusuario.getAtividade().getDataexecucao();
        novaData.plusWeeks(numeroSemanas);
        novaAtividade.setDataexecucao(novaData);
        novaAtividade.setDatalancamento(LocalDate.now());
        novaAtividade.setDescricao(atividadeusuario.getAtividade().getDescricao());
        novaAtividade.setHoraexecucao(atividadeusuario.getAtividade().getHoraexecucao());
        novaAtividade.setNotificacaohorario(atividadeusuario.getAtividade().isNotificacaohorario());
        novaAtividade.setPrioridade(atividadeusuario.getAtividade().getPrioridade());
        novaAtividade.setRotina(atividadeusuario.getAtividade().isRotina());
        novaAtividade.setSubdepartamento(atividadeusuario.getAtividade().getSubdepartamento());
        novaAtividade.setUsuario(atividadeusuario.getAtividade().getUsuario());
        return novaAtividade;
    }
    
    public Atividade gerarProximaAtividadeMensal(Atividadeusuario atividadeusuario, int multiplicador){
        LocalDate dataNovaAtividade = atividadeusuario.getAtividade().getDataexecucao();
        if (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinamensal().isDiacadames()){
            int numeroMeses =atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinamensal().getNumeromesesdia() * multiplicador;
            dataNovaAtividade.plusMonths(numeroMeses);
        }else if (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinamensal().isNocadames()){
            dataNovaAtividade = gearDataDiaSemana(atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinamensal().getNumerosemana(),
                    atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinamensal().getDiasemana(), 
                    (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinamensal().getNumeromesesno() * multiplicador), 
                    atividadeusuario.getAtividade().getDataexecucao());
        }
        if (dataNovaAtividade!=null){
        Atividade novaAtividade = new Atividade();
        novaAtividade.setCliente(atividadeusuario.getAtividade().getCliente());
        novaAtividade.setDataexecucao(dataNovaAtividade);
        novaAtividade.setDatalancamento(LocalDate.now());
        novaAtividade.setDescricao(atividadeusuario.getAtividade().getDescricao());
        novaAtividade.setHoraexecucao(atividadeusuario.getAtividade().getHoraexecucao());
        novaAtividade.setNotificacaohorario(atividadeusuario.getAtividade().isNotificacaohorario());
        novaAtividade.setPrioridade(atividadeusuario.getAtividade().getPrioridade());
        novaAtividade.setRotina(atividadeusuario.getAtividade().isRotina());
        novaAtividade.setSubdepartamento(atividadeusuario.getAtividade().getSubdepartamento());
        novaAtividade.setUsuario(atividadeusuario.getAtividade().getUsuario());
        return novaAtividade;
        }
        return null;
    }
    
    public Atividade gerarProximaAtividadeAnual(Atividadeusuario atividadeusuario) {
        LocalDate dataNovaAtividade = atividadeusuario.getAtividade().getDataexecucao();
        if (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinaanual().isEmmesdia()) {
            int numeroAno = atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinaanual().getNumeroano();
            dataNovaAtividade.plusYears(numeroAno);
        } else if (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinaanual().isNodiasemanames()) {
            dataNovaAtividade = gearDataDiaSemana(atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinaanual().getNumerosemana(),
                    atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinaanual().getDiasemana(),
                    (atividadeusuario.getAtividade().getRotinaatividade().getRotina().getRotinaanual().getNumeroano() * 12),
                    atividadeusuario.getAtividade().getDataexecucao());
        }
        if (dataNovaAtividade != null) {
            Atividade novaAtividade = new Atividade();
            novaAtividade.setCliente(atividadeusuario.getAtividade().getCliente());
            LocalDate novaData = atividadeusuario.getAtividade().getDataexecucao();
            novaAtividade.setDataexecucao(dataNovaAtividade);
            novaAtividade.setDatalancamento(LocalDate.now());
            novaAtividade.setDescricao(atividadeusuario.getAtividade().getDescricao());
            novaAtividade.setHoraexecucao(atividadeusuario.getAtividade().getHoraexecucao());
            novaAtividade.setNotificacaohorario(atividadeusuario.getAtividade().isNotificacaohorario());
            novaAtividade.setPrioridade(atividadeusuario.getAtividade().getPrioridade());
            novaAtividade.setRotina(atividadeusuario.getAtividade().isRotina());
            novaAtividade.setSubdepartamento(atividadeusuario.getAtividade().getSubdepartamento());
            novaAtividade.setUsuario(atividadeusuario.getAtividade().getUsuario());
            return novaAtividade;
        }
        return null;
    }
    
    public LocalDate gearDataDiaSemana(String posicaoSemana, String diaSemana, int numeroMeses, LocalDate dataAtividade){
        dataAtividade.plusMonths(numeroMeses);
        int mes = dataAtividade.getMonthValue();
        int ano = dataAtividade.getYear();
        dataAtividade = LocalDate.of(ano, mes, 1);
        int numeroPosicaoSemana =0;
        if (posicaoSemana.equalsIgnoreCase("primeiro")){
            numeroPosicaoSemana=1;
        }else if (posicaoSemana.equalsIgnoreCase("segundo")){
            numeroPosicaoSemana=2;
        }else if (posicaoSemana.equalsIgnoreCase("terceiro")){
            numeroPosicaoSemana=3;
        }else if (posicaoSemana.equalsIgnoreCase("quarto")){
            numeroPosicaoSemana=4;
        }else if (posicaoSemana.equalsIgnoreCase("quinto")){
            numeroPosicaoSemana=5;
        }
        if (diaSemana.equalsIgnoreCase("segunda")){
            diaSemana = "Monday";
        }else if (diaSemana.equalsIgnoreCase("terça")){
            diaSemana = "Tuesday";
        }else if (diaSemana.equalsIgnoreCase("quarta")){
            diaSemana = "Wednesday";
        }else if (diaSemana.equalsIgnoreCase("quinta")){
            diaSemana = "Thursday";
        }else if (diaSemana.equalsIgnoreCase("sexta")){
            diaSemana = "Friday";
        }else if (diaSemana.equalsIgnoreCase("sabado")){
            diaSemana = "Saturday";
        }else if (diaSemana.equalsIgnoreCase("domingo")){
            diaSemana = "Sunday";
        } 
        int contadorSemana=0;
        for (int i=0; i<50;i++){
            if (dataAtividade.getDayOfWeek().name().equalsIgnoreCase(diaSemana)){
                contadorSemana++;
            }
            if (contadorSemana==numeroPosicaoSemana){
                return dataAtividade;
            }
            dataAtividade.plusDays(1);
            int mesData = dataAtividade.getMonthValue();
            int anoData = dataAtividade.getYear();
            if ((mesData>mes) || (anoData>ano)){
                return null;
            }
        }
        return null;
    }
    
    public Atividadeusuario gearAtivadadeUsuario(Atividade atividade, Usuario usuario){
        Atividadeusuario atividadeusuario = new Atividadeusuario();
        atividadeusuario.setAtividade(atividade);
        atividadeusuario.setConcluido(false);
        atividadeusuario.setSituacao("Pause");
        atividadeusuario.setUsuario(usuario);
        return atividadeusuario;
    }
    
    public Rotinaatividade gerarRotinaAtividade(Atividade atividade, Rotinacliente rotinaCliente){
        Rotinaatividade rotinaatividade = new Rotinaatividade();
        rotinaatividade.setAtividade(atividade);
        rotinaatividade.setRotina(rotinaCliente);
        return rotinaatividade;
    }
    
}
