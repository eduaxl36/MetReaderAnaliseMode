/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metreader2022;

import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import br.com.angariamento.servicos.linhas.gerais.ServicosGerais;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
public class MetReader2022 {

    
    public static Map entryPointStringParaMapaSingular(String val) {
        
        Map novoMapa = new HashMap<>();
        
        try {

            String[] array = val.split("\n");
            List<String> angariarDuplos = new ArrayList();
            
            
            

            for (String x : array) {

                angariarDuplos.add(x.substring(0, 11));

            }
            
            Map<String, Long> result
                    = angariarDuplos.stream().collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );

           
            
            
            for (Object f : result.keySet()) {

              
                
                
                if (Integer.parseInt(result.get(f).toString()) == 1) {

                    novoMapa.put(f,Integer.parseInt(result.get(f).toString()));
                   
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return novoMapa;

    }
    
    public static Map entryPointStringParaMapaDuplicidade(String val) {
        
        Map novoMapa = new HashMap<>();
        
        try {

            String[] array = val.split("\n");
            List<String> angariarDuplos = new ArrayList();
            
            
            

            for (String x : array) {

                angariarDuplos.add(x.substring(0, 11));

            }
            
            Map<String, Long> result
                    = angariarDuplos.stream().collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );

           
            
            
            for (Object f : result.keySet()) {

              
                
                
                if (Integer.parseInt(result.get(f).toString()) > 1) {

                    novoMapa.put(f,Integer.parseInt(result.get(f).toString()));
                   
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return novoMapa;

    }

    
    
    
    
    public static List recuperaAudienciaSingular(String ocorrenciaDeAudiencia, Map ocorrenciaSingular) {
              List listaTemporaria = new ArrayList();

          if(ocorrenciaSingular.size()!=0){
        
        
        
  

        try {

            String[] array = ocorrenciaDeAudiencia.split("\n");

            for (Object chave : ocorrenciaSingular.keySet()) {

 
                for (String interno : array) {

                    if (interno.startsWith((chave).toString())) {

                        listaTemporaria.add(interno);

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }

          }
              
        return listaTemporaria;
    }
    
    public static List recuperaAudienciaDuplicados(String ocorrenciaDeAudiencia, Map ocorrenciaDupla) {

                 List listaTemporaria = new ArrayList();  
                 
                 
                if(ocorrenciaDupla.size()!=0){
        


        try {

            String[] array = ocorrenciaDeAudiencia.split("\n");

            for (Object chave : ocorrenciaDupla.keySet()) {

 
                for (String interno : array) {

                    if (interno.startsWith((chave).toString())) {

                        listaTemporaria.add(interno);

                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
        }
        
        
     

        
              
        return listaTemporaria;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static Multimap transformaAudienciaDuplicadaEmMapa(List audiencia) {


        Multimap myMultimap = ArrayListMultimap.create();

       if(audiencia.size()!=0)
       {
       
        try {

            Set semDuplicidade = new ServicosGerais().recuperaChavesSemDuplicacao(audiencia);

            for (Object x : semDuplicidade) {

                for (Object h : audiencia) {

                    if (h.toString().startsWith(x.toString())) {

                        myMultimap.put(x, h);
                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
       
       }
        
   
        
        
        return myMultimap;
    }

    
    
    
    
    
     public static Multimap transformaAudienciaSingularEmMapa(List audiencia) {

        Multimap myMultimap = ArrayListMultimap.create();

        
        if(audiencia.size()!=0)
       {
       
       
           
        try {

            Set semDuplicidade = new ServicosGerais().recuperaChavesSemDuplicacao(audiencia);

            for (Object x : semDuplicidade) {

                for (Object h : audiencia) {

                    if (h.toString().startsWith(x.toString())) {

                        myMultimap.put(x, h);
                    }

                }

            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
       
       
       
       }
        
    
        
        
        return myMultimap;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static Map geracaoFinalDaAudiencia(Multimap mapa) {
             Map novoMapa = new HashMap<>();
        try {

//        
            for (Object primeiroLoop : mapa.keySet()) {

                Multimap mapaduplo = ArrayListMultimap.create();
                String valorLoop = mapa.get(primeiroLoop).toString().replaceAll("\\[|\\]", "").trim().replaceAll("\\s", "");
                String valores[] = valorLoop.split(",");

                for (String interioLoop : valores) {

                    mapaduplo.put(primeiroLoop, new LinhaAudiencia().transformaLinhaEmAudiencia(interioLoop));

                }

                List listaAdicional = new ArrayList();
                int volta = 1;
                for (Object valMapa : mapaduplo.values()) {

                    if (volta == 1) {

                        List listamteporaria = (List) valMapa;

                        for (int i = 0; i < listamteporaria.size(); i++) {

                            listaAdicional.add(listamteporaria.get(i));

                        }

                    } else {

                        List listamteporaria = (List) valMapa;

                        for (int i = 0; i < listamteporaria.size(); i++) {

                            if ((listamteporaria.get(i).toString().contains("1"))) {

                                if (listaAdicional.get(i).toString().equals("1")) {

                                } else {

                                    listaAdicional.set(i, listamteporaria.get(i));
                                }

                            }

                        }

                    }

                    volta++;
                }



                
                
                novoMapa.put(primeiroLoop, listaAdicional);
                
  
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return novoMapa;
    }

    public static void main(String[] args) {
            
            String val = "01000000006XA352B2A372B4A16B5A13B3A2B2A17B5A27B27A1B21A571F\n" +
            "01000000001XA730B16A5B13A3B1A3B17A5B2A23B2A27B1A592F\n" +
            "01000000004XA768B1A26B23A52B91A475B4F\n" +
            "01000000007XA869B1A570F";


    
//    
//        Map ma = geracaoFinalDaAudiencia(
//                 transformaAudienciaDuplicadaEmMapa(
//                 recuperaAudienciaDuplicados(
//                 val, entryPointStringParaMapaDuplicidade(val))));
        
        
        
//        System.out.println(ma);
   
//        
//        
              Map maSingular = geracaoFinalDaAudiencia(
                      transformaAudienciaSingularEmMapa(
                              recuperaAudienciaSingular(
                 val, entryPointStringParaMapaSingular(val))));  
              
              
                    System.out.println(maSingular);  
              
              
//        
//
//                ma.putAll(maSingular);
//              
//              
//                
//               
//        for(Object x: ma.keySet())
//        {
//        
//        
//            System.out.println(x);
//        
//        }
////        
//        
//        
//        System.out.println(ma);
        
        
    
    }

}
