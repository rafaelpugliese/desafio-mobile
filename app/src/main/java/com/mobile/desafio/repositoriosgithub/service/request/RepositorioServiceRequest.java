package com.mobile.desafio.repositoriosgithub.service.request;

import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.provedor.RepositorioProvedorDados;
import com.mobile.desafio.repositoriosgithub.service.RepositorioService;

import java.io.IOException;
import java.net.URISyntaxException;

public class RepositorioServiceRequest implements RepositorioService {

    private RepositorioProvedorDados repositorioProvedorDados;

    public RepositorioServiceRequest() {
        this.repositorioProvedorDados = new RepositorioProvedorDados();
    }

    public Pagina listarRepositorios(Integer numeroPagina) throws IOException, URISyntaxException {
        return this.repositorioProvedorDados.getRepositorios(numeroPagina);
    }

}