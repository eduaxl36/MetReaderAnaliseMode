/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.dot.controller.estatistico;

import br.com.analise.dot.met.DOTDomiciliar;
import br.com.kantar.angariamento.model.abstrato.DOTServico;
import br.com.kantar.dot.servico.estatistico.PesoDotServico;
import java.util.List;
import java.util.OptionalDouble;

/**
 *
 * @author eduardo
 */
public class PesoDotController {
    
           public OptionalDouble obterPesoDomiciliarServicoDot(long p_id, List<DOTDomiciliar> p_lista_audiencia) {
           
      return new PesoDotServico().obterPesoDomiciliarServicoDot(p_id, p_lista_audiencia);
               
      }  
  
           
           
               public OptionalDouble obterPesoIndividualServicoDot(long p_id, List<? extends DOTServico> p_lista_interna) {
          return new PesoDotServico().obterPesoIndividualServicoDot(p_id, p_lista_interna);
               
               }         
           
}
