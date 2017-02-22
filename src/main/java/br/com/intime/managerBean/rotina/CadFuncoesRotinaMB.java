/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intime.managerBean.rotina;

import br.com.intime.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Anderson
 */
@Named
@ViewScoped
public class CadFuncoesRotinaMB implements Serializable{
    
    private List<Usuario> listaUsuario;
    private Usuario usuario;
    private boolean diario = true;
    private boolean semanal = false;
    private boolean mensal = false;
    private boolean anual = false;
    private boolean semInformacoes = false;
    private boolean numDiasDiario = true;
    private boolean todosDiasDiario = false;
    private boolean numDiasMensal = true;
    private boolean numDiasMensal2 = false;
    private boolean numMesAnual = true;
    private boolean numMesAnual2 = false;
    private boolean terminaNunca = false;
    private boolean terminaApos = false;
    private boolean terminaRecorrencia = false;
    private boolean terminaData = false;
    
    
    @PostConstruct
    public void init(){
        listaUsuario = new ArrayList<>();
    }

    
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isDiario() {
        return diario;
    }

    public void setDiario(boolean diario) {
        this.diario = diario;
    }

    public boolean isSemanal() {
        return semanal;
    }

    public void setSemanal(boolean semanal) {
        this.semanal = semanal;
    }

    public boolean isMensal() {
        return mensal;
    }

    public void setMensal(boolean mensal) {
        this.mensal = mensal;
    }

    public boolean isAnual() {
        return anual;
    }

    public void setAnual(boolean anual) {
        this.anual = anual;
    }

    public boolean isSemInformacoes() {
        return semInformacoes;
    }

    public boolean isNumDiasDiario() {
        return numDiasDiario;
    }

    public void setNumDiasDiario(boolean numDiasDiario) {
        this.numDiasDiario = numDiasDiario;
    }

    public boolean isTodosDiasDiario() {
        return todosDiasDiario;
    }

    public void setTodosDiasDiario(boolean todosDiasDiario) {
        this.todosDiasDiario = todosDiasDiario;
    }
    
    

    public void setSemInformacoes(boolean semInformacoes) {
        this.semInformacoes = semInformacoes;
    }

    public boolean isNumDiasMensal() {
        return numDiasMensal;
    }

    public void setNumDiasMensal(boolean numDiasMensal) {
        this.numDiasMensal = numDiasMensal;
    }

    public boolean isNumDiasMensal2() {
        return numDiasMensal2;
    }

    public void setNumDiasMensal2(boolean numDiasMensal2) {
        this.numDiasMensal2 = numDiasMensal2;
    }

    public boolean isNumMesAnual() {
        return numMesAnual;
    }

    public void setNumMesAnual(boolean numMesAnual) {
        this.numMesAnual = numMesAnual;
    }

    public boolean isNumMesAnual2() {
        return numMesAnual2;
    }

    public void setNumMesAnual2(boolean numMesAnual2) {
        this.numMesAnual2 = numMesAnual2;
    }

    public boolean isTerminaNunca() {
        return terminaNunca;
    }

    public void setTerminaNunca(boolean terminaNunca) {
        this.terminaNunca = terminaNunca;
    }

    public boolean isTerminaApos() {
        return terminaApos;
    }

    public void setTerminaApos(boolean terminaApos) {
        this.terminaApos = terminaApos;
    }

    public boolean isTerminaRecorrencia() {
        return terminaRecorrencia;
    }

    public void setTerminaRecorrencia(boolean terminaRecorrencia) {
        this.terminaRecorrencia = terminaRecorrencia;
    }

    public boolean isTerminaData() {
        return terminaData;
    }

    public void setTerminaData(boolean terminaData) {
        this.terminaData = terminaData;
    }

    
    
    
    public void verificarRecorrenciaDiaria(){
        if (diario) {
            verificarRecorrencia("diario");
        }else{
            verificarRecorrencia("desabilitarDiario");
        }
    }
    
    public void verificarRecorrenciaSemanal(){
        if (semanal) {
            verificarRecorrencia("semanal");
        }else{
            verificarRecorrencia("desabilitarSemanal");
        }
    }
    
    public void verificarRecorrenciaMensal(){
        if (mensal) {
            verificarRecorrencia("mensal");
        }else{
            verificarRecorrencia("desabilitarMensal");
        }
    }
    
    public void verificarRecorrenciaAnual(){
        if (anual) {
            verificarRecorrencia("anual");
        }else{
            verificarRecorrencia("desabilitarAnual");
        }
    }

    
    public void verificarRecorrencia(String tipoBotao){
        if (tipoBotao.equalsIgnoreCase("diario")) {
            diario = true;
            semanal = false;
            mensal = false;
            anual = false;
            todosDiasDiario = false;
            numDiasDiario = true;
        }else if (tipoBotao.equalsIgnoreCase("desabilitarDiario")) {
            diario = false;
            numDiasDiario = false;
            todosDiasDiario = false;
        }else{
            diario = false;
            numDiasDiario = false;
            todosDiasDiario = false;
        }
        
        if (tipoBotao.equalsIgnoreCase("semanal")) {
            diario = false;
            semanal = true;
            mensal = false;
            anual = false;
            semInformacoes = false;
        }else if (tipoBotao.equalsIgnoreCase("desabilitarSemanal")) {
            semanal = false;
        }else{
            semanal = false;
        }
        
        if (tipoBotao.equalsIgnoreCase("mensal")) {
            diario = false;
            semanal = false;
            mensal = true;
            anual = false;
            semInformacoes = false;
        }else if (tipoBotao.equalsIgnoreCase("desabilitarMensal")) {
            mensal = false;
        }else{
            mensal = false;
        }
        
        if (tipoBotao.equalsIgnoreCase("anual")) {
            diario = false;
            semanal = false;
            mensal = false;
            anual = true;
            semInformacoes = false;
        }else if (tipoBotao.equalsIgnoreCase("desabilitarAnual")) {
            anual = false;
        }else{
            anual = false;
        }
        
        if (!diario && !semanal && !mensal && !anual) {
            semInformacoes = true;
        }
        
    }
    
    
    public void verificarCheckNumDiasDiario(){
        if (numDiasDiario) {
            verificarInformacoesDiario("numDias");
        }else{
            verificarInformacoesDiario("desabilitarNumDias");
        }
        
    }
    
    public void verificarCheckTodosDiasDiario(){
        if (todosDiasDiario) {
            verificarInformacoesDiario("todosDias");
        }else{
            verificarInformacoesDiario("desabilitarTodosDias");
        }
        
    }
    
    public void verificarInformacoesDiario(String tipo){
        if (tipo.equalsIgnoreCase("numDias")) {
            todosDiasDiario = false;
            numDiasDiario = true;
        }else{
            numDiasDiario = false;
        }
        
        if (tipo.equalsIgnoreCase("todosDias")) {
            numDiasDiario = false;
            todosDiasDiario = true;
        }else{
            todosDiasDiario = false;
        }
        
    }
   
    
    public void verificarCheckNumDiasMensal(){
        if (numDiasMensal) {
            verificarInformacoesMensal("numDias");
        }else{
            verificarInformacoesMensal("desabilitarNumDias");
        }
        
    }
    
    public void verificarCheckNumDias2Mensal(){
        if (numDiasMensal2) {
            verificarInformacoesMensal("numDias2");
        }else{
            verificarInformacoesMensal("desabilitarNumDias2");
        }
        
    }
    
    public void verificarInformacoesMensal(String tipo){
        if (tipo.equalsIgnoreCase("numDias")) {
            numDiasMensal2 = false;
            numDiasMensal = true;
        }else{
            numDiasMensal = false;
        }
        
        if (tipo.equalsIgnoreCase("numDias2")) {
            numDiasMensal = false;
            numDiasMensal2 = true;
        }else{
            numDiasMensal2 = false;
        }
        
    }
    
    
    public void verificarCheckNumMesAnual(){
        if (numMesAnual) {
            verificarInformacoesAnual("numMes");
        }else{
            verificarInformacoesAnual("desabilitarNumMes");
        }
        
    }
    
    public void verificarCheckNumMes2Anual(){
        if (numMesAnual2) {
            verificarInformacoesAnual("numMes2");
        }else{
            verificarInformacoesAnual("desabilitarNumMes2");
        }
        
    }
    
    public void verificarInformacoesAnual(String tipo){
        if (tipo.equalsIgnoreCase("numMes")) {
            numMesAnual2 = false;
            numMesAnual = true;
        }else{
            numMesAnual = false;
        }
        
        if (tipo.equalsIgnoreCase("numMes2")) {
            numMesAnual = false;
            numMesAnual2 = true;
        }else{
            numMesAnual2 = false;
        }
        
    }
    
    
    
     public void verificarCheckTerminaNunca(){
        if (terminaNunca) {
            verificarInformacoesTermina("terminaNunca");
        }else{
            verificarInformacoesTermina("desabilitarTerminaNunca");
        }
        
    }
    
    public void verificarCheckTerminaApos(){
        if (terminaApos) {
            verificarInformacoesTermina("terminaApos");
        }else{
            verificarInformacoesTermina("desabilitarTerminaApos");
        }
        
    }
    
    public void verificarCheckTerminaData(){
        if (terminaData) {
            verificarInformacoesTermina("terminaData");
        }else{
            verificarInformacoesTermina("desabilitarTerminaData");
        }
        
    }
    
    public void verificarInformacoesTermina(String tipo){
        if (tipo.equalsIgnoreCase("terminaNunca")) {
            terminaApos = false;
            terminaNunca = true;
            terminaData = false;
        }else{
            terminaNunca = false;
        }
        
        if (tipo.equalsIgnoreCase("terminaApos")) {
            terminaApos = true;
            terminaNunca = false;
            terminaData = false;
        }else{
            terminaApos = false;
        }
        
        if (tipo.equalsIgnoreCase("terminaData")) {
            terminaApos = false;
            terminaNunca = false;
            terminaData = true;
        }else{
            terminaData = false;
        }
        
    }
}
