/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.util.aud.normalizador.cov;

import br.com.angariamento.servicos.audiencia.geral.LinhaAudiencia;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author eduardo
 */
public class TratamentoCovUtil {

    private TratamentoCovUtil() {
    }
    
      public static int retornaModalidade(String p_entrypoint) {

           
        Map var_controle_mapa_result = new servicoUtil().transformaAudStringToMap(p_entrypoint);

        for (Object l_result : var_controle_mapa_result.keySet()) {

            if (Integer.parseInt(var_controle_mapa_result.get(l_result).toString()) > 1) {

               return 2;

            }

        }

        return 1;

    }

    public static Multimap converteMapaDeAudienciaCruaEmConcreta(Multimap AudienciaCruaMapeada, Object Audiencia) {

        Multimap AudienciaConcretaMapeada = ArrayListMultimap.create();

        String AudienciaTratamento = AudienciaCruaMapeada.get(Audiencia).toString().replaceAll("\\[|\\]", "").trim().replaceAll("\\s", "");

        String AudienciasEmTratamento[] = AudienciaTratamento.split(",");

        for (String TratamentoAudiencia : AudienciasEmTratamento) {

            if (TratamentoAudiencia.contains("X")) {
                AudienciaConcretaMapeada.put(TratamentoAudiencia, new LinhaAudiencia().transformaLinhaEmAudiencia(TratamentoAudiencia));
            }

        }

        return AudienciaConcretaMapeada;

    }
    
    
    public static List audienciaParaListaIndividual(Multimap AudienciasConcretasMapeadas) {
        
        List Audiencias = new ArrayList();

        int ContadorLoop = 1;

        for (Object AudienciaConcreta : AudienciasConcretasMapeadas.values()) {

            if (ContadorLoop == 1) {

                List AudienciasIsoladas = (List) AudienciaConcreta;

                for (int i = 0; i < AudienciasIsoladas.size(); i++) {

                    Audiencias.add(AudienciasIsoladas.get(i));

                }

            } else {

                List AudienciasIsoladas = (List) AudienciaConcreta;

                for (int i = 0; i < AudienciasIsoladas.size(); i++) {

                    if ((AudienciasIsoladas.get(i).toString().contains("1"))) {

                        if (Audiencias.get(i).toString().equals("1")) {

                        } else {

                            Audiencias.set(i, AudienciasIsoladas.get(i));
                        }

                    }

                }

            }

            ContadorLoop++;
        }
        
        return Audiencias;
    }


}
