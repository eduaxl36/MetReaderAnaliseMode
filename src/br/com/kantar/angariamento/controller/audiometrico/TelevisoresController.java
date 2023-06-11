/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.controller.audiometrico;

import br.com.kantar.angariamento.servico.audiometrico.TelevisoresServico;
import java.util.Set;

/**
 *
 * @author eduardo
 */
public class TelevisoresController {
    
    
    public Set obterTelevisores(String p_entrypoint){
    
        return new TelevisoresServico().obterTelevisores(p_entrypoint);
    
    }
    
    public int obterQtdTelevisores(String p_entrypoint) {
    
    return new TelevisoresServico().obterQtdTelevisores(p_entrypoint);
    
    }
    
    
}
