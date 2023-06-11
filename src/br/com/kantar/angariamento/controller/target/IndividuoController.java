/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.controller.target;

import br.com.kantar.angariamento.servico.target.IndividuoServico;
import java.util.Set;

/**
 *
 * @author eduardo
 */
public class IndividuoController {
    
    
    public Set obterIndividuosAngariamentoEntryPoint(String p_entrypoint){
    
    return new IndividuoServico().obterIndividuosAngariamentoEntryPoint(p_entrypoint);
        
    }
    
     public int obterQtdIndividuos(String p_entrypoint) {
     
     return new IndividuoServico().obterQtdIndividuos(p_entrypoint);
     
     }
    
    
    
}
