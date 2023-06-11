/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.servico.audiometrico;

import br.com.kantar.angariamento.repository.audiometrico.CanalRepository;
import java.util.Set;

/**
 *
 * @author eduardo
 */
public class CanalServico {
    
    
    
     public Set obterCanais(String p_entrypoint){
     
         return new CanalRepository().obterCanais(p_entrypoint);
     
     }
     
    
     
     
    
}
