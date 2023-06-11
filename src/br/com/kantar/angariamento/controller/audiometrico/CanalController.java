/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.controller.audiometrico;

import br.com.kantar.angariamento.servico.audiometrico.CanalServico;
import java.util.Set;

/**
 *
 * @author eduardo
 */
public class CanalController {
    
    
    public Set obterCanais(String p_entrypoint){
    
    return new CanalServico().obterCanais(p_entrypoint);
    
    }
    
}
