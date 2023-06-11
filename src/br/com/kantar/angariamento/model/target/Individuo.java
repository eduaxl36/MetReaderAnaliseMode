/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.target;



import br.com.kantar.angariamento.model.afericao.Afericao;
import br.com.kantar.angariamento.model.abstrato.ModeloTarget;
import br.com.kantar.angariamento.model.estatistico.Demografica;
import br.com.kantar.angariamento.model.estatistico.Peso;
import java.util.List;



/**
 *
 * @author eduardo
 */
public class Individuo extends ModeloTarget {

    private List<Afericao> afericao;

    public Individuo() {
    }

    public Individuo(List<Afericao> afericao, long id, Demografica demografica, Peso peso) {
        super(id, demografica, peso);
        this.afericao = afericao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Individuo{afericao=").append(afericao);
        sb.append('}');
        return sb.toString();
    }

    public List<Afericao>  getAfericao() {
        return afericao;
    }

  
  
  
}
