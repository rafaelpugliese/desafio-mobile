package com.mobile.desafio.repositoriosgithub.sandbox;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SandBoxTest {

    @Mock
    private Context mMockContext;

    @Mock
    private SharedPreferences sharedPreferences;

    @Mock
    private SharedPreferences.Editor editor;

    @Test
    public void salvaRepositorio() throws Exception {
        Repositorio repositorio = new Repositorio();
        repositorio.setNome("teste");
        when(mMockContext.getApplicationContext()).thenReturn(mMockContext);
        when(mMockContext.getApplicationContext().getPackageName()).thenReturn("com.mobile.desafio.repositoriosgithub");
        when(mMockContext.createPackageContext("com.mobile.desafio.repositoriosgithub", 0)).thenReturn(mMockContext);
        when(mMockContext.getSharedPreferences("GitHubPreferencesFile", Context.MODE_PRIVATE)).thenReturn(sharedPreferences);
        when(sharedPreferences.edit()).thenReturn(editor);
        when(sharedPreferences.getString("repositorio", null)).thenReturn(new Gson().toJson(repositorio));

        SandBox sandBox = new SandBox(mMockContext);

        sandBox.salvaRepositorio(repositorio);
        assertEquals("teste", sandBox.getRepositorio().getNome());
    }

    @Test
    public void getRepositorio() throws Exception {
    }

}