package com.mobile.desafio.repositoriosgithub.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mobile.desafio.repositoriosgithub.R;
import com.mobile.desafio.repositoriosgithub.adapter.RepositorioAdapter;
import com.mobile.desafio.repositoriosgithub.asynctask.ListarRepositoriosAsyncTask;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;

public class MainActivity extends GitHubActivity<Pagina<Repositorio>> implements ActivityCompat.OnRequestPermissionsResultCallback {

    private RepositorioAdapter repositorioAdapter;
    private int PERMISSAO_WRITE = 10;
    private RecyclerView repositorioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        repositorioList = findViewById(R.id.Repositorios_lista);
        repositorioList.addItemDecoration(itemDecorator);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSAO_WRITE);
        }

        ListarRepositoriosAsyncTask listarRepositoriosAsyncTask = new ListarRepositoriosAsyncTask(this);
        listarRepositoriosAsyncTask.execute(1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSAO_WRITE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, R.string.mensagem_permissao_escrita_negada, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void atualizar(Pagina<Repositorio> pagina) {
        if (repositorioAdapter == null) {
            repositorioAdapter = new RepositorioAdapter(this, pagina);
            repositorioList.setAdapter(repositorioAdapter);
        } else if (!pagina.getItens().isEmpty()) {
            repositorioAdapter.setUltimaPagina(pagina);
            repositorioAdapter.addRepositorios(pagina.getItens());
            repositorioList.smoothScrollToPosition(repositorioAdapter.getItemCount() - pagina.getItens().size() + 1);
        }
    }
}