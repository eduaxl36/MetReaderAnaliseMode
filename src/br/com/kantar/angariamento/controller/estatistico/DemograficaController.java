/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.controller.estatistico;


import br.com.kantar.angariamento.model.target.Domicilio;
import br.com.kantar.angariamento.model.estatistico.Demografica;
import br.com.kantar.angariamento.servico.estatistico.DemograficaServico;
import java.util.Map;

/**
 *
 * @author eduardo
 */
public class DemograficaController {
    
    
    public Domicilio obterDemograficaDomiciliarAngariamento(String p_entrypoint) {
    
    return new DemograficaServico().obterDemograficaDomiciliarAngariamento(p_entrypoint);
    
    }
    
    
     public Map<String, Demografica> obterDemograficaIndividualAngariamento(String p_entrypoiny) {
     
     return new DemograficaServico().obterDemograficaIndividualAngariamento(p_entrypoiny);
     
     
     }

}
