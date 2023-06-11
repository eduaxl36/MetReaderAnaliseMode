/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.angariamento.servicos.basicdataarquivo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class primeiroTratamentoParaAngariamentoArquivoMet {
 
    
  public  LocalTime obterHora(String pedacoArray){
    
        try {
            
    String exp1 ="I\\d{1,}"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    if(m.find())
    {
        
         LocalTime lcTemp = LocalTime.parse(m.group().substring(17,19)+":"+m.group().substring(19,21));
         return lcTemp;
    }          
         
    
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
     return null;
    }  
  
  
    public  LocalDate obterData(String pedacoArray){
    
        try {
            
   
    
    String exp1 ="I\\d{1,}"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    if(m.find())
    {

       DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
       LocalDate lcData = LocalDate.parse(m.group().substring(9,17),formatter);

         return lcData;
    }          
         
    
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
     return null;
    }
    
    
}
