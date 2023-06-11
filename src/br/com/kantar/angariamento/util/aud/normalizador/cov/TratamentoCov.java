/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.util.aud.normalizador.cov;

import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import br.com.angariamento.servicos.linhas.gerais.ServicosGerais;
import static br.com.kantar.angariamento.util.aud.normalizador.cov.TratamentoCovUtil.audienciaParaListaIndividual;
import static br.com.kantar.angariamento.util.aud.normalizador.cov.TratamentoCovUtil.converteMapaDeAudienciaCruaEmConcreta;
import static br.com.kantar.angariamento.util.aud.normalizador.cov.TratamentoCovUtil.retornaModalidade;
import br.com.kantar.angariamento.util.aud.normalizador.cov.exceptions.MapaVazioException;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author eduardo
 */
public class TratamentoCov {

    /**
     * Principal objetivo deste metodo é conveter uma String de audiencia crua
     * em um mapa que contem como chave tv e canal que se repetem mais de uma
     * vez.
     *
     * @param EntradaAudienciaCrua --> string de audiecias
     * 01000000184XA96B11A93B74A1166F,01000000184XA96B31A93B74A1166F
     * @return retorma mapa com chave (tv,canal) e valor (repeticoes) ex:
     * 01000000184=2
     */
    public Map mapeiaRepeticoesDeAudienciaMaiorQueUm(String EntradaAudienciaCrua) {

        Map Audiencias = new HashMap<>();

        Map AudienciasDTO = new servicoUtil().transformaAudStringToMap(EntradaAudienciaCrua);

        for (Object Audiencia : AudienciasDTO.keySet()) {

            if (retornaModalidade(EntradaAudienciaCrua) > 1) {

                Audiencias.put(Audiencia, Integer.parseInt(AudienciasDTO.get(Audiencia).toString()));

            }

        }

        return Audiencias;

    }

  /**
     * Atraves da chave que contem a duplicação (MapaRepeticoes), o metodo le a
     * String (EntradaAudienciaCrua) e devolve uma lista com a audiencia
     * duplicada baseada na chave duplicada
     *
     * recebe -> {01000000100=3} ,
     *
     * 01000000100XA788B2A650F 01000000001XA790B2A68B2A578F
     * 01000000006XA792B1A48B19A2B13A565F 01000000100XA793B5A642F
     * 01000000100XA798B14A628F 01000000045XA812B28A600F
     * 01000000004XA840B1A34B266A299F
     *
     * devolve --> [01000000100XA788B2A650F, 01000000100XA793B5A642F,
     * 01000000100XA798B14A628F]
     *
     *
     * @param EntradaAudienciaCrua
     * @param MapaRepeticoes
     * @return List
     */
    
    public List insereAudienciaMapeadaEmLista(String EntradaAudienciaCrua, Map MapaRepeticoes) {

        List Audiencias = new ArrayList();

        String[] AudienciasFatiadas = EntradaAudienciaCrua.split("\n");

        for (Object AudienciaMapeada : MapaRepeticoes.keySet()) {

            for (String AudienciaFatiada : AudienciasFatiadas) {

                if (AudienciaFatiada.startsWith((AudienciaMapeada).toString())) {

                    Audiencias.add(AudienciaFatiada);

                }

            }

        }

        return Audiencias;
    }

     /**
     * Metodo desmembra em chave (canal+tv)e valor (audiencia crua e agrupada)
     *
     * @param Audiencias
     * 01000000184XA96B11A93B74A1166F,01000000184XA95B11A93B74A1166F
     * @return 01000000184 = [A96B11A93B74A1166F,01000000184XA95B11A93B74A1166F]
     */
    public Multimap agrupaAudienciaPorTvCanal(List Audiencias) {

        Multimap AudienciasMapeadas = ArrayListMultimap.create();

        if (!Audiencias.isEmpty()) {

            Set AudienciasNaoDuplicadas = new ServicosGerais().recuperaChavesSemDuplicacao(Audiencias);

            for (Object AudienciaUnica : AudienciasNaoDuplicadas) {

                for (Object Audiencia : Audiencias) {

                    if (Audiencia.toString().startsWith(AudienciaUnica.toString())) {

                        AudienciasMapeadas.put(AudienciaUnica, Audiencia);
                    }

                }

            }

        }
        return AudienciasMapeadas;
    }

   /**
     * Metodo recebe valor --> 010000000109X [A200b400....] e converte para -->
     * 010000000109X [0,0,0,0,0,0,1,......]
     *
     * @param AudienciasAgrupadas
     * @return Map
     */
    public Map obterAudienciasDesagrupadasParaValoresDoMapa(Multimap AudienciasAgrupadas) {

        Map AudienciasDesagrupadas = new HashMap<>();

        for (Object AudienciaAgrupada : AudienciasAgrupadas.keySet()) {

            Multimap ReceptorMapeadoAudiencias = converteMapaDeAudienciaCruaEmConcreta(AudienciasAgrupadas, AudienciaAgrupada);

            List audienciasIndexadas = audienciaParaListaIndividual(ReceptorMapeadoAudiencias);

            AudienciasDesagrupadas.put(AudienciaAgrupada, audienciasIndexadas);

        }

        return AudienciasDesagrupadas;
    }

    
    /**
     * 
     * @param EntradaAudienciaCrua
     * @return 
     */
    public Map obterAudienciaTratada(String EntradaAudienciaCrua) {

        Map var_controle_mp_duplicado = null;

        var_controle_mp_duplicado = obterAudienciasDesagrupadasParaValoresDoMapa(
                agrupaAudienciaPorTvCanal(
                        insereAudienciaMapeadaEmLista(
                                EntradaAudienciaCrua, mapeiaRepeticoesDeAudienciaMaiorQueUm(EntradaAudienciaCrua))));

        return var_controle_mp_duplicado;

    }

}
