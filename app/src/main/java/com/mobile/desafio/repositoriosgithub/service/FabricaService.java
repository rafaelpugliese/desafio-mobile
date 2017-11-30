package com.mobile.desafio.repositoriosgithub.service;

import android.content.Context;

import com.mobile.desafio.repositoriosgithub.service.persistence.PullServicePersistence;
import com.mobile.desafio.repositoriosgithub.service.persistence.RepositorioServicePersistence;
import com.mobile.desafio.repositoriosgithub.service.request.PullServiceRequest;
import com.mobile.desafio.repositoriosgithub.service.request.RepositorioServiceRequest;

public class FabricaService {

    public static RepositorioService getRepositorioServico(Context context, boolean conectado) {
        if (conectado) {
            return new RepositorioServiceRequest();
        } else {
            return new RepositorioServicePersistence(context);
        }
    }

    public static PullService getPullServico(Context context, boolean conectado) {
        if (conectado) {
            return new PullServiceRequest();
        } else {
            return new PullServicePersistence(context);
        }
    }
}