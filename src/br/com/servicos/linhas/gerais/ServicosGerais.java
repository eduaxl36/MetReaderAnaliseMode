/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servicos.linhas.gerais;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Eduardo.Fernando
 */
public class ServicosGerais {
    
    
 /// metodo que avalia se o conjunto de audiencias coletadas possui repetição de audiencia ou nao   
  public static boolean entryPointDirecionador(String valorInicialEntrada){
    
        try {
            
                String[] array = valorInicialEntrada.split("\n");
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

                    return true;
                   
                }

            }
                
                
            
        } catch (Exception e) {
       
        e.printStackTrace();
        
        }
    
    
    return false;
    }
    
    
    
    
     public static List transformaLinhaEmAudiencia(String linhaDeAudiencia)
    {
    
        
        List<Integer> listaTemp = new ArrayList<>();
        
        try {
          
            String linhaColetadaDaAudiencia = linhaDeAudiencia.substring(12,linhaDeAudiencia.length());
            

            int voltas=1;
            
            
            Matcher exp = Pattern.compile("\\w\\d{1,}").matcher(linhaColetadaDaAudiencia);
            
            while(exp.find()){


            if(exp.group().startsWith("A")){
            
            
            
            int valorCorrespondenteAudiencia =  Integer.parseInt(exp.group().replaceAll("A",""));
           
            
            if(voltas==1){
            
                
             for(int i=0;i<valorCorrespondenteAudiencia;i++)
             {
             
                 listaTemp.add(0);
                 
             }
                
                
            }
            else{
            
                
                 for(int i=0;i<valorCorrespondenteAudiencia;i++)
             {
             
                 listaTemp.add(0);
                 
                 
             }        
            

            }      
            

            }    
                
      if(exp.group().startsWith("B")){
            
            
            
            int valorCorrespondenteAudiencia =  Integer.parseInt(exp.group().replaceAll("B",""));
           
            
            if(voltas==1){
            
                
             for(int i=0;i<valorCorrespondenteAudiencia;i++)
             {
             
                 listaTemp.add(1);
                 
                 
             }
                
                
            }
            else{
            
                
                 for(int i=0;i<valorCorrespondenteAudiencia;i++)
             {
             
                 listaTemp.add(1);
                 
                 
             }        
            

            }      
            

            }     
            
              
            voltas++;
            }
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    return listaTemp;
    }
    
    
     
       public  Set recuperaChavesSemDuplicacao(List valor){
    
        Set arrTemp = new LinkedHashSet();
        
        for(Object loop:valor)
        {
        
        arrTemp.add(loop.toString().substring(0,11));
        
        
        }
        
        
        return arrTemp;
    }  
 
       
       public static void main(String[] args) {
        
           
           
    }
       
       
}
