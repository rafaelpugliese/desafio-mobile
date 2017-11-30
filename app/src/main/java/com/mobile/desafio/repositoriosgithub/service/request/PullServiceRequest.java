package com.mobile.desafio.repositoriosgithub.service.request;

import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.provedor.PullProvedorDados;
import com.mobile.desafio.repositoriosgithub.service.PullService;

import java.io.IOException;
import java.net.URISyntaxException;

public class PullServiceRequest implements PullService {

    private PullProvedorDados pullProvedorDados;

    public PullServiceRequest() {
        this.pullProvedorDados = new PullProvedorDados();
    }

    public Pagina listarPulls(Repositorio repositorio, Integer numeroPagina) throws IOException, URISyntaxException {
        return this.pullProvedorDados.getPulls(repositorio, numeroPagina);
    }
}