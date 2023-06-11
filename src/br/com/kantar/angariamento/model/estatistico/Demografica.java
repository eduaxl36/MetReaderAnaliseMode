/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.kantar.angariamento.model.estatistico;




import java.util.List;

import java.util.Objects;

/**
 *
 * @author Eduardo.Fernando
 */
public class Demografica { 

    public Demografica() {
    }

    
private List<Integer>listaDemograficas;

    public List<Integer> getListaDemograficas() {
        return listaDemograficas;
    }

    public Demografica(List<Integer> listaDemograficas) {
        this.listaDemograficas = listaDemograficas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.listaDemograficas);
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
        final Demografica other = (Demografica) obj;
        if (!Objects.equals(this.listaDemograficas, other.listaDemograficas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return listaDemograficas.toString();
    }


    
    
    
}
