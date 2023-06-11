/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.controller.arquivo;

import br.com.kantar.angariamento.model.abstrato.ModeloArquivo;
import br.com.kantar.angariamento.servico.arquivo.ArquivoMetServico;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author eduardo
 */
public class arquivoMetController {
    
    
     public LocalDate obterData(String p_entry_point) {
     
         return new ArquivoMetServico().obterData(p_entry_point);
     
     }
     
      public LocalTime obterHora(String p_entry_point) {
          
     return new ArquivoMetServico().obterHora(p_entry_point);
      
      }
      
      
      public ModeloArquivo retornarObjeto(String p_entrypoint) {
      
      return new ArquivoMetServico().retornarObjeto(p_entrypoint);
      
      }   
      
}
