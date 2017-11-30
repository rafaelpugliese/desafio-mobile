package com.mobile.desafio.repositoriosgithub.asynctask;

import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.asynctask.persistence.SalvarPullsPersistenceAsyncTask;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Pull;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.sandbox.SandBox;
import com.mobile.desafio.repositoriosgithub.service.FabricaService;
import com.mobile.desafio.repositoriosgithub.service.PullService;

public class ListarPullsAsyncTask extends AbstractAsyncTask<Integer, Void, Pagina<Pull>> {

    private PullService pullService;

    public ListarPullsAsyncTask(GitHubActivity activity) {
        super(activity);
        this.pullService = FabricaService.getPullServico(activity, isConectado());
    }

    @Override
    protected Pagina<Pull> executeInBackground(Integer[] params) throws Exception {
        SandBox sandBox = new SandBox(getActivity());
        Repositorio repositorio = sandBox.getRepositorio();
        return this.pullService.listarPulls(repositorio, params[0]);
    }

    @Override
    protected void onPostExecuteOnline(Pagina<Pull> pagina) {
        Pull[] pulls = pagina.getItens().toArray(new Pull[pagina.getItens().size()]);
        new SalvarPullsPersistenceAsyncTask(getActivity()).execute(pulls);
        getActivity().atualizar(pagina);
    }

    @Override
    protected void onPostExecuteOffline(Pagina<Pull> pagina) {
        getActivity().atualizar(pagina);

    }

}