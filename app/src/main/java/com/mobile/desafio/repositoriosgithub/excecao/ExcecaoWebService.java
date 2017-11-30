package com.mobile.desafio.repositoriosgithub.excecao;

import android.util.Log;

public class ExcecaoWebService extends RuntimeException {

    private static final String LOG_TAG = ExcecaoWebService.class.getName();


    public ExcecaoWebService(String mensagem) {
        super(mensagem);
        Log.e(LOG_TAG, mensagem, new Throwable(mensagem));
    }
}
