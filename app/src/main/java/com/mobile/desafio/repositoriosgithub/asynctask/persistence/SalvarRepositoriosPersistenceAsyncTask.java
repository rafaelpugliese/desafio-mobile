package com.mobile.desafio.repositoriosgithub.asynctask.persistence;

import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.service.persistence.RepositorioServicePersistence;

public class SalvarRepositoriosPersistenceAsyncTask extends AbstractPersistenceAsyncTask<Repositorio, Void, Void> {

    private RepositorioServicePersistence repositorioServicePersistence;

    public SalvarRepositoriosPersistenceAsyncTask(GitHubActivity context) {
        super(context);

        this.repositorioServicePersistence = new RepositorioServicePersistence(context);
    }

    @Override
    public Void executeInBackground(Repositorio[] repositorios) {
        this.repositorioServicePersistence.inserirOuAtualizarTodos(repositorios);
        return null;
    }
}