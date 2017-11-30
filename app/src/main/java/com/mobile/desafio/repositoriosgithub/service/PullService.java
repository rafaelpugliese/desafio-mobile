package com.mobile.desafio.repositoriosgithub.service;

import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;

public interface PullService {

    Pagina listarPulls(Repositorio repositorio, Integer numeroPagina) throws Exception;

}