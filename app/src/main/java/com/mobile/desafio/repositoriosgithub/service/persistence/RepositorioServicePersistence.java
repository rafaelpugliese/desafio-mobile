package com.mobile.desafio.repositoriosgithub.service.persistence;

import android.content.Context;

import com.mobile.desafio.repositoriosgithub.build.PaginaBuilder;
import com.mobile.desafio.repositoriosgithub.dao.AppDatabase;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.service.RepositorioService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

public class RepositorioServicePersistence implements RepositorioService {

    private AppDatabase db;
    private UsuarioServicePersistence usuarioServicePersistence;

    public RepositorioServicePersistence(Context context) {
        this.db = AppDatabase.getAppDatabase(context);
        this.usuarioServicePersistence = new UsuarioServicePersistence(context);
    }

    public Pagina listarRepositorios(Integer numero) throws IOException, URISyntaxException {
        List<Repositorio> repositorios = db.repositorioDao().getAll();
        for (Repositorio repositorio : repositorios) {
            repositorio.setDono(this.usuarioServicePersistence.findById(repositorio.getDonoId()));
        }
        Collections.sort(repositorios);
        return PaginaBuilder.getNewInstance().addItens(repositorios).addNumero(1).build();
    }

    public void inserirOuAtualizarTodos(Repositorio... repositorios) {
        for (Repositorio r : repositorios) {
            this.usuarioServicePersistence.inserirOuAtualizar(r.getDono());
            Repositorio repositorio = this.db.repositorioDao().findById(r.getId());
            if (repositorio != null) {
                this.db.repositorioDao().updateAll(r);
            } else {
                this.db.repositorioDao().insertAll(r);
            }
        }
    }

}