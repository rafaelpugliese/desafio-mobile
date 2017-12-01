package com.mobile.desafio.repositoriosgithub.service.persistence;

import android.content.Context;

import com.mobile.desafio.repositoriosgithub.dao.AppDatabase;
import com.mobile.desafio.repositoriosgithub.dominio.Usuario;

public class UsuarioServicePersistence {

    private AppDatabase db;

    public UsuarioServicePersistence(Context context) {
        this.db = AppDatabase.getAppDatabase(context);
    }

    public void inserirOuAtualizar(Usuario usuario) {
        Usuario u = findById(usuario.getId());

        if (u != null) {
            this.db.usuarioDao().updateAll(usuario);
        } else {
            this.db.usuarioDao().insertAll(usuario);
        }
    }

    public Usuario findById(Integer id) {
        return this.db.usuarioDao().findById(id);
    }

}