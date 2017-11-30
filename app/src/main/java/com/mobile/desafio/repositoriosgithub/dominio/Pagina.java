package com.mobile.desafio.repositoriosgithub.dominio;

import java.io.Serializable;
import java.util.List;

public class Pagina<T> implements Serializable {

    private int numero;

    private List<T> itens;

    private Integer proxima;

    public int getNumero() {
        return numero;
    }

    public void setItens(List<T> itens) {
        this.itens = itens;
    }

    public List<T> getItens() {
        return itens;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setProxima(Integer proxima) {
        this.proxima = proxima;
    }

    public Integer getProxima() {
        return proxima;
    }

    public boolean existeProxima() {
        return proxima != null;
    }
}