package com.mobile.desafio.repositoriosgithub.dominio;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Pull implements Serializable, Comparable<Pull> {

    @PrimaryKey
    private Integer id;

    @ColumnInfo
    private String descricao;

    @Ignore
    private Usuario dono;

    @ColumnInfo
    private Integer donoId;

    @Ignore
    private Repositorio repositorio;

    @ColumnInfo
    private Integer repositorioId;

    @ColumnInfo
    private String titulo;

    @ColumnInfo
    private String html;

    @ColumnInfo
    private boolean open;

    @ColumnInfo
    private boolean merged;

    @ColumnInfo
    private String dataCriacao;

    public Pull() {
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getHtml() {
        return html;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isMerged() {
        return merged;
    }

    public boolean isOpen() {
        return open;
    }

    public Integer getDonoId() {
        return donoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDonoId(Integer donoId) {
        this.donoId = donoId;
    }

    public Integer getRepositorioId() {
        return repositorioId;
    }

    public Repositorio getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(Repositorio repositorio) {
        this.repositorio = repositorio;
        if (repositorio != null) {
            setRepositorioId(repositorio.getId());
        }
    }

    public void setRepositorioId(Integer repositorioId) {
        this.repositorioId = repositorioId;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public int compareTo(@NonNull Pull pull) {
        return pull.getDataCriacao().compareTo(this.dataCriacao);
    }
}