/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servicos.audiencia;

import metreader2022.*;
import br.com.servicos.linhas.gerais.ServicosGerais;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
public class servicosNormalizacaoCOV {

    
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

        return listaTemporaria;
    }
    
    public static List recuperaAudienciaDuplicados(String ocorrenciaDeAudiencia, Map ocorrenciaDupla) {


        List listaTemporaria = new ArrayList();

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

        
              
        return listaTemporaria;
    }

    public static Multimap transformaAudienciaDuplicadaEmMapa(List audiencia) {

        Multimap myMultimap = ArrayListMultimap.create();

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
        
        
        
        return myMultimap;
    }

   
    
     public static Multimap transformaAudienciaSingularEmMapa(List audiencia) {

        Multimap myMultimap = ArrayListMultimap.create();

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
        
        
        
        return myMultimap;
    }
    
    
   
    public static Map geracaoUniaoDasAudiencias(Multimap mapa) {
             Map novoMapa = new HashMap<>();
        try {

//        
            for (Object primeiroLoop : mapa.keySet()) {

                Multimap mapaduplo = ArrayListMultimap.create();
                String valorLoop = mapa.get(primeiroLoop).toString().replaceAll("\\[|\\]", "").trim().replaceAll("\\s", "");
                String valores[] = valorLoop.split(",");

                for (String interioLoop : valores) {

                    mapaduplo.put(primeiroLoop, new ServicosGerais().transformaLinhaEmAudiencia(interioLoop));

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

    
    
    public static Map obterValoresParaExtracao(String val){
    
        Map temp = new LinkedHashMap();
    
    
        try {
            
               Map ma = geracaoUniaoDasAudiencias(
                 transformaAudienciaDuplicadaEmMapa(
                 recuperaAudienciaDuplicados(
                 val, entryPointStringParaMapaDuplicidade(val))));
   
        
        
              Map maSingular = geracaoUniaoDasAudiencias(
                      transformaAudienciaSingularEmMapa(
                              recuperaAudienciaSingular(
                 val, entryPointStringParaMapaSingular(val))));  
        

                ma.putAll(maSingular);   
                
                temp.putAll(ma);
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    return temp;
    }
    
    


}
