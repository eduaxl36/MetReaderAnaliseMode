/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.controller.target;

import br.com.kantar.angariamento.servico.target.DomicilioServico;

/**
 *
 * @author eduardo
 */
public class DomicilioController {
    
    
   public String obterId(String p_entrypoint){
   
   return new DomicilioServico().obterId(p_entrypoint);
   
   }
    
}
