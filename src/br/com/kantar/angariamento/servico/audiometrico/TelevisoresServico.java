/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.servico.audiometrico;

import br.com.kantar.angariamento.repository.audiometrico.TelevisoresRepository;
import java.util.Set;

/**
 *
 * @author eduardo
 */
public class TelevisoresServico {
    
    
     public Set obterTelevisores(String p_entrypoint){
     
     return new TelevisoresRepository().obterTelevisores(p_entrypoint);
     
     }
    
     public int obterQtdTelevisores(String p_entrypoint) {
     
     return new TelevisoresRepository().obterQtdTelevisores(p_entrypoint);
         
     }
     
    
}
