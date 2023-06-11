/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servicos.audiencia;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author Eduardo.Fernando
 */
public class primeiroTratamentoParaAngariamentoDomciliar {
    
    
   public Set coletaDeTelevisoresDomiciliares(String pedacoArray){
    
        try {
            
 
    Set lst = new LinkedHashSet();

    String exp1 ="\\d{11}X"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
    
        lst.add(m.group().substring(0,2));
   
   
    }          
         
    return lst;
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
     return null;
    }
   
   
   
     public Set coletaDeCanaisParaObjetoDomciliar(String pedacoArray){
    
    
        try {
            

    String exp1 ="\\d{11}X"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    Set listaSemDuplicidade = new LinkedHashSet();
    
    
    
    while(m.find())
    {
    
        listaSemDuplicidade.add(m.group().substring(m.group().length()-5,m.group().length()-1));
   
    }          
            
    

    
    return listaSemDuplicidade;
    
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
     return null;
    }
    
     
      public StringBuilder coletaDeAudienciaDomiciliar(String pedacoArray){
    
    
        try {
            
 
    StringBuilder ac =new StringBuilder();

    String exp1 ="\\d{11}X[A|B\\d{1,}]{1,}F"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
    
        ac.append(m.group()+"\n");
   
   
    }          
          
    
    return ac;
    
    
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    return null;
    }
     
     
    public String coletaDePesoDomiciliar(String pedacoArray){
    
    String dom="";
    
        try {

    String exp1 ="I\\d{1,}\r\n\\w{1,}.*\r\nW\\d{1,}\\.\\d{1,}"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {

    Matcher z = Pattern.compile("W\\d{1,}\\.\\d{1,}").matcher(m.group());
        
    if(z.find()){
    
        dom = new String(z.group());
    
    }
        
    }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    return dom;
    }   
     
     
     
    
   public String coletaDeDemograficaDomiciliar(String arquivoEmString){
    
    String peso="";
    
        try {
            
 
                String exp1 ="I\\d{1,}\r\n\\w{1,}.*"; 
                Matcher m = Pattern.compile(exp1).matcher(arquivoEmString);

                while(m.find())
                {

                Matcher z = Pattern.compile("D.*").matcher(m.group());

                if(z.find()){

                peso = new String(z.group());

                }



                }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    return peso;
    
    }  
     
     
     
     public Set coletaDeIndividuosParaObjDomicilios(String pedacoArray){
    
         
         Set temp = new LinkedHashSet();
    
        try {
            

                String exp1 ="Z\\d{1,}"; 

                Matcher m = Pattern.compile(exp1).matcher(pedacoArray);

                while(m.find())
                {

                temp.add(m.group().toString().subSequence(m.group().length()-3, m.group().length()));

                }          
            

        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
        return temp;
    
    }
   
}
