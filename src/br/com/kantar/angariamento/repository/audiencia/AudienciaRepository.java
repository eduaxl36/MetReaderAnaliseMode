/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.repository.audiencia;

import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import br.com.kantar.angariamento.util.aud.normalizador.cov.entryPointCov;
import br.com.kantar.angariamento.controller.estatistico.DemograficaController;
import br.com.kantar.angariamento.controller.estatistico.PesoController;
import br.com.kantar.angariamento.controller.target.IndividuoController;
import br.com.kantar.angariamento.kantar.model.audiometrico.Audiometria;
import br.com.kantar.angariamento.model.afericao.Afericao;
import br.com.kantar.angariamento.model.arquivo.ArquivoMet;
import br.com.kantar.angariamento.model.audiencia.Audiencia;
import br.com.kantar.angariamento.model.componente.audiometrico.Canal;
import br.com.kantar.angariamento.model.componente.audiometrico.Televisor;
import br.com.kantar.angariamento.model.estatistico.Demografica;
import br.com.kantar.angariamento.model.estatistico.Peso;
import br.com.kantar.angariamento.model.target.Individuo;
import br.com.kantar.angariamento.servico.arquivo.ArquivoMetServico;
import br.com.kantar.angariamento.servico.audiometrico.TelevisoresServico;
import br.com.metodos.uteis.gerais.ServicosUteisGerais;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author eduardo
 */
public class AudienciaRepository {

    public Map entrypointAudienciaIndividual(String p_entrypoint) {

        Map var_controle_mapa_retorno = new LinkedHashMap();

        String var_controle_st_dom = "";

        Matcher var_controle_regex_basis = Pattern.compile("Z\\d{1,}\n.*\n.*\n[\\d{11}X[A|B\\d{1,}]{1,}F\n]{1,}").matcher(p_entrypoint);

        while (var_controle_regex_basis.find()) {

            String var_controle_base_r_exp = var_controle_regex_basis.group();

            Matcher var_controle_regex_get_dom = Pattern.compile("Z\\d{1,}").matcher(var_controle_base_r_exp);

            var_controle_st_dom = var_controle_regex_get_dom.find() ? var_controle_regex_get_dom.group() : "";

            Matcher var_controle_regex_get_aud = Pattern.compile("(\\d{1,}X[A|B\\d{1,}]{1,}F){1,}").matcher(var_controle_base_r_exp);

            StringBuilder var_controle_sb = new StringBuilder();

            while (var_controle_regex_get_aud.find()) {

                var_controle_sb.append(var_controle_regex_get_aud.group() + "\n");

            }

            var_controle_mapa_retorno.put(var_controle_st_dom, var_controle_sb.toString());

        }

        return var_controle_mapa_retorno;
    }

    public Multimap obterDetalhamentoAudienciaIndividualAngariada(String p_entrypoint) {

        Multimap var_controle_multimap_retorno = ArrayListMultimap.create();

        Map var_coleta_mapa_entry_point_map = entrypointAudienciaIndividual(p_entrypoint);

        for (Object l_map : var_coleta_mapa_entry_point_map.keySet()) {

            String[] var_controle_array_st_linhas = var_coleta_mapa_entry_point_map.get(l_map).toString().split("\n");

            for (String l_linhas : var_controle_array_st_linhas) {

                if (l_linhas.matches(".*\\d{1,}.*")) {

                    int var_controle_tv = Integer.parseInt(l_linhas.substring(0, 2));
                    int var_controle_canal = Integer.parseInt(l_linhas.substring(l_linhas.indexOf("X") - 5, l_linhas.indexOf("X")));

                    String var_controle_complete_key = l_map + ";" + var_controle_tv + ";" + var_controle_canal;

                    List var_controle_lista = (List) new LinhaAudiencia().transformaLinhaEmAudiencia(l_linhas);

                    var_controle_multimap_retorno.put(var_controle_complete_key, var_controle_lista);

                }

            }

        }

        return var_controle_multimap_retorno;

    }

    public List<Individuo> obterAudienciaIndividual(String p_entrypoint, int[] p_array_canais) {

        List<Individuo> var_controle_lista_retorno = new ArrayList();

        Multimap var_controle_mapa_detalhamento = obterDetalhamentoAudienciaIndividualAngariada(p_entrypoint);

        List<Afericao> var_controle_lista_afericoes_repo_temp = null;

        Map<Integer, Integer> var_controle_mapa_audiencia_concreta = null;

        Map var_controle_mapa_demografico = new DemograficaController().obterDemograficaIndividualAngariamento(p_entrypoint);

        Map var_controle_mapa_peso = new PesoController().obterPesoIndividual(p_entrypoint);

        for (Object l_detalhamento : var_controle_mapa_detalhamento.keySet()) {

            List var_controle_lista_info_detalhamento = (List) var_controle_mapa_detalhamento.get(l_detalhamento);

            for (int i = 0; i < var_controle_lista_info_detalhamento.size(); i++) {

                var_controle_lista_afericoes_repo_temp = new ArrayList();

                var_controle_mapa_audiencia_concreta = new LinkedHashMap();

                String[] var_controle_dados_array_fericao = l_detalhamento.toString().split(";");

                long var_controle_id_individuo = Long.parseLong(var_controle_dados_array_fericao[0].substring(1, var_controle_dados_array_fericao[0].length()));

                int var_controle_id_canal = Integer.parseInt(var_controle_dados_array_fericao[2]);

                if (ServicosUteisGerais.validaCanalEmArray(p_array_canais, var_controle_id_canal)) {

                    int var_controle_id_tv = Integer.parseInt(var_controle_dados_array_fericao[1]);

                    List var_controle_lista_receptor = (List) var_controle_lista_info_detalhamento.get(i);

                    List<String> var_controle_lista_conversor = (List<String>) var_controle_lista_receptor.stream().map(Object::toString).collect(Collectors.toList());

                    var_controle_lista_receptor = var_controle_lista_conversor.stream().map(Integer::parseInt).collect(Collectors.toList());

                    AtomicInteger var_controle_somador = new AtomicInteger(0);

                    var_controle_mapa_audiencia_concreta = (Map<Integer, Integer>) var_controle_lista_receptor.stream().collect(Collectors.toMap(n -> var_controle_somador.incrementAndGet(), s1 -> s1));

                    var_controle_lista_afericoes_repo_temp.add(new Afericao(new Televisor(var_controle_id_tv), new Canal(var_controle_id_canal), new Audiometria(var_controle_mapa_audiencia_concreta), "", "", null));

                    String[] var_controle_array_demo_string_format = var_controle_mapa_demografico.get(l_detalhamento.toString().substring(0, 12)).toString().replaceAll("D,", "").replaceAll("\\s", "").replaceAll("\\[|\\]", "").replaceAll("listaDemograficas=", "").replaceAll("Demografica\\{", "").replaceAll("\\}", p_entrypoint).split(",");

                    List<Integer> var_controle_demo_converter = Arrays.asList(var_controle_array_demo_string_format).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());

                    double var_controle_peso = Double.parseDouble(var_controle_mapa_peso.get(l_detalhamento.toString().substring(0, 12)).toString().replaceAll("W", ""));

                    var_controle_lista_retorno.add(new Individuo(var_controle_lista_afericoes_repo_temp, var_controle_id_individuo, new Demografica(var_controle_demo_converter), new Peso(var_controle_peso)));

                }
            }

        }

        return var_controle_lista_retorno;

    }

    public StringBuilder entrypointAudienciaDomiciliar(String pedacoArray) {

        StringBuilder ac = new StringBuilder();

        String exp1 = "\\d{11}X[A|B\\d{1,}]{1,}F";
        Matcher m = Pattern.compile(exp1).matcher(pedacoArray);

        while (m.find()) {

            ac.append(m.group() + "\n");

        }

        return ac;

    }

    public List<Afericao> obterAudienciaDomiciliar(String p_entrypoint_raw_string, int[] p_array_canais) {

        List<Afericao> var_controle_lista_afericoes_retorno = new ArrayList<>();
        String dom = p_entrypoint_raw_string.substring(0, 9);
        Map var_controle_mapa_detalhamento = new entryPointCov().getAudienciaTratada(entrypointAudienciaDomiciliar(p_entrypoint_raw_string).toString());

        for (Object l_detalhe : var_controle_mapa_detalhamento.keySet()) {

            Map<Integer, Integer> var_controle_mapa_audiencia_concreta = new LinkedHashMap();

            List var_controle_list_temp = (List) var_controle_mapa_detalhamento.get(l_detalhe);

            for (int i = 0; i < var_controle_list_temp.size(); i++) {

                var_controle_mapa_audiencia_concreta.put(i, Integer.parseInt(var_controle_list_temp.get(i).toString()));

            }

            int var_controle_canal = Integer.parseInt(l_detalhe.toString().substring(l_detalhe.toString().length() - 5, l_detalhe.toString().length()));

            if (ServicosUteisGerais.validaCanalEmArray(p_array_canais, var_controle_canal)) {
                String obterPesoDomiciliar = new PesoController().obterPesoDomiciliar(p_entrypoint_raw_string);

                var_controle_lista_afericoes_retorno.add(new Afericao(new Televisor(Integer.parseInt(l_detalhe.toString().substring(0, 2))), new Canal(var_controle_canal), new Audiometria(var_controle_mapa_audiencia_concreta), dom, "", new Peso(Double.parseDouble(obterPesoDomiciliar.replaceAll("W", "")))));

            }

        }

        return var_controle_lista_afericoes_retorno;

    }

    public boolean filtrarCanaisEmAudiencia(int[] p_array_canais, String p_entrypoint) {

        for (int x : p_array_canais) {

            String tempVar = x + "X";

            if (p_entrypoint.contains(tempVar)) {

                return true;

            }

        }

        return false;

    }

    public List<Audiencia> fornecerListaAudiencia(Map<LocalDate, File> p_contentmap, int[] p_array_canais) throws FileNotFoundException, IOException {

        List<Audiencia> var_controle_lista_retorno = new ArrayList();

        for (File l_arq : p_contentmap.values()) {

            FileInputStream var_controle_fis = new FileInputStream(l_arq);

            String var_controle_fis_as_string = IOUtils.toString(var_controle_fis, "UTF-8").replaceAll("I", "QI").replaceAll("\r\n", "\n");

            String[] var_controle_array_desmembramento = var_controle_fis_as_string.split("Q");

            for (int i = 1; i < var_controle_array_desmembramento.length; i++) {

                if (filtrarCanaisEmAudiencia(p_array_canais, var_controle_array_desmembramento[i])) {

                    var_controle_lista_retorno.add(
                            new Audiencia(
                                    (ArquivoMet) new ArquivoMetServico().retornarObjeto(var_controle_array_desmembramento[i]),
                                    new DemograficaController().obterDemograficaDomiciliarAngariamento(var_controle_array_desmembramento[i]),
                                    new TelevisoresServico().obterQtdTelevisores(var_controle_array_desmembramento[i]),
                                    new IndividuoController().obterQtdIndividuos(var_controle_array_desmembramento[i]),
                                    obterAudienciaDomiciliar(var_controle_array_desmembramento[i], p_array_canais),
                                    obterAudienciaIndividual(var_controle_array_desmembramento[i], p_array_canais)
                            )
                    );

                }

            }

        }

        return var_controle_lista_retorno;
    }

}
