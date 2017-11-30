package com.mobile.desafio.repositoriosgithub.asynctask.persistence;

import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.dominio.Pull;
import com.mobile.desafio.repositoriosgithub.service.persistence.PullServicePersistence;

public class SalvarPullsPersistenceAsyncTask extends AbstractPersistenceAsyncTask<Pull, Void, Void> {

    private PullServicePersistence pullServicePersistence;

    public SalvarPullsPersistenceAsyncTask(GitHubActivity context) {
        super(context);

        this.pullServicePersistence = new PullServicePersistence(context);
    }

    @Override
    public Void executeInBackground(Pull[] pulls) {
        this.pullServicePersistence.inserirOuAtualizarTodos(pulls);
        return null;
    }
}