package com.mobile.desafio.repositoriosgithub.activity;

import android.app.Activity;

public abstract class GitHubActivity<T> extends Activity {

    public abstract void atualizar(T o);
}