/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.analise.dot.met;

import br.com.kantar.angariamento.controller.audiencia.ControllerAudiencia;
import br.com.kantar.angariamento.model.abstrato.DOTServico;
import br.com.kantar.angariamento.model.afericao.Afericao;
import br.com.kantar.angariamento.model.target.Individuo;
import br.com.kantar.angariamento.model.audiencia.Audiencia;
import br.com.kantar.dot.controller.estatistico.DemograficaDotController;
import br.com.kantar.dot.controller.estatistico.PesoDotController;
import com.google.common.collect.*;
import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 *
 * @author eduardo
 */
public class DOTIndividual extends DOTServico {

    private long id_domicilio;
    private long id_individuo;

    public DOTIndividual() {
    }

    public DOTIndividual(int[] p_array_canais, int p_medicaoInicial, int p_medicaoFinal, List<Audiencia> p_lista) {
        super(p_array_canais, p_medicaoInicial, p_medicaoFinal, p_lista);
    }

    public DOTIndividual(long id_domicilio, int canalSingular, int[] ncanal, LocalDate data, int total, List<Integer> demografica, double peso, int tv, int medicaoInicial, int medicaoFinal, List<Audiencia> lstAud, long id_individuo) {
        super(canalSingular, ncanal, data, total, demografica, peso, tv, medicaoInicial, medicaoFinal, lstAud);
        this.id_domicilio = id_domicilio;
        this.id_individuo = id_individuo;
    }

    public DOTIndividual(int canalSingular, long id_domicilio, long id_individuo, int tv, LocalDate data, int total, List<Integer> demografica, double peso) {
        super(canalSingular, tv, data, total, demografica, peso);
        this.id_domicilio = id_domicilio;
        this.id_individuo = id_individuo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DOTDomiciliar{id_domicilio=").append(id_domicilio);
        sb.append('}');
        return sb.toString();
    }

    public long getId_domicilio() {
        return id_domicilio;
    }

    public void setId_domicilio(int id_domicilio) {
        this.id_domicilio = id_domicilio;
    }

    public long getId_individuo() {
        return id_individuo;
    }

    public void setId_individuo(int id_individuo) {
        this.id_individuo = id_individuo;
    }

    @Override
    public int obterTotal(List<? extends DOTServico> p_lista, Long p_ind, LocalDate p_data, int p_canal, int p_tv) {

        List<DOTIndividual> var_controle_lista_retorno = (List<DOTIndividual>) p_lista;

        try {

            int var_controle_total = var_controle_lista_retorno.stream()
                    .filter(x -> x.getId_individuo() == p_ind)
                    .filter(x -> x.getCanalSingular() == p_canal)
                    .filter(x -> x.getTv() == p_tv)
                    .filter(x -> x.getData().equals(p_data))
                    .collect(Collectors.summingInt(x -> x.getTotal()));

            return var_controle_total;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    @Override
    public List<? extends DOTServico> criarObjetoDotPorId(List<? extends DOTServico> p_lista, long p_dom, LocalDate p_data, int p_array_canais[]) {

        DOTIndividual var_controle_obj = null;
        List<DOTIndividual> var_controle_lista = (List<DOTIndividual>) p_lista;
        List<DOTIndividual> var_controle_lista_retorno = new ArrayList<>();
        Set var_controle_remover_du_dem = new LinkedHashSet();

        PesoDotController var_controle_angariador_peso_individual = new PesoDotController();

        try {

            int var_controle_tv = 0;

            for (int l_canal : p_array_canais) {

                for (int i = 1; i <= 8; i++) {

                    var_controle_tv = i;

                    Long var_controle_n_dom = Long.parseLong(String.valueOf(p_dom).substring(0, 8));

                    Long var_controle_n_ind = p_dom;

                    int var_controle_total = obterTotal(var_controle_lista, var_controle_n_ind, p_data, l_canal, var_controle_tv);

                    var_controle_obj = new DOTIndividual(
                            l_canal,
                            var_controle_n_dom,
                            var_controle_n_ind,
                            var_controle_tv,
                            p_data,
                            var_controle_total, new DemograficaDotController().obterDemograficaIndividualServicoDOT(var_controle_n_ind, p_lista), var_controle_angariador_peso_individual.obterPesoIndividualServicoDot(var_controle_n_ind, p_lista).orElse(0.0)
                    );

                    var_controle_remover_du_dem.add(new DemograficaDotController().obterDemograficaIndividualServicoDOT(var_controle_n_ind, p_lista));
                    
                    
                    var_controle_lista_retorno.add(var_controle_obj);

                }

            }

            return var_controle_lista_retorno;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return var_controle_lista_retorno;
    }

    @Override
    public List<DOTIndividual> geraObjetoDot(int[] p_array_canais, int p_minutoInicial, int p_minutoFinal) {

        List<DOTIndividual> var_controle_lista_retorno = new ArrayList();

        try {

            DOTIndividual var_controle_objeto = null;

            List<Audiencia> var_controle_lista_audiencia = getLista_obj_geral_audiencia();

            for (Audiencia l_aud : var_controle_lista_audiencia) {

                for (Individuo l_ind : l_aud.getLista_afericao_individuo()) {

                    for (Afericao l_afericao : l_ind.getAfericao()) {

                        LocalDate var_controle_data = l_aud.getInfo_audiometrica().getData();

                        int var_controle_total = l_afericao.getSintonia().getSintonias().entrySet().stream()
                                .filter(map -> map.getKey() >= p_minutoInicial && map.getKey() < p_minutoFinal)
                                .collect(Collectors.summingInt(x -> x.getValue()));
                        
                      
                        
                        long var_controle_id_ind = l_ind.getId();

                        var_controle_objeto = new DOTIndividual(
                                l_aud.getInfo_domiciliares().getId(),
                                l_afericao.getCanal().getId(),
                                getNcanal(),
                                var_controle_data,
                                var_controle_total,
                                l_ind.getDemografica().getListaDemograficas(),
                                l_ind.getPeso().getPeso(),
                                l_afericao.getTelevisor().getId(),
                                getMedicaoInicial(),
                                getMedicaoFinal(),
                                getLista_obj_geral_audiencia(),
                                var_controle_id_ind
                        );

                        var_controle_lista_retorno.add(var_controle_objeto);

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_lista_retorno;

    }

    @Override
    public Set<Long> obterListaId(List<? extends DOTServico> p_lista) {

        List<DOTIndividual> var_controle_lista = (List<DOTIndividual>) p_lista;

        Set var_controle_set_retorno = null;

        try {

            var_controle_set_retorno = var_controle_lista.stream()
                    .map(x -> x.getId_individuo())
                    .collect(Collectors.toSet());

            return var_controle_set_retorno;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_set_retorno;

    }

    @Override
    public List<? extends DOTServico> criaListaGeralObjetos(List<? extends DOTServico> p_lista, int p_array_canais[]) {

        List<DOTIndividual> var_controle_lista_retorno = new ArrayList();

        Set var_controle_set_remover_duplicatas = new LinkedHashSet();

        List<DOTIndividual> var_controle_lista = (List<DOTIndividual>) p_lista;

        Set<LocalDate> var_controle_lista_datas = obterListaDatas(var_controle_lista);

        Set<Long> var_controle_lista_indi = obterListaId(var_controle_lista);

        for (Long l_n_ind : var_controle_lista_indi) {

            for (LocalDate l_data : var_controle_lista_datas) {

                for (DOTIndividual di : var_controle_lista) {

                    if (di.getId_individuo() == l_n_ind) {

                        if (!(var_controle_set_remover_duplicatas.contains(l_data + "" + l_n_ind))) {

                            var_controle_lista_retorno.addAll((Collection<? extends DOTIndividual>) criarObjetoDotPorId(var_controle_lista, l_n_ind, l_data, p_array_canais));
                        }

                        var_controle_set_remover_duplicatas.add(l_data + "" + l_n_ind);

                    }

                }

            }

        }

        return var_controle_lista_retorno;
    }

    @Override
    public Multimap cronogramaDeImpressao() {

        Multimap myMultimap = ArrayListMultimap.create();

        try {

            List<DOTIndividual> temp1 = geraObjetoDot(getNcanal(), getMedicaoInicial(), getMedicaoFinal());
            List<DOTIndividual> dotlist = (List<DOTIndividual>) criaListaGeralObjetos(temp1, getNcanal());

            for (DOTIndividual dot : dotlist) {

                String demograficaTratada = ""+dot.getDemografica().get(0);
                
         //       .trim().replaceAll("\\[|\\]", "").trim().replaceAll(",", ";").trim().replaceAll("\\s", "").replaceAll(",", ";").trim().replaceAll("\\s", "");

                myMultimap.put(dot.getId_individuo() + ";" + dot.getCanalSingular() + ";" + dot.getTv() + ";" + demograficaTratada + ";" + dot.getPeso() + ";", dot.getTotal());

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        return myMultimap;
    }

    @Override
    public Set<LocalDate> obterListaDatas(List<? extends DOTServico> p_lista) {
        
        Set var_controle_set_datas_retorno=null;
        try {
            List<DOTIndividual> va_controle_lista = (List<DOTIndividual>) p_lista;
            
            var_controle_set_datas_retorno = va_controle_lista.stream()
            .map(x -> x.getData())
            .collect(Collectors.toSet());

            return var_controle_set_datas_retorno;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_set_datas_retorno;
    }

    
    
    public static void main(String[] args) throws Exception {

        Audiencia aud = new Audiencia();
          
       File[] arquivos = new File[]{new File("sourcefiles/20211128.MET")};
    
        List<Audiencia> lstAud = new ControllerAudiencia().fornecerListaAudiencia(new DOTDomiciliar().geraCaminhosOrdenadosPorData(arquivos), new int[]{100});

        DOTIndividual doti = new DOTIndividual(new int[]{100}, 1, 1001, lstAud);

        Multimap mp = doti.cronogramaDeImpressao();

        for (Object x : mp.keySet()) {

            System.out.println(x + "" + mp.get(x));

        }

    }

}
