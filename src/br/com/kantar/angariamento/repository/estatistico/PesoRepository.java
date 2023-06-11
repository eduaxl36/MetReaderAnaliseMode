/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.repository.estatistico;

import br.com.analise.dot.met.DOTDomiciliar;
import br.com.analise.dot.met.DOTIndividual;
import br.com.kantar.angariamento.model.abstrato.DOTServico;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

/**
 *
 * @author eduardo
 */
public class PesoRepository {
    
     public String obterPesoDomiciliarEntryPoint(String p_entrypoint){
    
    String var_controle_st_retorno="";
    
    
        try {

            
                    Matcher var_controle_regex = Pattern.compile("I\\d{1,}\n\\w{1,}.*\nW\\d{1,}\\.\\d{1,}").matcher(p_entrypoint);

                    while(var_controle_regex.find())
                    {

                    Matcher var_controle_regex_interno = Pattern.compile("W\\d{1,}\\.\\d{1,}").matcher(var_controle_regex.group());

                    if(var_controle_regex_interno.find()){

                    var_controle_st_retorno = new String(var_controle_regex_interno.group());

                    }

                    }          


                    } catch (Exception e) {

                    e.printStackTrace();

                    }
    
    return var_controle_st_retorno;
    }
   
     
     
     
    public  Map obterPesoIndividualEntryPoint(String p_entrypoint){
    
         
    Map var_controle_mapa_retorno = new LinkedHashMap();
         
    String var_controle_st_dom = "";

    try {
            
            Matcher var_controle_exp_main = Pattern.compile("Z\\d{1,}\n.*\n.*\n[\\d{11}X[A|B\\d{1,}]{1,}F\n]{1,}").matcher(p_entrypoint);

            while(var_controle_exp_main.find())
            {

            String var_controle_get_main_expression_result = var_controle_exp_main.group();

            Matcher var_controle_regex_dom = Pattern.compile("Z\\d{1,}").matcher(var_controle_get_main_expression_result);

            var_controle_st_dom=var_controle_regex_dom.find()?var_controle_regex_dom.group():"";

            Matcher var_controle_regex_aud = Pattern.compile("W\\d{1,}\\.\\d{1,}").matcher(var_controle_get_main_expression_result);

            StringBuilder var_controle_sb = new StringBuilder();


            while(var_controle_regex_aud.find())
            {

            var_controle_sb.append(var_controle_regex_aud.group()+"\n");

            }


            var_controle_mapa_retorno.put(var_controle_st_dom, var_controle_sb.toString());

            }          


    } 
    
    catch (Exception e) 
    {

    e.printStackTrace();

    }
    
    
        

        return var_controle_mapa_retorno;
    }
    
     
}
