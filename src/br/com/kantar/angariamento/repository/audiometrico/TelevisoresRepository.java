/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.repository.audiometrico;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eduardo
 */
public class TelevisoresRepository {
    
   
 public Set obterTelevisores(String p_entrypoint){
    
     
                Set var_controle_set_retorno=null;
    
                var_controle_set_retorno = new LinkedHashSet();

                Matcher var_controle_regex = Pattern.compile("\\d{11}X").matcher(p_entrypoint);

                while(var_controle_regex.find())
                {

                var_controle_set_retorno.add(var_controle_regex.group().substring(0,2));


                }          

                return var_controle_set_retorno;
     
    }
 
 
    
     public int obterQtdTelevisores(String p_entrypoint) {

            return obterTelevisores(p_entrypoint).size();

    }
     
        
    
    
}
