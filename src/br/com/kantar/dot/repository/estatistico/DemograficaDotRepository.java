/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.dot.repository.estatistico;

import br.com.analise.dot.met.DOTDomiciliar;
import br.com.analise.dot.met.DOTIndividual;
import br.com.kantar.angariamento.model.abstrato.DOTServico;
import br.com.kantar.angariamento.model.audiencia.Audiencia;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author eduardo
 */
public class DemograficaDotRepository {
    
    
    public int obterTamanhoDemograficaDomiciliarServicoDOT(List<Audiencia>p_lista_interna) {
    
        try {
            
            return p_lista_interna.get(0).getInfo_domiciliares().getDemografica().getListaDemograficas().size();
            
        } catch (Exception e) {
        
        e.printStackTrace();
        }
        
       return 0; 
    }
    
    

    public int obterTamanhoDemograficaIndividualServicoDOT(List<Audiencia>p_lista_interna) {
    
        try {
            
            return p_lista_interna.get(0).getInfo_domiciliares().getDemografica().getListaDemograficas().size();
            
        } catch (Exception e) {
        
        e.printStackTrace();
        }
        
       return 0; 
    }  
    
    
    
    
    
  public List<Integer> obterDemograficaDomiciliarServicoDOT(long p_id, List<? extends DOTServico> p_lista) {

        List<Integer> var_controle_list_demo_retorno=null;
        try {
            
            List<DOTDomiciliar> var_controle_list_domiciliar = (List<DOTDomiciliar>) p_lista;

            Set var_controle_set = var_controle_list_domiciliar.
                    stream()
                    .filter(x -> x.getId_domicilio() == p_id)
                    .distinct()
                    .map(x -> x.getDemografica())
                    .collect(Collectors.toSet());

           var_controle_list_demo_retorno = new ArrayList(var_controle_set);

            return var_controle_list_demo_retorno;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return var_controle_list_demo_retorno;

    }
  
  
  
    public List<Integer> obterDemograficaIndividualServicoDOT(long p_id_ind, List<? extends DOTServico> p_lista) {
        
        List<Integer> var_controle_lista_retorno=null;
        try {

            List<DOTIndividual> var_controle_lista_rec = (List<DOTIndividual>) p_lista;

            Set var_controle_set = var_controle_lista_rec.
                    stream()
                    .filter(x -> x.getId_individuo() == p_id_ind)
                    .distinct()
                    .map(x -> x.getDemografica())
                    .collect(Collectors.toSet());

             var_controle_lista_retorno= new ArrayList(var_controle_set);

            return var_controle_lista_retorno;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return var_controle_lista_retorno;

    }   
    
    
}
