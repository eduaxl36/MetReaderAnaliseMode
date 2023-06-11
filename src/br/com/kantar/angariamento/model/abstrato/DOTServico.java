/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.abstrato;


import br.com.kantar.angariamento.model.audiencia.Audiencia;
import com.google.common.collect.Multimap;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

/**
 *
 * @author eduardo
 */
public abstract class DOTServico {
    
    private int [] array_canais;
    
    private LocalDate data;
    
    private int total;
    
    private List<Integer> lista_demograficas;
    
    private double peso;
    
    private int tv;
  
    private int medicaoInicial;
    
    private int medicaoFinal;
    
    private int canal;
    
    private List<Audiencia>lista_obj_geral_audiencia;

    public DOTServico() {
    }

    
    public DOTServico(int[] p_array_canais,int p_medicaoInicial, int p_medicaoFinal,List<Audiencia>p_lista_obj_geral_audiencia) {
    
        this.array_canais = p_array_canais;
        this.medicaoInicial = p_medicaoInicial;
        this.medicaoFinal = p_medicaoFinal;
        this.lista_obj_geral_audiencia = p_lista_obj_geral_audiencia;
          
    }

    public DOTServico(int p_canal,int p_tv,LocalDate p_data, int p_total, List<Integer> p_lista_demograficas, double p_peso) {
        this.data = p_data;
        this.total = p_total;
        this.lista_demograficas = p_lista_demograficas;
        this.peso = p_peso;
        this.tv =    p_tv;
        this.canal = p_canal;
    }

    public DOTServico(int p_canal,int[] p_array_canais, LocalDate p_data, int p_total, List<Integer> p_lista_demograficas, double p_peso,int tv, int p_medicaoInicial, int p_medicaoFinal,List<Audiencia>p_lista_obj_geral_audiencia) {
    
        this.array_canais = p_array_canais;
        this.data = p_data;
        this.total = p_total;
        this.lista_demograficas = p_lista_demograficas;
        this.peso = p_peso;
        this.medicaoInicial = p_medicaoInicial;
        this.medicaoFinal = p_medicaoFinal;
        this.lista_obj_geral_audiencia = p_lista_obj_geral_audiencia;
        this.tv = tv;
        this.canal = p_canal;
                
        
    }
 
    public int getTv() {
        return tv;
    }

    public int getCanalSingular() {
        return canal;
    }

    public int[] getNcanal() {
        return array_canais;
    }

    public LocalDate getData() {
        return data;
    }

    public int getTotal() {
        return total;
    }

    public List<Integer> getDemografica() {
        return lista_demograficas;
    }

    public double getPeso() {
        return peso;
    }

    public int getMedicaoInicial() {
        return medicaoInicial;
    }

    public int getMedicaoFinal() {
        return medicaoFinal;
    }

    public List<Audiencia> getLista_obj_geral_audiencia() {
        return lista_obj_geral_audiencia;
    }


    @Override
    public int hashCode() {
        int hash = 3;
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
        final DOTServico other = (DOTServico) obj;
        if (this.total != other.total) {
            return false;
        }
        if (Double.doubleToLongBits(this.peso) != Double.doubleToLongBits(other.peso)) {
            return false;
        }
  
        if (this.medicaoInicial != other.medicaoInicial) {
            return false;
        }
        if (this.medicaoFinal != other.medicaoFinal) {
            return false;
        }

        if (!Arrays.equals(this.array_canais, other.array_canais)) {
            return false;
        }

        if (!Objects.equals(this.lista_demograficas, other.lista_demograficas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(", array_canais=").append(array_canais);
        sb.append(", data=").append(data);
        sb.append(", total=").append(total);
        sb.append(", lista_demograficas=").append(lista_demograficas);
        sb.append(", peso=").append(peso);
     
        sb.append(", medicaoInicial=").append(medicaoInicial);
        sb.append(", medicaoFinal=").append(medicaoFinal);
        sb.append('}');
        return sb.toString();
    }

    public abstract List<? extends DOTServico> geraObjetoDot(int []p_arraycanais,int p_minutoInicial, int p_minutoFinal);
    
    public abstract Set<Long>obterListaId(List<? extends DOTServico>p_lista_servicoDot);

    public abstract Multimap cronogramaDeImpressao();
   
    public abstract List<? extends DOTServico> criaListaGeralObjetos(List<? extends DOTServico> p_lista_servicoDot,int p_arraycanais[]);
    
    public abstract List<? extends DOTServico> criarObjetoDotPorId(List<? extends DOTServico> p_lista_servicoDot, long p_id, LocalDate p_data, int p_arraycanais[]); 
    
    public abstract int obterTotal(List<? extends DOTServico> p_lista_servico_dot,Long p_id, LocalDate p_data, int p_canal, int p_tv);
    
    
    public Set<LocalDate> obterListaDatas(List<? extends DOTServico> p_lista) {
        
        Set var_controle_datas_retorno_lista =null;
        
        try {
            List<? extends DOTServico> lstinterno = (List<? extends DOTServico>) p_lista;
            
            var_controle_datas_retorno_lista = lstinterno.stream()
            .map(x -> x.getData())
            .collect(Collectors.toSet());

            return var_controle_datas_retorno_lista;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return var_controle_datas_retorno_lista;
    }
    

 public Map<LocalDate,File>geraCaminhosOrdenadosPorData(File[]p_arquivos) throws Exception{
 
     
 Map<LocalDate,File>var_controle_mapa_internoDatas_retorno= new TreeMap();

 for(File l_arquivo_loop:p_arquivos)
 {
 DateTimeFormatter var_controle_formatter_data = DateTimeFormatter.BASIC_ISO_DATE;
 
 String var_controle_fatiamento_arquivo =  l_arquivo_loop.getName().substring(0,8);
 LocalDate var_controle_data_arquivo = LocalDate.parse(var_controle_fatiamento_arquivo,var_controle_formatter_data);
 var_controle_mapa_internoDatas_retorno.put(var_controle_data_arquivo, l_arquivo_loop);
 
 }    
 

 return var_controle_mapa_internoDatas_retorno;
 
 }
    
    
  public String obterCabecalho(Map<LocalDate,File>p_valores,int p_tamanhoDemografica)
    {
    
        List lstInterna = new LinkedList();
        
        try {
            
            StringBuilder sbDem = new StringBuilder();
            
            for(int i=1;i<=p_tamanhoDemografica;i++)
            {
            
            sbDem.append("D"+i+",");
                
            }
            
            String temp = sbDem.substring(0,sbDem.length()-1);
            
            String info = "DOM;CANAL;TV;PESO;";
            
            for(File p_arquivo:p_valores.values())
            {
            
            lstInterna.add(p_arquivo.getName().substring(0,8));
            
            }
            
            String finalLine = info+temp+";"+lstInterna.toString().replaceAll("\\[|\\]", "").replaceAll(",", ";");
   
            return finalLine;
            
        } catch (Exception e) {
        
            e.printStackTrace();
        
        }
    
    return "";
    }
    
    
    
    
    
    

}
