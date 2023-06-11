/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servicos.audiencia;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eduardo.Fernando
 */
public class primeiroTratamentoParaAngariamentoIndividual {
 
    
     public static Map coletaDeAudienciaIndividual(String pedacoArray){
    
         
    Map mp = new LinkedHashMap();
         
    String domicilio = "";
    String audiencias = "";
        try {
            

    String exp1 ="Z\\d{1,}\r\n.*\r\n.*\r\n[\\d{11}X[A|B\\d{1,}]{1,}F\r\n]{1,}"; 
    
   
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
    
    
        
            System.out.println(mp);
        
        return mp;
    }
    
     
     
     
     
     
   public static Map coletaDeDemograficaIndividual(String pedacoArray){
    
         
    Map mp = new LinkedHashMap();
         
    String domicilio = "";

        try {
            

    String exp1 ="Z\\d{1,}\r\n.*\r\n.*\r\n[\\d{11}X[A|B\\d{1,}]{1,}F\r\n]{1,}"; 
    
   
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
    
    
        
            System.out.println(mp);
        
        return mp;
    } 
    
   
   
   
   
   
   
   
    public static Map coletaDePesoIndividual(String pedacoArray){
    
         
    Map mp = new LinkedHashMap();
         
    String domicilio = "";

        try {
            

    String exp1 ="Z\\d{1,}\r\n.*\r\n.*\r\n[\\d{11}X[A|B\\d{1,}]{1,}F\r\n]{1,}"; 
    
   
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
    
    
        
            System.out.println(mp);
        
        return mp;
    }
    
    
   
  
    
    
    
    
    
    
    
    
    
    
}
