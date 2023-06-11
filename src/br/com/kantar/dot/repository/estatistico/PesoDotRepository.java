/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.dot.repository.estatistico;

import br.com.analise.dot.met.DOTDomiciliar;
import br.com.analise.dot.met.DOTIndividual;
import br.com.kantar.angariamento.model.abstrato.DOTServico;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;

/**
 *
 * @author eduardo
 */
public class PesoDotRepository {
    
    
    
   
    
     public OptionalDouble obterPesoDomiciliarServicoDot(long p_id, List<DOTDomiciliar> p_lista_audiencia) {
   
         
        DoubleStream var_controle_ocorrencia=null;
        try {

            var_controle_ocorrencia = p_lista_audiencia.
                    stream().filter(x -> x.getId_domicilio() == p_id)
                    .mapToDouble(x -> x.getPeso());

            return var_controle_ocorrencia.min();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return var_controle_ocorrencia.min();

    } 
    
     
     
     
    public OptionalDouble obterPesoIndividualServicoDot(long p_id, List<? extends DOTServico> p_lista_interna) {
        
        List<DOTIndividual> var_controle_lista_retorno = (List<DOTIndividual>) p_lista_interna;
        
        DoubleStream var_controle_ocorrencia=null;
        try {

            var_controle_ocorrencia = var_controle_lista_retorno.
                    stream()
                    .filter(x -> x.getId_individuo() == p_id)
                    .mapToDouble(x -> x.getPeso());

            return var_controle_ocorrencia.min();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return var_controle_ocorrencia.min();

    }   
}
