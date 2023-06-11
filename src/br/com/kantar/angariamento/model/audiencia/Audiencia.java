/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.audiencia;

import br.com.kantar.angariamento.model.arquivo.ArquivoMet;
import br.com.kantar.angariamento.model.target.Individuo;
import br.com.kantar.angariamento.model.componente.audiometrico.Televisor;
import br.com.kantar.angariamento.model.target.Domicilio;
import br.com.kantar.angariamento.model.afericao.Afericao;
import br.com.kantar.angariamento.kantar.model.audiometrico.Audiometria;
import br.com.kantar.angariamento.model.componente.audiometrico.Canal;
import br.com.kantar.angariamento.servico.audiometrico.TelevisoresServico;

import br.com.kantar.angariamento.model.estatistico.Peso;
import br.com.kantar.angariamento.servico.arquivo.ArquivoMetServico;
import br.com.kantar.angariamento.util.aud.normalizador.cov.entryPointCov;
import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import br.com.kantar.angariamento.controller.estatistico.DemograficaController;
import br.com.kantar.angariamento.controller.estatistico.PesoController;
import br.com.kantar.angariamento.controller.target.IndividuoController;
import br.com.kantar.angariamento.model.estatistico.Demografica;
import br.com.metodos.uteis.gerais.ServicosUteisGerais;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.io.*;
import java.time.LocalDate;

import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;
import org.apache.commons.io.*;

/**
 *
 * @author Eduardo.Fernando
 */
public class Audiencia {

    private ArquivoMet info_audiometrica;
    private Domicilio info_domiciliares;
    private int total_tv_dom;
    private int total_ind_dom;
    private List<Afericao> lista_afericao_domiciliar;
    private List<Individuo> lista_afericao_individuo;

    public Audiencia() {
    }

    public Audiencia(ArquivoMet p_info_audiometrica, Domicilio p_info_domiciliares, int p_total_tv_dom, int p_total_ind_dom, List<Afericao> p_lista_afericao_domiciliar, List<Individuo> p_lista_afericao_individuo) {
        this.info_audiometrica = p_info_audiometrica;
        this.info_domiciliares = p_info_domiciliares;
        this.total_tv_dom = p_total_tv_dom;
        this.total_ind_dom = p_total_ind_dom;
        this.lista_afericao_domiciliar = p_lista_afericao_domiciliar;
        this.lista_afericao_individuo = p_lista_afericao_individuo;
    }

    public ArquivoMet getInfo_audiometrica() {
        return info_audiometrica;
    }

    public Domicilio getInfo_domiciliares() {
        return info_domiciliares;
    }

    public int getTotal_tv_dom() {
        return total_tv_dom;
    }

    public int getTotal_ind_dom() {
        return total_ind_dom;
    }

    public List<Afericao> getLista_afericao_domiciliar() {
        return lista_afericao_domiciliar;
    }

    public List<Individuo> getLista_afericao_individuo() {
        return lista_afericao_individuo;
    }

}
