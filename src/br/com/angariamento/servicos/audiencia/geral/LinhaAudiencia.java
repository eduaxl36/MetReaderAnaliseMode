/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.angariamento.servicos.audiencia.geral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eduardo.Fernando
 */
public class LinhaAudiencia {
    
     
    public List insereLigados(String exp){
    
      List listaTemp = new ArrayList();

        try {
            
            int valorCorrespondenteAudiencia =  Integer.parseInt(exp.replaceAll("B",""));
            String[]arrayTemp = new String[valorCorrespondenteAudiencia];
            Arrays.fill(arrayTemp, 0,valorCorrespondenteAudiencia,"1");
            List t =  Arrays.asList(arrayTemp);

            listaTemp.addAll(t);
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
        return listaTemp;
    
    }
    
    public List insereDesligados(String exp){
    
        List listaTemp = new ArrayList();

        try {
            
             int valorCorrespondenteAudiencia =  Integer.parseInt(exp.replaceAll("A",""));
            
        
           
            String[]arrayTemp = new String[valorCorrespondenteAudiencia];
            Arrays.fill(arrayTemp, 0,valorCorrespondenteAudiencia,"0");
            List t =  Arrays.asList(arrayTemp);

            listaTemp.addAll(t);
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
        return listaTemp;
    
    }
    
    
    
     public  List transformaLinhaEmAudiencia(String linhaDeAudiencia)
    {
    
        
        List<Integer> listaTemp = new ArrayList<>();
        
        try {
            
            int indDivior =  linhaDeAudiencia.indexOf("X")+1;
            String linhaColetadaDaAudiencia = linhaDeAudiencia.substring(indDivior,linhaDeAudiencia.length());
           
            
            Matcher exp = Pattern.compile("\\w\\d{1,}").matcher(linhaColetadaDaAudiencia);
            
            while(exp.find()){


            if(exp.group().startsWith("A")){
            
                listaTemp.addAll(insereDesligados(exp.group()));
            
            }    
                
            if(exp.group().startsWith("B")){
            
                listaTemp.addAll(insereLigados(exp.group()));

            }     
            
            }     
          
       
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    return listaTemp;
    }
    
    
     
 
       public static void main(String[] args) {
        
           System.out.println();
           
           
           
           for(Object x:new LinhaAudiencia().transformaLinhaEmAudiencia("03000000583XA729B15A7B13A676F"))
           {
           
           
               System.out.println(x);
           
           }           
           
    }
       
       
}
