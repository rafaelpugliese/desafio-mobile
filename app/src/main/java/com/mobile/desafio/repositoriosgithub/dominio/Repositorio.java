package com.mobile.desafio.repositoriosgithub.dominio;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Repositorio implements Serializable, Comparable<Repositorio> {

    @PrimaryKey
    private Integer id;

    @ColumnInfo
    private String nome;

    @ColumnInfo
    private String descricao;

    @ColumnInfo
    private Integer donoId;

    @Ignore
    private Usuario dono;

    @ColumnInfo
    private Integer forks;

    @ColumnInfo
    private Integer stars;

    public Repositorio() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
        if (dono != null) {
            setDonoId(dono.getId());
        }
    }

    public Usuario getDono() {
        return dono;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public Integer getForks() {
        return forks;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getStars() {
        return stars;
    }

    public void setDonoId(Integer donoId) {
        this.donoId = donoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDonoId() {
        return donoId;
    }

    @Override
    public int compareTo(@NonNull Repositorio repositorio) {
        return repositorio.getStars() - this.getStars();
    }
}