/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.repository.estatistico;



import br.com.kantar.angariamento.model.target.Domicilio;
import br.com.kantar.angariamento.controller.estatistico.PesoController;
import br.com.kantar.angariamento.controller.target.DomicilioController;
import br.com.kantar.angariamento.model.estatistico.Demografica;

import br.com.kantar.angariamento.model.estatistico.Peso;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author eduardo
 */
public class DemograficaRepository {
    
    
  
    
    
   public  Map obterDemograficaIndividualEntryPoint(String p_entrypoint){
    
         
    Map var_controle_mapa_retorno = new LinkedHashMap();
         
    String var_controle_st_dom = "";

        try {
            
    Matcher var_controle_main_regex = Pattern.compile("Z\\d{1,}\n.*\n.*\n[\\d{11}X[A|B\\d{1,}]{1,}F\n]{1,}").matcher(p_entrypoint);
    
    while(var_controle_main_regex.find())
    {
        
    String var_controle_store_regex_result = var_controle_main_regex.group();
    
    Matcher dom = Pattern.compile("Z\\d{1,}").matcher(var_controle_store_regex_result);
    
    var_controle_st_dom=dom.find()?dom.group():"";
    
 
    Matcher var_controle_regex_aud = Pattern.compile("D.*").matcher(var_controle_store_regex_result);
    
    StringBuilder var_controle_sb = new StringBuilder();
    
   
    while(var_controle_regex_aud.find())
    {
    
    var_controle_sb.append(var_controle_regex_aud.group()+"\n");
    
    }
    
 
    var_controle_mapa_retorno.put(var_controle_st_dom, var_controle_sb.toString());
    
   
    }          
            
            
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    
    

        
        return var_controle_mapa_retorno;
    } 
    
    
    

   public String obterDemograficaDomiciliarEntryPoint(String p_entrypoint){
    
    String var_controle_st="";
    
        try {
            
                Matcher var_controle_regex_sp = Pattern.compile("I\\d{1,}\nD.*").matcher(p_entrypoint);

                while(var_controle_regex_sp.find())
                {
  
                    Matcher var_controle_regex_int = Pattern.compile("D.*").matcher(var_controle_regex_sp.group());

                    while(var_controle_regex_int.find()){

                    var_controle_st = new String(var_controle_regex_int.group());

                }



                }          
            
            return var_controle_st;
        } catch (Exception e) {
        
        e.printStackTrace();
        
        }
    return var_controle_st;
    
    }
    
    
  public Domicilio obterDemograficaDomiciliarAngariamento(String p_entrypoint) {
      
        Domicilio var_controle_obj_dom=null;
        
        try {

            String[] var_controle_array_st = obterDemograficaDomiciliarEntryPoint(p_entrypoint).split(",");
            List<Integer> var_controle_lista = new ArrayList<>();
            for (String l_st : var_controle_array_st) {

                if (l_st.equals("D")) {

                } else {

                    var_controle_lista.add(Integer.parseInt(l_st));

                }

            }
             var_controle_obj_dom = new Domicilio(Integer.parseInt(new DomicilioController().obterId(p_entrypoint)), new Demografica(var_controle_lista), new Peso(Double.parseDouble(new PesoController().obterPesoDomiciliar(p_entrypoint).replaceAll("W", "").trim())));

            return var_controle_obj_dom;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return var_controle_obj_dom;
    }
    
  
 
  public Map<String, Demografica> obterDemograficaIndividualAngariamento(String p_entrypoiny) {

        Map<String, Demografica> var_controle_mapa_retorno = new LinkedHashMap();

        Map var_controle_temp_map = obterDemograficaIndividualEntryPoint(p_entrypoiny);
        


        for (Object l_map : var_controle_temp_map.keySet()) {

            String[] var_controle_array_st = var_controle_temp_map.get(l_map).toString().split(",");

            List<Integer> var_controle_list = new ArrayList();

            for (String l_st : var_controle_array_st) {

                if (l_st.equals("D")) {
                } else {

            
                    var_controle_list.add(Integer.parseInt(l_st.trim()));

                }

            }

            var_controle_mapa_retorno.put(l_map.toString(), new Demografica(var_controle_list));

        }

        return var_controle_mapa_retorno;

    }
  
  
  
  

    
}
