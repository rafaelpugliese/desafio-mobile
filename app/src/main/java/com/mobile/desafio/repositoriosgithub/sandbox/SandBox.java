package com.mobile.desafio.repositoriosgithub.sandbox;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import com.google.gson.Gson;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;

public class SandBox {

    private final static String REPOSITORIO = "repositorio";

    private SharedPreferences sharedPreferences;
    private static final String PREFERENCES_FILE = "GitHubPreferencesFile";

    public SandBox(Context context) {

        try {
            context = context.createPackageContext(context.getApplicationContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public void set(String chave, String valor) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(chave, valor);
        editor.apply();
    }

    public void salvaRepositorio(Repositorio repositorio) {
        set(REPOSITORIO, new Gson().toJson(repositorio));
    }

    public Repositorio getRepositorio() {

        String repositorios = sharedPreferences.getString(REPOSITORIO, null);

        return new Gson().fromJson(repositorios, Repositorio.class);
    }
}