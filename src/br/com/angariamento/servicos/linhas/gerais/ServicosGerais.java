/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.angariamento.servicos.linhas.gerais;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo.Fernando
 */
public class ServicosGerais {
    
    

       public  Set recuperaChavesSemDuplicacao(List p_lista){
    
         Set var_controle_set_retorno = (Set) p_lista.stream()
        .map(x->x.toString().substring(0,11))
        .distinct()
        .collect(Collectors.toSet());
                

//        for(Object l_dados:p_lista)
//        {
//        
//        var_controle_set_retorno.add(l_dados.toString().substring(0,11));
//        
//        
//        }
        
        
        return var_controle_set_retorno;
    }  
 
       
}
