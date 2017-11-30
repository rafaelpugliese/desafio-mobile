package com.mobile.desafio.repositoriosgithub.service.persistence;

import android.content.Context;

import com.mobile.desafio.repositoriosgithub.build.PaginaBuilder;
import com.mobile.desafio.repositoriosgithub.dao.AppDatabase;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Pull;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.service.PullService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

public class PullServicePersistence implements PullService {

    private AppDatabase db;
    private UsuarioServicePersistence usuarioServicePersistence;

    public PullServicePersistence(Context context) {
        this.db = AppDatabase.getAppDatabase(context);
        this.usuarioServicePersistence = new UsuarioServicePersistence(context);
    }

    public Pagina<Pull> listarPulls(Repositorio repositorio, Integer page) throws IOException, URISyntaxException {
        List<Pull> pulls = db.pullDao().findByDonoAndRepositorio(repositorio.getId());
        for (Pull pull : pulls) {
            pull.setDono(this.usuarioServicePersistence.findById(pull.getDonoId()));
        }
        Collections.sort(pulls);
        return PaginaBuilder.getNewInstance().addItens(pulls).addNumero(1).build();
    }

    public void inserirOuAtualizarTodos(Pull... pulls) {
        for (Pull p : pulls) {
            this.usuarioServicePersistence.inserirOuAtualizar(p.getDono());
            Pull pull = this.db.pullDao().findById(p.getId());
            if (pull != null) {
                this.db.pullDao().updateAll(p);
            } else {
                this.db.pullDao().insertAll(p);
            }
        }
    }
}