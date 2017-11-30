package com.mobile.desafio.repositoriosgithub.service;

import com.mobile.desafio.repositoriosgithub.dominio.Pagina;

public interface RepositorioService {

    Pagina listarRepositorios(Integer numeroPagina) throws Exception;

}