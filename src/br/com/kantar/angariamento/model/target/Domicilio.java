/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.target;


import br.com.kantar.angariamento.model.abstrato.ModeloTarget;
import br.com.kantar.angariamento.model.estatistico.Demografica;


import br.com.kantar.angariamento.model.estatistico.Peso;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Domicilio extends ModeloTarget {

    public Domicilio() {
    }

    public Domicilio(int id) {
        super(id);
    }

    public Domicilio(int id, Demografica demografica, Peso peso) {
        super(id, demografica, peso);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('}');
        return sb.toString();
    }


     
    
  
}
