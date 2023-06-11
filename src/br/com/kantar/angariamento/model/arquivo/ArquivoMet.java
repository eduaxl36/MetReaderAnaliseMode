/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.arquivo;

import br.com.angariamento.biblioteca.info.TIPO_AUDIENCIA;
import br.com.kantar.angariamento.model.abstrato.ModeloArquivo;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Eduardo.Fernando
 */
public class ArquivoMet extends ModeloArquivo {

    public ArquivoMet() {
    }

    public ArquivoMet(TIPO_AUDIENCIA p_tipo, LocalTime p_hora, LocalDate p_data) {
        super(p_tipo, p_hora, p_data);
    }

    
}
