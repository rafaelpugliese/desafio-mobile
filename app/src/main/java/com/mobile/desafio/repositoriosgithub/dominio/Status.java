package com.mobile.desafio.repositoriosgithub.dominio;

import android.graphics.Color;

import com.mobile.desafio.repositoriosgithub.R;

public enum Status {

    OPEN("Open", R.color.colorOpen), CLOSED("Closed", R.color.colorClosed), MERGED("Merged", R.color.colorMerged);

    private String nome;
    private int cor;

    Status(String nome, int cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public int getCor() {
        return cor;
    }

    public static Status getStatus(Pull pull) {

        if (pull.isMerged()) {
            return Status.MERGED;
        } else if (pull.isOpen()) {
            return Status.OPEN;
        } else {
            return Status.CLOSED;
        }
    }
}