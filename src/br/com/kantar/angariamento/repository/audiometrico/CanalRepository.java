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
public class CanalRepository {
    
    
    
     public Set obterCanais(String p_entrypoint){
    
      Set var_controle_set=null;

      Matcher var_controle_regex = Pattern.compile("\\d{11}X").matcher(p_entrypoint);
    
      var_controle_set= new LinkedHashSet();
    

      while(var_controle_regex.find())
      {
    
        var_controle_set.add(var_controle_regex.group().substring(var_controle_regex.group().length()-5,var_controle_regex.group().length()-1));
   
      }          
            
   
     return var_controle_set;
    
    }
}
