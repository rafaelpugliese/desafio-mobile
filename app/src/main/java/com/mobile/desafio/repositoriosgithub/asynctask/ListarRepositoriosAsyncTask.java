package com.mobile.desafio.repositoriosgithub.asynctask;

import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.asynctask.persistence.SalvarRepositoriosPersistenceAsyncTask;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.service.FabricaService;
import com.mobile.desafio.repositoriosgithub.service.RepositorioService;

public class ListarRepositoriosAsyncTask extends AbstractAsyncTask<Integer, Void, Pagina<Repositorio>> {

    private RepositorioService repositorioService;

    public ListarRepositoriosAsyncTask(GitHubActivity activity) {
        super(activity);

        this.repositorioService = FabricaService.getRepositorioServico(activity, isConectado());
    }

    @Override
    protected Pagina executeInBackground(Integer[] parms) throws Exception {
        return this.repositorioService.listarRepositorios(parms[0]);
    }

    @Override
    protected void onPostExecuteOnline(Pagina<Repositorio> pagina) {
        Repositorio[] repositorios = pagina.getItens().toArray(new Repositorio[pagina.getItens().size()]);
        new SalvarRepositoriosPersistenceAsyncTask(getActivity()).execute(repositorios);
        getActivity().atualizar(pagina);
    }

    @Override
    protected void onPostExecuteOffline(Pagina pagina) {
        getActivity().atualizar(pagina);
    }

}