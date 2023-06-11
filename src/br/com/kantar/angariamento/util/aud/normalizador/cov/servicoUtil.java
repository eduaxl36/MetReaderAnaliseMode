

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.util.aud.normalizador.cov;

import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

/**
 *
 * @author eduardo
 */
public class servicoUtil {
    
    
    
    public Map transformaAudStringToMap(String p_entrypoint)
    {
    
                String[] var_modelo_array_st = p_entrypoint.split("\n");
                List<String> var_modelo_lista = new ArrayList();

            
                for (String l_st : var_modelo_array_st) {
                    
                    if(l_st.toString().matches(".*\\d{1,}.*")){

                    var_modelo_lista.add(l_st.substring(0, 11));
                    }


                }
                Map<String, Long> var_controle_mapa_retorno
                    = var_modelo_lista.stream().collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );
    return var_controle_mapa_retorno;
    
    }
    
    
    
    
    public Multimap converteMapaEmList(String p_entrypoint_st,Multimap p_mapa)
    {
    
                Multimap var_controle_mapa_retorno = ArrayListMultimap.create();
                String var_controle_loop_st = p_mapa.get(p_entrypoint_st).toString().replaceAll("\\[|\\]", "").trim().replaceAll("\\s", "");
                     
                String var_controle_array_st[] = var_controle_loop_st.split(",");
                
                for (String l_val : var_controle_array_st) {

                    if(l_val.contains("X"))
                    {
                    
                        var_controle_mapa_retorno.put(p_entrypoint_st, new LinhaAudiencia().transformaLinhaEmAudiencia(l_val));
                    
                    
                    }
                    
                    

                }
    
    return var_controle_mapa_retorno;
    }
    
    
    public Map geraMapaFinal(Multimap p_map_entrypoint,String p_entrada_st)
    {
  
    Map var_controle_mapa_retorno = new HashMap<>();
  
    List var_controle_lista = new ArrayList();
    
    int var_controle_voltas = 1;
    
                for (Object l_mapa : p_map_entrypoint.values()) {

                    if (var_controle_voltas == 1) {

                        List var_controle_lst_tp = (List) l_mapa;

                        for (int i = 0; i < var_controle_lst_tp.size(); i++) {

                            var_controle_lista.add(var_controle_lst_tp.get(i));

                        }

                    } else {

                        List var_controle_lst_tp = (List) l_mapa;

                        for (int i = 0; i < var_controle_lst_tp.size(); i++) {

                            if ((var_controle_lst_tp.get(i).toString().contains("1"))) {

                                if (var_controle_lista.get(i).toString().equals("1")) {

                                } else {

                                    var_controle_lista.set(i, var_controle_lst_tp.get(i));
                                }

                            }

                        }

                    }

                    var_controle_voltas++;
                }



                
                
                var_controle_mapa_retorno.put(p_entrada_st, var_controle_lista);
    
    
    
    
    return var_controle_mapa_retorno;
    
    }
    
    
    
    
    
    
    
}
