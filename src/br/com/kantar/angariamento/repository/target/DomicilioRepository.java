/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.repository.target;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eduardo
 */
public class DomicilioRepository {
    

    public String obterId(String p_entrypoint){
    
    String var_controle_id_retorno="";
    

                Matcher var_controle_regex = Pattern.compile("I\\d{1,}").matcher(p_entrypoint);

                while(var_controle_regex.find())
                {

  
                 var_controle_id_retorno=var_controle_regex.group().substring(1,9);


                }          
            
 
    return var_controle_id_retorno;
    
    }
        
    
    
}
