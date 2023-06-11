/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.servico.estatistico;

import br.com.kantar.angariamento.repository.estatistico.PesoRepository;
import java.util.Map;

/**
 *
 * @author eduardo
 */
public class PesoServico {
    
    
    
       public String obterPesoDomiciliar(String p_entrypoint){
       
       return new PesoRepository().obterPesoDomiciliarEntryPoint(p_entrypoint);
       
       }
    
    
        public  Map obterPesoIndividual(String p_entrypoint){
        
        return new PesoRepository().obterPesoIndividualEntryPoint(p_entrypoint);
        
        }
       
       
       
}
