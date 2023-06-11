/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.angariamento.servicos.audiencia.domiciliar;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eduardo.Fernando
 */
public class PrimeiroTratamentoParaAngariamentoDomciliar {
    
    
   public Set obterTelevisores(String pedacoArray){
    
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
   
   
   
     public Set obterCanais(String pedacoArray){
    
    
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
    
     
      public StringBuilder obterAudiencia(String pedacoArray){
    
    
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
     
     
    public String obterPeso(String pedacoArray){
    
    String dom="";
    
        try {

    String exp1 ="I\\d{1,}\n\\w{1,}.*\nW\\d{1,}\\.\\d{1,}"; 
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
     
     
     public String obterId(String arquivoEmString){
    
    String id="";
    
        try {
            
 
                String exp1 ="I\\d{1,}"; 
                Matcher m = Pattern.compile(exp1).matcher(arquivoEmString);

                while(m.find())
                {

  
                 id=m.group().substring(1,9);


                }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    return id;
    
    } 
    
   public String obterDemografica(String arquivoEmString){
    
    String demo="";
    
        try {
            
 
                String exp1 ="I\\d{1,}\nD.*"; 
                Matcher m = Pattern.compile(exp1).matcher(arquivoEmString);

                while(m.find())
                {


                    
                Matcher z = Pattern.compile("D.*").matcher(m.group());

                while(z.find()){

                demo = new String(z.group());

                }



                }          
            
            return demo;
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    return demo;
    
    }  
     
     
     
     public Set obterIndividuos(String pedacoArray){
    
         
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
