/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.servico.audiencia;

import br.com.kantar.angariamento.model.afericao.Afericao;
import br.com.kantar.angariamento.model.audiencia.Audiencia;
import br.com.kantar.angariamento.model.target.Individuo;
import br.com.kantar.angariamento.repository.audiencia.*;
import com.google.common.collect.Multimap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eduardo
 */
public class AudienciaServicos {
    
      
   public List<Individuo> obterAudienciaIndividual(String p_entrypoint, int[] p_array_canais) {
       
       return new AudienciaRepository().obterAudienciaIndividual(p_entrypoint, p_array_canais);

   }
   
   
    public List<Afericao> obterAudienciaDomiciliar(String p_entrypoint_raw_string, int[] p_array_canais){
    
    return new AudienciaRepository().obterAudienciaDomiciliar(p_entrypoint_raw_string, p_array_canais);
    
    }
   
    
    
    public List<Audiencia> fornecerListaAudiencia(Map<LocalDate, File> p_contentmap, int[] p_array_canais) throws FileNotFoundException, IOException {
        
    return new AudienciaRepository().fornecerListaAudiencia(p_contentmap, p_array_canais);
        
    }
    
    
}
