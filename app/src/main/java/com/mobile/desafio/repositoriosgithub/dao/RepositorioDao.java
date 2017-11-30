package com.mobile.desafio.repositoriosgithub.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;

import java.util.List;

@Dao
public interface RepositorioDao {

    @Query("SELECT * FROM repositorio")
    List<Repositorio> getAll();

    @Query("SELECT * FROM repositorio WHERE id LIKE :id LIMIT 1")
    Repositorio findById(Integer id);

    @Query("SELECT * FROM repositorio WHERE nome LIKE :nome LIMIT 1")
    Repositorio findByName(String nome);

    @Update
    void updateAll(Repositorio... repositorios);

    @Insert
    void insertAll(Repositorio... repositorios);
}