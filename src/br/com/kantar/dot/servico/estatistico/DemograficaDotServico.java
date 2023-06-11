/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.dot.servico.estatistico;

import br.com.kantar.angariamento.model.abstrato.DOTServico;
import br.com.kantar.angariamento.model.audiencia.Audiencia;
import br.com.kantar.dot.repository.estatistico.DemograficaDotRepository;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class DemograficaDotServico {
    
    
     public int obterTamanhoDemograficaDomiciliarServicoDOT(List<Audiencia>p_lista_interna) {
     
     return new DemograficaDotRepository().obterTamanhoDemograficaDomiciliarServicoDOT(p_lista_interna);
     
     }
    
    
      public int obterTamanhoDemograficaIndividualServicoDOT(List<Audiencia>p_lista_interna) {
      
      return new DemograficaDotRepository().obterTamanhoDemograficaIndividualServicoDOT(p_lista_interna);
      
      }
     
    
      
        public List<Integer> obterDemograficaDomiciliarServicoDOT(long p_id, List<? extends DOTServico> p_lista) {
        
       return new DemograficaDotRepository().obterDemograficaDomiciliarServicoDOT(p_id, p_lista);
        
      }
      
        
     public List<Integer> obterDemograficaIndividualServicoDOT(long p_id_ind, List<? extends DOTServico> p_lista) {
     
         return new DemograficaDotRepository().obterDemograficaIndividualServicoDOT(p_id_ind, p_lista);
     
     }       
        
        
        
      
}
