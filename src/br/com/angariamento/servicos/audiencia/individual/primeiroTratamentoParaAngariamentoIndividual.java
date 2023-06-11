/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.angariamento.servicos.audiencia.individual;

import br.com.kantar.angariamento.model.afericao.Afericao;
import br.com.kantar.angariamento.kantar.model.audiometrico.Audiometria;
import br.com.kantar.angariamento.model.componente.audiometrico.Canal;
import br.com.kantar.angariamento.model.componente.audiometrico.Televisor;
import br.com.kantar.angariamento.model.estatistico.Demografica;
import br.com.kantar.angariamento.model.estatistico.Peso;
import br.com.kantar.angariamento.model.target.Individuo;
import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class primeiroTratamentoParaAngariamentoIndividual {
 
    
     public  Map coletaDeAudienciaIndividual(String pedacoArray){
     

    Map mp = new LinkedHashMap();
         
    String domicilio = "";

        try {
            

    String exp1 ="Z\\d{1,}\n.*\n.*\n[\\d{11}X[A|B\\d{1,}]{1,}F\n]{1,}"; 
    
    
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
        
    String resultadoDaExpressaBase = m.group();

    Matcher dom = Pattern.compile("Z\\d{1,}").matcher(resultadoDaExpressaBase);
    
    domicilio=dom.find()?dom.group():"";
    
 
    Matcher auds = Pattern.compile("(\\d{1,}X[A|B\\d{1,}]{1,}F){1,}").matcher(resultadoDaExpressaBase);
    StringBuilder sb = new StringBuilder();

    while(auds.find())
    {

   
    sb.append(auds.group()+"\n");
    
    }
    

    
    mp.put(domicilio, sb.toString());
   


    }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
        
  
        
        return mp;
    }
    
     
     public Multimap obterDetalhamentoAudienciaIndividual(String pedacoArray)
     {
     
    
         
         
     Multimap myMultimap = ArrayListMultimap.create();
         try {
  
             
             Map s = coletaDeAudienciaIndividual(pedacoArray);
             
             for(Object x:s.keySet())
             {
             
              
             String []linhas =   s.get(x).toString().split("\n");

                 
             for(String xl:linhas){
                 
             if(xl.matches(".*\\d{1,}.*")){
             
   
              String valorCrudo= xl;
              

              int tv =  Integer.parseInt(valorCrudo.substring(0,2));
              int canal = Integer.parseInt(valorCrudo.substring(valorCrudo.indexOf("X")-5,valorCrudo.indexOf("X")));
              
              String chaveCompleta = x+";"+tv+";"+canal;
              

              
                       
                          List h = (List) new LinhaAudiencia().transformaLinhaEmAudiencia(xl);

                          

      
                          
                          
              myMultimap.put(chaveCompleta, h);              
                       
                      }             
              


              

             }
             

             }
        
         } catch (Exception e) {
         e.printStackTrace();
         
         }

         
     return myMultimap;
     
     }
   public  Map coletaDeDemograficaIndividual(String pedacoArray){
    
         
    Map mp = new LinkedHashMap();
         
    String domicilio = "";

        try {
            

    String exp1 ="Z\\d{1,}\n.*\n.*\n[\\d{11}X[A|B\\d{1,}]{1,}F\n]{1,}"; 
    
   
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
    String resultadoDaExpressaBase = m.group();
    
    Matcher dom = Pattern.compile("Z\\d{1,}").matcher(resultadoDaExpressaBase);
    
    domicilio=dom.find()?dom.group():"";
    
 
    Matcher auds = Pattern.compile("D.*").matcher(resultadoDaExpressaBase);
    StringBuilder sb = new StringBuilder();
    
   
    while(auds.find())
    {
    
    sb.append(auds.group()+"\n");
    
    }
    
   

    
   
    mp.put(domicilio, sb.toString());
   
    }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    

        
        return mp;
    } 
    
   
   
   
   
   
   
   
    public  Map coletaDePesoIndividual(String pedacoArray){
    
         
    Map mp = new LinkedHashMap();
         
    String domicilio = "";

        try {
            

    String exp1 ="Z\\d{1,}\n.*\n.*\n[\\d{11}X[A|B\\d{1,}]{1,}F\n]{1,}"; 
    
   
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
    String resultadoDaExpressaBase = m.group();
    
    
    
    
    
    Matcher dom = Pattern.compile("Z\\d{1,}").matcher(resultadoDaExpressaBase);
    
    domicilio=dom.find()?dom.group():"";
    
 
    Matcher auds = Pattern.compile("W\\d{1,}\\.\\d{1,}").matcher(resultadoDaExpressaBase);
    StringBuilder sb = new StringBuilder();
    
   
    while(auds.find())
    {
    
    sb.append(auds.group()+"\n");
    
    }
    

    mp.put(domicilio, sb.toString());
   
    }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
        

        return mp;
    }
    
       
    
    public static void main(String[] args) {
        
        
        new primeiroTratamentoParaAngariamentoIndividual().obterDetalhamentoAudienciaIndividual("I10602171202201050600202201060600\n" +
"D,42,3,1,2,4,5,1,2,1,2\n" +
"W4192.95\n" +
"Z10602171001\n" +
"D,42,3,1,2,4,5,1,2,1,2,2,6,1,2,2,84,2,6,1,7\n" +
"W4385.31\n" +
"01000000007XA181B6A791B56A406F\n" +
"01000000004XA187B61A66B85A10B99A184B15A22B249A462F");
        
    }
    
}
