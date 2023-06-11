/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.afericao;


import br.com.kantar.angariamento.model.componente.audiometrico.Canal;
import br.com.kantar.angariamento.model.componente.audiometrico.Televisor;
import br.com.kantar.angariamento.kantar.model.audiometrico.Audiometria;
import br.com.kantar.angariamento.model.estatistico.Peso;


/**
 *
 * @author Eduardo.Fernando
 */
public class Afericao {

    private Televisor televisor;
    private Canal canal;
    private Audiometria sintonia;
    private String NumeroDomicilio;
    private String Individuo;
    private Peso Peso;

    public Afericao(Televisor televisor, Canal canal, Audiometria sintonia, String NumeroDomicilio, String Individuo,Peso Peso) {
        this.televisor = televisor;
        this.canal = canal;
        this.sintonia = sintonia;
        this.NumeroDomicilio = NumeroDomicilio;
        this.Individuo = Individuo;
        this.Peso = Peso;
    }

    @Override
    public String toString() {
        return "Afericao{" + "televisor=" + televisor + ", canal=" + canal + ", sintonia=" + sintonia + ", NumeroDomicilio=" + NumeroDomicilio + ", Individuo=" + Individuo + ", Peso=" + Peso + '}';
    }




    public Televisor getTelevisor() {
        return televisor;
    }

    public Canal getCanal() {
        return canal;
    }

    public Audiometria getSintonia() {
        return sintonia;
    }

    public String getNumeroDomicilio() {
        return NumeroDomicilio;
    }

    public void setNumeroDomicilio(String NumeroDomicilio) {
        this.NumeroDomicilio = NumeroDomicilio;
    }

    public String getIndividuo() {
        return Individuo;
    }

    public void setIndividuo(String Individuo) {
        this.Individuo = Individuo;
    }

    public Peso getPeso() {
        return Peso;
    }

    public void setPeso(Peso Peso) {
        this.Peso = Peso;
    }

    

}
