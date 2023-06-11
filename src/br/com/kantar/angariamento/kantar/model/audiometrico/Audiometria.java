/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.kantar.model.audiometrico;



import java.util.Map;
import java.util.Objects;

/**
 *
 * @author eduardo
 */
public class Audiometria {
    
 private Map<Integer,Integer>sintonias; 

    public Audiometria(Map<Integer, Integer> sintonias) {
        this.sintonias = sintonias;
    }

    @Override
    public String toString() {
        return "Sintonia{" + "sintonias=" + sintonias + '}';
    }

    public Map<Integer,Integer> getSintonias() {
        return sintonias;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.sintonias);
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
        final Audiometria other = (Audiometria) obj;
        if (!Objects.equals(this.sintonias, other.sintonias)) {
            return false;
        }
        return true;
    }
 
  

}
