/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.servico.arquivo;

import br.com.angariamento.biblioteca.info.TIPO_AUDIENCIA;
import br.com.kantar.angariamento.model.abstrato.ModeloArquivo;
import br.com.kantar.angariamento.model.arquivo.ArquivoMet;
import br.com.kantar.angariamento.repository.arquivo.ArquivoMetRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eduardo
 */
public class ArquivoMetServico {
    
    
    public LocalDate obterData(String p_entry_point) {

        return new ArquivoMetRepository().obterDataRepository(p_entry_point);
        
    }


    public LocalTime obterHora(String p_entry_point) {

 
        return new ArquivoMetRepository().obterHoraRepository(p_entry_point);
        
    }

    
    public ModeloArquivo retornarObjeto(String p_entrypoint) {
    
     return new ArquivoMetRepository().retornarObjetoRepository(p_entrypoint);
     
    }


    
}
