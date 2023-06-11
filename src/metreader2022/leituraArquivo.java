/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metreader2022;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Eduardo.Fernando
 */
public class leituraArquivo {
 
    public static void coletaDeTelevisoresDomiciliares(String pedacoArray){
    
    
        try {
            
 
    Set lst = new LinkedHashSet();

    String exp1 ="\\d{11}X"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
    
        lst.add(m.group().substring(0,2));
   
   
    }          
         
            System.out.println(lst);
    
    
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    }
     public static void coletaDeCanaisParaObjetoDomciliar(String pedacoArray){
    
    
        try {
            

    String exp1 ="\\d{11}X"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    Set listaSemDuplicidade = new LinkedHashSet();
    
    
    
    while(m.find())
    {
    
        listaSemDuplicidade.add(m.group().substring(m.group().length()-5,m.group().length()-1));
   
    }          
            
    
    System.out.println(listaSemDuplicidade);
    
    
    
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    }
    
      public static void coletaDeAudienciaDomiciliar(String pedacoArray){
    
    
        try {
            
 
    StringBuilder ac =new StringBuilder();

    String exp1 ="\\d{11}X[A|B\\d{1,}]{1,}F"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
    
        ac.append(m.group()+"\n");
   
   
    }          
          
    
            System.out.println(ac);
    
    
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    }
     
     
    public static void coletaDePesoDomiciliar(String pedacoArray){
    
    
        try {
            

  
    String exp1 ="I\\d{1,}\r\n\\w{1,}.*\r\nW\\d{1,}\\.\\d{1,}"; 
    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {

    Matcher z = Pattern.compile("W\\d{1,}\\.\\d{1,}").matcher(m.group());
        
    if(z.find()){
    
        System.out.println(z.group());
    
    }
        

   
    }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    }   
     
     
     
    
   public static void coletaDeDemograficaDomiciliar(String arquivoEmString){
    
    
        try {
            
 
                String exp1 ="I\\d{1,}\r\n\\w{1,}.*"; 
                Matcher m = Pattern.compile(exp1).matcher(arquivoEmString);

                while(m.find())
                {

                Matcher z = Pattern.compile("D.*").matcher(m.group());

                if(z.find()){

                System.out.println(z.group());

                }



                }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    }  
     
     
     
     public static void coletaDeIndividuosParaObjDomicilios(String pedacoArray){
    
    
        try {
            
   

    
    StringBuilder ac =new StringBuilder();


   
    
    String exp1 ="Z\\d{1,}"; 
    
    

    Matcher m = Pattern.compile(exp1).matcher(pedacoArray);
    
    while(m.find())
    {
    
        ac.append(m.group().toString().subSequence(m.group().length()-3, m.group().length())+"\n");
   
   
    }          
            
     
            System.out.println(ac);
    
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    
    }
     
     
     
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
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
    
    
    
    
    public static void main(String[] args) throws Exception {
        
        FileInputStream fisTargetFile = new FileInputStream(new File("c:/teste/teste.met"));

        String targetFileStr = IOUtils.toString(fisTargetFile, "UTF-8").replaceAll("I", "QI");    
    
        String[]quebras = targetFileStr.split("Q");
    

         String tempDeletarDepois = quebras[2];
         
//              coletaDeAudienciaIndividual(tempDeletarDepois);
         
       //  coletaDeDemograficaIndividual(tempDeletarDepois);
//         coletaDePesoIndividual(tempDeletarDepois);
//          coletaDeAudienciaDomiciliar(tempDeletarDepois);
//          coletaDeIndividuosParaObjDomicilios(tempDeletarDepois);
//          coletaDePesoDomiciliar(tempDeletarDepois);
//          coletaDeCanaisParaObjetoDomciliar(tempDeletarDepois);
//          coletaDeDemograficaDomiciliar(tempDeletarDepois);
//          coletaDeTelevisoresDomiciliares(tempDeletarDepois);
  
    
    
   
 
        //coletaDeIndividuos(targetFileStr);
        //
//        
//        
        


 
    }
    
    
}
