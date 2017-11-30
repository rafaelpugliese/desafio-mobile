package com.mobile.desafio.repositoriosgithub.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.dominio.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    @Query("SELECT * FROM usuario WHERE id LIKE :id LIMIT 1")
    Usuario findById(Integer id);

    @Query("SELECT * FROM usuario WHERE nome LIKE :nome LIMIT 1")
    Usuario findByNome(String nome);

    @Update
    void updateAll(Usuario... usuarios);

    @Insert
    void insertAll(Usuario... usuarios);

}