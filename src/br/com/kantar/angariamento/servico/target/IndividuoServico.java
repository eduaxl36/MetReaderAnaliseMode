/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.servico.target;

import br.com.kantar.angariamento.repository.target.IndividuoRepository;
import java.util.Set;

/**
 *
 * @author eduardo
 */
public class IndividuoServico {
    
    
    public Set obterIndividuosAngariamentoEntryPoint(String p_entrypoint){
    
        return new IndividuoRepository().obterIndividuosAngariamentoEntryPoint(p_entrypoint);
    
    }
    
    public int obterQtdIndividuos(String p_entrypoint) {
    
        return new IndividuoRepository().obterQtdIndividuos(p_entrypoint);
    
        
    }
    
    
}
