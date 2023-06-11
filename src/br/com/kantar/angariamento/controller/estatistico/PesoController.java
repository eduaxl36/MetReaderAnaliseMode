/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.controller.estatistico;

import br.com.kantar.angariamento.servico.estatistico.PesoServico;
import java.util.Map;

/**
 *
 * @author eduardo
 */
public class PesoController {
    
    
    public String obterPesoDomiciliar(String p_entrypoint){
    
    return new PesoServico().obterPesoDomiciliar(p_entrypoint);
    
    }
    
    public  Map obterPesoIndividual(String p_entrypoint){
    
    return new PesoServico().obterPesoIndividual(p_entrypoint);
    }
}
