package com.mobile.desafio.repositoriosgithub.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mobile.desafio.repositoriosgithub.dominio.Pull;

import java.util.List;

@Dao
public interface PullDao {

    @Query("SELECT * FROM pull")
    List<Pull> getAll();

    @Query("SELECT * FROM pull WHERE id LIKE :id LIMIT 1")
    Pull findById(Integer id);

    @Query("SELECT * FROM pull WHERE repositorioId LIKE :repositorioId")
    List<Pull> findByDonoAndRepositorio(Integer repositorioId);

    @Update
    void updateAll(Pull... pulls);

    @Insert
    void insertAll(Pull... pulls);
}