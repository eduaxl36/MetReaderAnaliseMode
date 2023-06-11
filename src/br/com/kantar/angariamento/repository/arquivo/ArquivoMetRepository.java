/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.angariamento.repository.arquivo;

import br.com.angariamento.biblioteca.info.TIPO_AUDIENCIA;
import br.com.kantar.angariamento.model.abstrato.ModeloArquivo;
import br.com.kantar.angariamento.model.arquivo.ArquivoMet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author eduardo
 */
public class ArquivoMetRepository {
    
 /**
 Metodo resposavel por retornar uma data, vinda de um rawString (diretamente da leitura crua do arquivo)
 @return LocalDate
  */
    public LocalDate obterDataRepository(String p_entry_point) {

           LocalDate var_controle_dt_temp = null; 
           Matcher var_controle_exp_regex = Pattern.compile("I\\d{1,}").matcher(p_entry_point);

            if (var_controle_exp_regex.find()) {

                DateTimeFormatter var_controle_formatter_data = DateTimeFormatter.BASIC_ISO_DATE;

                var_controle_dt_temp = LocalDate.parse(var_controle_exp_regex.group().substring(9, 17), var_controle_formatter_data);

                return var_controle_dt_temp;
            }


        return var_controle_dt_temp;
    }

 /**
 Metodo resposavel por retornar uma hora, vinda de um rawString (diretamente da leitura crua do arquivo)
 @return LocalTime
 */
    public LocalTime obterHoraRepository(String p_entry_point) {

        LocalTime var_controle_hr_temp = null;

            Matcher var_controle_exp_regex = Pattern.compile("I\\d{1,}").matcher(p_entry_point);

            if (var_controle_exp_regex.find()) {

                var_controle_hr_temp = LocalTime.parse(var_controle_exp_regex.group().substring(17, 19) + ":" + var_controle_exp_regex.group().substring(19, 21));

                return var_controle_hr_temp;
            }


        return var_controle_hr_temp;
    }
    
   
 /**
Metodo responsavel por retornar um objeto instanciado do arquivo MET, vinda de um rawString (diretamente da leitura crua do arquivo). Este metodo utiliza obterHora e obterData para criação da instancia. 
@return ModeloArquivo
*/  
    
   public ModeloArquivo retornarObjetoRepository(String p_entrypoint) {

            ArquivoMet var_controle_obj_aud = null;


            LocalDate var_controle_data = obterDataRepository(p_entrypoint);
            LocalTime var_controle_hora = obterHoraRepository(p_entrypoint);

            if (var_controle_hora.toString().equals("06:00")) {

                var_controle_obj_aud = new ArquivoMet(TIPO_AUDIENCIA.I6, var_controle_hora, var_controle_data);

            } else {

                var_controle_obj_aud = new ArquivoMet(TIPO_AUDIENCIA.I2, var_controle_hora, var_controle_data);

            }

            return var_controle_obj_aud;

    }  
    
    
}
