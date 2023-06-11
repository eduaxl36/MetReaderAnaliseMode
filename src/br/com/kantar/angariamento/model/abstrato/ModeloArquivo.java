/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.abstrato;

import br.com.analise.dot.met.DOTIndividual;
import br.com.angariamento.biblioteca.info.TIPO_AUDIENCIA;
import br.com.kantar.angariamento.model.arquivo.ArquivoMet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Eduardo.Fernando
 */
public abstract class ModeloArquivo {
    
    private TIPO_AUDIENCIA tipo;
    private LocalTime hora;
    private LocalDate data;   

    public ModeloArquivo() {
    }

    public ModeloArquivo(TIPO_AUDIENCIA tipo, LocalTime hora, LocalDate data) {
        this.tipo = tipo;
        this.hora = hora;
        this.data = data;
    }

    public TIPO_AUDIENCIA getTipo() {
        return tipo;
    }

    public LocalTime getHora() {
        return hora;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.tipo);
        hash = 59 * hash + Objects.hashCode(this.hora);
        hash = 59 * hash + Objects.hashCode(this.data);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModeloArquivo other = (ModeloArquivo) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ModeloArquivo{tipo=").append(tipo);
        sb.append(", hora=").append(hora);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    
    
  
    public Set<LocalDate> obterListaDatas(List<? extends DOTServico> p_lista) {
        
        Set var_controle_set_datas_retorno=null;
        try {
            List<DOTIndividual> va_controle_lista = (List<DOTIndividual>) p_lista;
            
            var_controle_set_datas_retorno = va_controle_lista.stream()
            .map(x -> x.getData())
            .collect(Collectors.toSet());

            return var_controle_set_datas_retorno;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_set_datas_retorno;
    }

    
    
}
