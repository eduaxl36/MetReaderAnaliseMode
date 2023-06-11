/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.util.aud.normalizador.cov;


import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import br.com.angariamento.servicos.linhas.gerais.ServicosGerais;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class Singular {
    

     public  Map entryPointMapeamentoSingular(String p_entrypoint) {
        
        Map var_controle_mapa_retorno = new HashMap<>();
        
        try {

          Map var_controle_rs = new servicoUtil(). transformaAudStringToMap(p_entrypoint);
            

            for (Object l_rs : var_controle_rs.keySet()) {

              
                
                
                if (Integer.parseInt(var_controle_rs.get(l_rs).toString()) == 1) {

                    var_controle_mapa_retorno.put(l_rs,Integer.parseInt(var_controle_rs.get(l_rs).toString()));
                   
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return var_controle_mapa_retorno;

    }
    
  
    
    
    
    
    public static List converteChaveDoMapaEmLista(String p_entrypoint, Map p_mapa) {
                List listaTemporaria = new ArrayList();

          if(p_mapa.size()!=0){
        
       
        try {

            String[] p_array_st = p_entrypoint.split("\n");

            for (Object l_key : p_mapa.keySet()) {

 
                for (String l_int : p_array_st) {

                    if (l_int.startsWith((l_key).toString())) {

                        listaTemporaria.add(l_int);

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

          }
      
        return listaTemporaria;
    }
    

    
 
    
    
    
    
    
     public static Multimap transformaAudienciaSingularEmMapa(List p_lista) {

        Multimap var_controle_mapa_retorno = ArrayListMultimap.create();

        
        if(p_lista.size()!=0)
       {
            
        try {

            Set var_controle_s_duplicidade = new ServicosGerais().recuperaChavesSemDuplicacao(p_lista);

            for (Object l_dp : var_controle_s_duplicidade) {

                for (Object l_lst : p_lista) {

                    if (l_lst.toString().startsWith(l_dp.toString())) {

                        var_controle_mapa_retorno.put(l_dp, l_lst);
                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
       
       
       
       }
        
    
        return var_controle_mapa_retorno;
    }
    

    public  Map geracaoFinalDaAudiencia(Multimap p_mapa) {
        
        Map var_controle_mapa_retorno = new HashMap<>();
        
        try {

     
            for (Object l_mapa : p_mapa.keySet()) {

                Multimap var_controle_mp_duplo = ArrayListMultimap.create();

                String var_controle_vl_loop = p_mapa.get(l_mapa).toString().replaceAll("\\[|\\]", "").trim().replaceAll("\\s", "");

                String p_array_val[] = var_controle_vl_loop.split(",");
                
                for (String l_val : p_array_val) {

                    if(l_val.contains("X")){
                        
                    var_controle_mp_duplo.put(l_mapa, new LinhaAudiencia().transformaLinhaEmAudiencia(l_val));
                    
                    }
                    
                }

                List var_controle_lista_adicional = new ArrayList();
                int var_controle_volta = 1;
                
                for (Object l_valmap : var_controle_mp_duplo.values()) {

                    if (var_controle_volta == 1) {

                        List var_controle_lista_temp = (List) l_valmap;

                        for (int i = 0; i < var_controle_lista_temp.size(); i++) {

                            var_controle_lista_adicional.add(var_controle_lista_temp.get(i));

                        }

                    } else {

                        List listamteporaria = (List) l_valmap;

                        for (int i = 0; i < listamteporaria.size(); i++) {

                            if ((listamteporaria.get(i).toString().contains("1"))) {

                                if (var_controle_lista_adicional.get(i).toString().equals("1")) {

                                } else {

                                    var_controle_lista_adicional.set(i, listamteporaria.get(i));
                                }

                            }

                        }

                    }

                    var_controle_volta++;
                }



                
                
                var_controle_mapa_retorno.put(l_mapa, var_controle_lista_adicional);
                
  
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_mapa_retorno;
    } 


    
    
   
  public  Map obterAudienciaTratada(String p_entrypoint){
      Map var_controle_mapa_singular=null;
      
      try {
          

            var_controle_mapa_singular = geracaoFinalDaAudiencia(
            transformaAudienciaSingularEmMapa(
            converteChaveDoMapaEmLista(
            p_entrypoint, entryPointMapeamentoSingular(p_entrypoint))));  

            
            return var_controle_mapa_singular;
            
           
      } catch (Exception e) {
      
      e.printStackTrace();
      
      }
      
      
      return var_controle_mapa_singular;


}
    
  
    
}
