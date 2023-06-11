/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.analise.dot.met;
 
import br.com.kantar.angariamento.controller.audiencia.ControllerAudiencia;
import br.com.kantar.angariamento.model.abstrato.DOTServico;
import br.com.kantar.angariamento.model.afericao.Afericao;
import br.com.kantar.angariamento.model.audiencia.Audiencia;
import br.com.kantar.angariamento.model.estatistico.Peso;
import br.com.kantar.dot.controller.estatistico.DemograficaDotController;
import br.com.kantar.dot.controller.estatistico.PesoDotController;
import br.com.metodos.uteis.gerais.ServicosUteisGerais;
import com.google.common.collect.*;
import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

/**
 *
 * @author eduardo
 */
public class DOTDomiciliar extends DOTServico {

    private long id_domicilio;

    public DOTDomiciliar() {}

    
    public DOTDomiciliar(int[] p_arraycanal, int p_medicaoInicial, int p_medicaoFinal, List<Audiencia> p_list_geral_audiencia) {
        super(p_arraycanal, p_medicaoInicial, p_medicaoFinal, p_list_geral_audiencia);
    }

    
    public DOTDomiciliar(long p_id_domicilio, int p_canal, int[] p_arraycanal, LocalDate p_data, int p_total, List<Integer> p_lista_demograficas, double p_peso, int p_tv, int p_medicaoInicial, int p_medicaoFinal, List<Audiencia> p_list_geral_audiencia) {
        super(p_canal, p_arraycanal, p_data, p_total, p_lista_demograficas, p_peso, p_tv, p_medicaoInicial, p_medicaoFinal, p_list_geral_audiencia);
        this.id_domicilio = p_id_domicilio;
    }

 
    public DOTDomiciliar(int p_canal, long p_id_domicilio, int p_tv, LocalDate p_data, int p_total, List<Integer> p_lista_demograficas, double p_peso) {
        super(p_canal, p_tv, p_data, p_total, p_lista_demograficas, p_peso);
        this.id_domicilio = p_id_domicilio;
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

    public void setId_domicilio(long id_domicilio) {
        this.id_domicilio = id_domicilio;
    }

 
    @Override
    public int obterTotal(List<? extends DOTServico> p_lista,Long p_dom, LocalDate p_data, int p_canal, int p_tv) {
        int total=0;
        List<DOTDomiciliar>var_controle_lista = (List<DOTDomiciliar>) p_lista;
        try {

             total = 
                    
                    var_controle_lista.stream()
                    .filter(x -> x.getId_domicilio() == p_dom)
                    .filter(x -> x.getCanalSingular() == p_canal)
                    .filter(x -> x.getTv() == p_tv)
                    .filter(x -> x.getData().equals(p_data))
                    .collect(Collectors.summingInt(x -> x.getTotal()));

            return total;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return total;

    }
       
   
    @Override
    public List<? extends DOTServico> criarObjetoDotPorId(List<? extends DOTServico > p_lista, long p_id, LocalDate p_data, int p_a_canais[]) {

        /// variavel dotdomicilar que recebera os dados temporariamente para serem inseridos na lista de dotdoms
        DOTDomiciliar var_controle_obj = null;
        
        /// casting de lista, supertipo para classe concreta
        
        List<DOTDomiciliar> var_controle_lista = (List<DOTDomiciliar>) p_lista;
        
        /// lista criada afim de servir de retorno após a inserção dos dados
        
        List<DOTDomiciliar> var_controle_lista_retorno = new ArrayList<>();

        // objeto criado e instanciado para obtencao do peso domiciliar
        
        PesoDotController var_controle_angariador_peso_domiciliar = new PesoDotController();

        try {

            int var_controle_tv = 0;

            for (int l_canal : p_a_canais) {

                for (int i = 1; i <= 8; i++) {

                    var_controle_tv = i;

                            int var_controle_soma_audiencia = obterTotal(var_controle_lista, p_id, p_data, l_canal, var_controle_tv);
                            var_controle_obj = new DOTDomiciliar(
                            l_canal,
                            p_id,
                            var_controle_tv,
                            p_data,
                            var_controle_soma_audiencia, new DemograficaDotController().obterDemograficaDomiciliarServicoDOT(p_id, p_lista),var_controle_angariador_peso_domiciliar.obterPesoDomiciliarServicoDot(p_id, var_controle_lista).orElse(0.0)
                    );

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
    public List<DOTDomiciliar> geraObjetoDot( int[] p_array_canais, int p_minutoInicial, int p_minutoFinal) {

        List<DOTDomiciliar> var_controle_lista_retorno = new ArrayList();
        
        DOTDomiciliar var_controle_obj = null;
        
        try {

            for (Audiencia l_dados_audiencia : getLista_obj_geral_audiencia()) {

                setId_domicilio(l_dados_audiencia.getInfo_domiciliares().getId());

                List<Afericao> var_controle_lista_afericao = l_dados_audiencia.getLista_afericao_domiciliar();

                for (Afericao l_dados_afericao : var_controle_lista_afericao) {

                    int var_controle_l_canal = l_dados_afericao.getCanal().getId();

                    if (ServicosUteisGerais.validaCanalEmArray(p_array_canais, var_controle_l_canal)) {

                        LocalDate var_controle_l_data = l_dados_audiencia.getInfo_audiometrica().getData();

                        int var_controle_total = l_dados_afericao.getSintonia().getSintonias().entrySet().stream()
                                .filter(map -> map.getKey() >= p_minutoInicial && map.getKey() < p_minutoFinal)
                                .collect(Collectors.summingInt(x -> x.getValue()));

                                var_controle_obj = new DOTDomiciliar(
                                getId_domicilio(),
                                var_controle_l_canal,
                                getNcanal(),
                                var_controle_l_data,
                                var_controle_total,
                                l_dados_audiencia.getInfo_domiciliares().getDemografica().getListaDemograficas(),
                                l_dados_audiencia.getInfo_domiciliares().getPeso().getPeso(),
                                l_dados_afericao.getTelevisor().getId(),
                                getMedicaoInicial(),
                                getMedicaoFinal(),
                                getLista_obj_geral_audiencia()
                        );

                        var_controle_lista_retorno.add(var_controle_obj);

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

        List<DOTDomiciliar> var_controle_lista = (List<DOTDomiciliar>) p_lista;
        Set var_controle_set_retorno=null;
        
        try {

                     var_controle_set_retorno = var_controle_lista.stream()
                    .map(x -> x.getId_domicilio())
                    .collect(Collectors.toSet());

            return var_controle_set_retorno;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_set_retorno;
    }


    @Override
    public List<? extends DOTServico> criaListaGeralObjetos(List<? extends DOTServico> p_lista_generica, int p_array_canais[]) {
        
        
        List<DOTDomiciliar> p_lista_convertida_parametro = (List<DOTDomiciliar>) p_lista_generica;
        List<DOTDomiciliar> p_lista_dom_retorno = new ArrayList();
        Set<LocalDate> var_controle_lista_datas = obterListaDatas(p_lista_convertida_parametro);

        
        for (LocalDate l_data : var_controle_lista_datas) {

            Set<Long> var_controle_lista_dom = obterListaId(p_lista_convertida_parametro);

            for (Long l_dom : var_controle_lista_dom) {

                p_lista_dom_retorno.addAll((List<DOTDomiciliar>)criarObjetoDotPorId(p_lista_convertida_parametro, l_dom, l_data, p_array_canais));

            }

        }

        return p_lista_dom_retorno;
    }

    
    @Override
    public Multimap cronogramaDeImpressao() {

        Multimap var_controle_mapa_agregacao = ArrayListMultimap.create();

        try {

            List<DOTDomiciliar> var_controle_domiciliar_temp = geraObjetoDot(getNcanal(), getMedicaoInicial(), getMedicaoFinal());
            List<DOTDomiciliar> var_controle_domiciliar_consolidado = (List<DOTDomiciliar>) criaListaGeralObjetos(var_controle_domiciliar_temp, getNcanal());

            for (DOTDomiciliar l_dados_domiciliar : var_controle_domiciliar_consolidado) 
            {
                
            String var_controle_dem_as_string =""+l_dados_domiciliar.getDemografica().get(0);

                var_controle_mapa_agregacao.put(l_dados_domiciliar.getId_domicilio() + ";" 
              + l_dados_domiciliar.getCanalSingular() + ";" 
              + l_dados_domiciliar.getTv() + ";" 
              + var_controle_dem_as_string.trim().replaceAll("\\[|\\]", "").trim().replaceAll(",", ";").trim().replaceAll("\\s", "")+";"
              + l_dados_domiciliar.getPeso(),String.valueOf(l_dados_domiciliar.getTotal()));

                
            }

        } catch (Exception e) {

            e.printStackTrace();
            
        }
        
        
        return var_controle_mapa_agregacao;
    }

   

    public static void main(String[] args) throws Exception {

        
        File[] arquivos = new File[]{new File("sourcefiles/20211128.MET"),new File("sourcefiles/20211129.MET"),new File("sourcefiles/20211130.MET")};

        List<Audiencia> lstAud = new ControllerAudiencia().fornecerListaAudiencia(new DOTDomiciliar().geraCaminhosOrdenadosPorData(arquivos),new int[]{0});

//        lstAud.forEach(x->{
//        
//        
//           x.getLista_afericao_domiciliar().forEach(d->{
//           
//           
//               System.out.println(d.getCanal().getId());
//           
//           });
//        
//        
//        });
        
        


//
//        Map<Integer, Integer> mapa = new HashMap<>();
//        Map<Integer, List<Object>> segundoMapa = new HashMap<>();
//
//        lstAud.forEach(audiencia -> {
//
//            audiencia.getLista_afericao_domiciliar().stream().filter(x->x.getCanal().getId()==4).collect(Collectors.toList()).forEach(afericao -> {
//
//                Peso numeroPeso = afericao.getPeso();
//
//                afericao.getSintonia().getSintonias().forEach((minuto, audienciaMinuto) -> {
//
//                    if (audienciaMinuto == 1) {
//                        String numeroDomicilio = afericao.getNumeroDomicilio();
//                        mapa.put(minuto, mapa.getOrDefault(minuto, 0) + 1);
//
//                        segundoMapa.computeIfAbsent(minuto, k -> new ArrayList<>())
//                                .add(numeroDomicilio + "" + numeroPeso);
//
//                    }
//                });
//            });
//        });
//        System.out.println(segundoMapa);
//        System.out.println(mapa);
//

//
//        lstAud.forEach(x->{
//        
//        
//            System.out.println(x.getInfo_domiciliares());
//        
//        
//        });
//        
        
        
        DOTDomiciliar dot = new DOTDomiciliar(new int[]{100}, 1, 49, lstAud);


        Multimap mapa = dot.cronogramaDeImpressao();
//        
        for (Object x : mapa.keySet()) {

            System.out.println(x + ";" + mapa.get(x).toString().replaceAll("\\[|\\]", "").replaceAll(",", ";").replaceAll("\\s", ""));

        }



//
//        Audiencia aud = new Audiencia();
//        File[] arquivos = new File[]{new File("C:\\teste\\testedot\\20220120.MET"), new File("C:\\teste\\testedot\\20220121.MET"), new File("C:\\teste\\testedot\\20220122.MET"),new File("C:\\teste\\testedot\\20220123.MET"),new File("C:\\teste\\testedot\\20220124.MET")};
//
//        List<Audiencia> lstAud = aud.fornecerListaAudiencia(new DOTDomiciliar().geraCaminhosOrdenadosPorData(arquivos),new int[]{502});
//
//        DOTDomiciliar dot = new DOTDomiciliar(new int[]{502}, 0, 1000, lstAud);
//
////
//        List<DOTDomiciliar> var_controle_domiciliar_temp = dot.geraObjetoDot(new int[]{502}, 0, 1000);
//        List<DOTDomiciliar> var_controle_domiciliar_consolidado = (List<DOTDomiciliar>) dot.criaListaGeralObjetos(var_controle_domiciliar_temp, new int[]{100});
//
//
//
//            for (DOTDomiciliar l_dados_domiciliar : var_controle_domiciliar_consolidado) 
//            {
//            
//            
//                System.out.println(StringUtils.countOccurrencesOf(l_dados_domiciliar.getDemografica().toString(), ","));
//                
//            
//            
//            }
//


//

//
//        new DOTDomiciliar().geraCaminhosOrdenadosPorData(arquivos).forEach((x,y)->{
//        
//        
//            System.out.println(x);
//        
//        
//        });
        
        
        
//        System.out.println(new DOTDomiciliar().obterCabecalho( new DOTDomiciliar().geraCaminhosOrdenadosPorData(arquivos),10));
//        



    }




}