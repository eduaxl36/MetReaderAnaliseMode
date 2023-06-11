/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.repository.target;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eduardo
 */
public class IndividuoRepository {
    
   
   public Set obterIndividuosAngariamentoEntryPoint(String p_entrypoint){
    
         
         Set var_controle_temp = new LinkedHashSet();
    
                Matcher var_controle_regex = Pattern.compile("Z\\d{1,}").matcher(p_entrypoint);

                while(var_controle_regex.find())
                {

                var_controle_temp.add(var_controle_regex.group().toString().subSequence(var_controle_regex.group().length()-3, var_controle_regex.group().length()));

                }          
                
        return var_controle_temp;
    
    } 
    
  
   public int obterQtdIndividuos(String p_entrypoint) {

            return obterIndividuosAngariamentoEntryPoint(p_entrypoint).size();

    }
       
    
    
    
    
    
    
    
}
