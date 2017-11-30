package com.mobile.desafio.repositoriosgithub.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mobile.desafio.repositoriosgithub.R;
import com.mobile.desafio.repositoriosgithub.adapter.PullAdapter;
import com.mobile.desafio.repositoriosgithub.asynctask.ListarPullsAsyncTask;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.sandbox.SandBox;

public class PullsActivity extends GitHubActivity<Pagina> {

    public final static String EXTRA_REPOSITORIO = "repositorio";

    private RecyclerView pullsList;
    private PullAdapter pullAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulls);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        pullsList = findViewById(R.id.Pulls_lista);
        pullsList.addItemDecoration(itemDecorator);

        Repositorio repositorio = (Repositorio) getIntent().getExtras().get(EXTRA_REPOSITORIO);

        setTitle(repositorio.getNome());

        SandBox sandBox = new SandBox(this);
        sandBox.salvaRepositorio(repositorio);

        ListarPullsAsyncTask listarPullsAsyncTask = new ListarPullsAsyncTask(this);
        listarPullsAsyncTask.execute(1);
    }

    @Override
    public void atualizar(Pagina pagina) {

        if (pullAdapter == null) {
            if (pagina.getItens().isEmpty()) {
                Toast.makeText(this, R.string.pulls_vazios, Toast.LENGTH_LONG).show();
            } else {
                pullAdapter = new PullAdapter(this, pagina);
                pullsList.setAdapter(pullAdapter);
            }
        } else if (!pagina.getItens().isEmpty()) {
            pullAdapter.addPulls(pagina.getItens());
            pullAdapter.setUltimaPagina(pagina);
            pullsList.smoothScrollToPosition(pullAdapter.getItemCount() - pagina.getItens().size() + 1);
        }

    }
}