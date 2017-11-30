package com.mobile.desafio.repositoriosgithub.service;

import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;

public interface RepositorioService {

    Pagina<Repositorio> listarRepositorios(Integer numeroPagina) throws Exception;

}