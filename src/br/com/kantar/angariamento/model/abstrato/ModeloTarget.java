/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.abstrato;



import br.com.kantar.angariamento.model.estatistico.Demografica;
import br.com.kantar.angariamento.model.estatistico.Peso;


/**
 *
 * @author Eduardo.Fernando
 */
public abstract class ModeloTarget {

    public ModeloTarget() {
    }
    
    private long id;
    private Demografica demografica;
    private  Peso peso;   

    public ModeloTarget(long id) {
        this.id = id;
    }

    public ModeloTarget(long id, Demografica demografica, Peso peso) {
        this.id = id;
        this.demografica = demografica;
        this.peso = peso;
    }

    public long getId() {
        return id;
    }

    public Demografica getDemografica() {
        return demografica;
    }

    public Peso getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "EntidadeAferivel{" + "id=" + id + ", demografica=" + demografica + ", peso=" + peso + '}';
    }


  

}
