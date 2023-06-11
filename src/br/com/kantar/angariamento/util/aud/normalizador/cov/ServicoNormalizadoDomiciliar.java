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


/**
 *
 * @author eduardo
 */
public class ServicoNormalizadoDomiciliar {

    
     public static Map entryPointStringParaMapaSingular(String p_entrypoint) {
        
        Map novoMapa = new HashMap<>();
        
        try {

            String[] var_controle_array_st = p_entrypoint.split("\n");
            
            List<String> var_controle_lista = new ArrayList();

            
                for (String l_st : var_controle_array_st) {
                    
                    if(l_st.toString().matches(".*\\d{1,}.*")){

                     var_controle_lista.add(l_st.substring(0, 11));
                     
                    }


                }
            
            Map<String, Long> var_controle_mapa_resultado
                    = var_controle_lista.stream().collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );

           
            
            
            for (Object l_res : var_controle_mapa_resultado.keySet()) {

              
                
                
                if (Integer.parseInt(var_controle_mapa_resultado.get(l_res).toString()) == 1) {

                    novoMapa.put(l_res,Integer.parseInt(var_controle_mapa_resultado.get(l_res).toString()));
                   
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return novoMapa;

    }
    
    public static Map entryPointStringParaMapaDuplicidade(String p_entrypoint) {
        
        
        Map var_controle_mapa_retorno = new HashMap<>();
        
        try {

            String[] p_array_st = p_entrypoint.split("\n");
            List<String> var_controle_lista = new ArrayList();
            
          
            for (String l_st : p_array_st) {
            if(l_st.toString().matches(".*\\d{1,}.*")){

             var_controle_lista.add(l_st.substring(0, 11));
        
             }
        

            }
            
            Map<String, Long> var_controle_lista_resultado
                    = var_controle_lista.stream().collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );

           
            
            
            for (Object l_res : var_controle_lista_resultado.keySet()) {

              
                if (Integer.parseInt(var_controle_lista_resultado.get(l_res).toString()) > 1) {

                    var_controle_mapa_retorno.put(l_res,Integer.parseInt(var_controle_lista_resultado.get(l_res).toString()));
                   
                }

            }
            
            
            

        } catch (Exception e) {

            e.printStackTrace();

        }

       
        
        return var_controle_mapa_retorno;

    }

    
    
    
    
    public static List recuperaAudienciaSingular(String p_entrypoint, Map p_mapa) {
        
    List var_controle_lista_retorno = new ArrayList();

          if(p_mapa.size()!=0){
        
       
        try {

            String[] var_controle_array_st = p_entrypoint.split("\n");

            for (Object l_key : p_mapa.keySet()) {

 
                for (String l_int : var_controle_array_st) {

                    if (l_int.startsWith((l_key).toString())) {

                        var_controle_lista_retorno.add(l_int);

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

          }
      
        return var_controle_lista_retorno;
    }
    
    public static List recuperaAudienciaDuplicados(String p_entrypoint, Map p_mapa) {

       List var_controle_lista = new ArrayList();  
                 
       if(p_mapa.size()!=0){
        
        try {

            String[] var_controle_array_st = p_entrypoint.split("\n");

            for (Object l_key : p_mapa.keySet()) {

 
                for (String l_int : var_controle_array_st) {

                    if (l_int.startsWith((l_key).toString())) {

                        var_controle_lista.add(l_int);

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
        }
        
        
     

        
              
        return var_controle_lista;
    }

    
 
    public static Multimap transformaAudienciaDuplicadaEmMapa(List p_lista) {

        
       Multimap var_controle_mapa_retorno = ArrayListMultimap.create();

       if(p_lista.size()!=0)
       {
       
        try {

            Set var_controle_set = new ServicosGerais().recuperaChavesSemDuplicacao(p_lista);

            for (Object l_set : var_controle_set) {

                for (Object l_list : p_lista) {

                    if (l_list.toString().startsWith(l_set.toString())) {

                        var_controle_mapa_retorno.put(l_set, l_list);
                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
       
       }
        
   

        
        return var_controle_mapa_retorno;
    }

    
    
    
    
    
     public static Multimap transformaAudienciaSingularEmMapa(List p_lista) {
         
         

        Multimap var_controle_mapa_retorno = ArrayListMultimap.create();

        
        if(p_lista.size()!=0)
       {
       
        try {

            Set var_controle_set = new ServicosGerais().recuperaChavesSemDuplicacao(p_lista);

            for (Object l_set : var_controle_set) {

                for (Object l_list : p_lista) {

                    if (l_list.toString().startsWith(l_set.toString())) {

                        var_controle_mapa_retorno.put(l_set, l_list);
                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
       
       
       
       }
        
    
                 
        
        return var_controle_mapa_retorno;
    }
    

    public static Map geracaoFinalDaAudiencia(Multimap p_mapa) {
        
              
             Map var_controle_mapa_retorno = new HashMap<>();
        try {

     
            for (Object l_map : p_mapa.keySet()) {

                Multimap var_controle_mapa_dup = ArrayListMultimap.create();
                
                String var_controle_st = p_mapa.get(l_map).toString().replaceAll("\\[|\\]", "").trim().replaceAll("\\s", "");
                         
                String var_controle_array_st[] = var_controle_st.split(",");
                
              

                for (String l_array_st : var_controle_array_st) {

                    if(l_array_st.contains("X")){
                    var_controle_mapa_dup.put(l_map, new LinhaAudiencia().transformaLinhaEmAudiencia(l_array_st));
                    
                    
                    }
                    
                }


                
                List var_controle_lista = new ArrayList();
                
                int var_controle_volta = 1;
                
                
                for (Object l_mapa : var_controle_mapa_dup.values()) {

                    if (var_controle_volta == 1) {

                        List listamteporaria = (List) l_mapa;

                        for (int i = 0; i < listamteporaria.size(); i++) {

                            var_controle_lista.add(listamteporaria.get(i));

                        }

                    } else {

                        List listamteporaria = (List) l_mapa;

                        for (int i = 0; i < listamteporaria.size(); i++) {

                            if ((listamteporaria.get(i).toString().contains("1"))) {

                                if (var_controle_lista.get(i).toString().equals("1")) {

                                } else {

                                    var_controle_lista.set(i, listamteporaria.get(i));
                                }

                            }

                        }

                    }

                    var_controle_volta++;
                }



                
                
                var_controle_mapa_retorno.put(l_map, var_controle_lista);
                
  
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_mapa_retorno;
    } 


    
    
    
    
    
    
    
    
    
    
    
    
    
  public static Map getAudienciaTratada(String p_entrypoint){

      try {
          
            Map var_controle_mapa_duplo = geracaoFinalDaAudiencia(
            transformaAudienciaDuplicadaEmMapa(
            recuperaAudienciaDuplicados(
            p_entrypoint, entryPointStringParaMapaDuplicidade(p_entrypoint))));

            Map var_controle_mapa_singular = geracaoFinalDaAudiencia(
            transformaAudienciaSingularEmMapa(
            recuperaAudienciaSingular(
            p_entrypoint, entryPointStringParaMapaSingular(p_entrypoint))));  
            
            var_controle_mapa_duplo.putAll(var_controle_mapa_singular);
            
            return var_controle_mapa_duplo;
            
           
      } catch (Exception e) {
      
      e.printStackTrace();
      
      }
      
      
      return null;


}
  
    
    
    
    public static void main(String[] args) {
        
        Map s = getAudienciaTratada("01000000006XA34B48A204B66A199B39A79B193A2B21A555F\n" +
"01000000004XA82B147A123B199A39B5A37B37A193B2A22B36A518F\n" +
"01000000358XA885B1A36B58A460F\n" +
"02000000018XA16B128A65B41A38B14A78B9A289B70A22B247A423F\n" +
"02000000992XA636B2A802F\n" +
"02000000004XA144B18A244B38A996F\n" +
"02000000006XA389B17A1034F\n" +
"02000000001XA630B6A804F");
       
        
        for(Object x:s.keySet())
        {
        
       
            System.out.println(x);
            
        
        
                           
        
        }
        
        
    }
    
}






