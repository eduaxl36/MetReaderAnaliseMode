/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.util.aud.normalizador.cov;


import static br.com.kantar.angariamento.util.aud.normalizador.cov.Singular.transformaAudienciaSingularEmMapa;
import java.util.Map;


/**
 *
 * @author eduardo
 */
public class entryPointCov {
      
  public  Map getAudienciaTratada(String p_entry_point){
      
      Map var_controle_mapa_duplicado_retorno =null;
              
      try {
          
            var_controle_mapa_duplicado_retorno  =new TratamentoCov().obterAudienciasDesagrupadasParaValoresDoMapa(
            new TratamentoCov().agrupaAudienciaPorTvCanal(
            new TratamentoCov().insereAudienciaMapeadaEmLista(
            p_entry_point, new TratamentoCov().mapeiaRepeticoesDeAudienciaMaiorQueUm(p_entry_point))));  

            Map var_controle_mapa_singular = new Singular().geracaoFinalDaAudiencia(
            transformaAudienciaSingularEmMapa(
            new Singular().converteChaveDoMapaEmLista(
            p_entry_point, new Singular().entryPointMapeamentoSingular(p_entry_point))));  

            var_controle_mapa_duplicado_retorno.putAll(var_controle_mapa_singular);
            
            return var_controle_mapa_duplicado_retorno;
            
           
      } catch (Exception e) {
      
      e.printStackTrace();
      
      }
      
      
      return var_controle_mapa_duplicado_retorno;


}
  
  
    public static void main(String[] args) {
        System.out.println(new entryPointCov().getAudienciaTratada("01000000006XA34B48A204B66A199B39A79B193A2B21A555F\n" +
"01000000004XA82B147A123B199A39B5A37B37A193B2A22B36A518F\n" +
"01000000358XA885B1A36B58A460F\n" +
"02000000018XA16B128A65B41A38B14A78B9A289B70A22B247A423F\n" +
"02000000992XA636B2A802F\n" +
"02000000004XA144B18A244B38A996F\n" +
"02000000006XA389B17A1034F\n" +
"02000000001XA630B6A804F").keySet());
    }
  
}
