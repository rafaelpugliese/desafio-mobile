package com.mobile.desafio.repositoriosgithub.activity;

import android.app.Instrumentation;
import android.support.test.annotation.UiThreadTest;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.desafio.repositoriosgithub.R;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private Instrumentation inst;
    private ListView listView;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.mainActivity = getActivity();
        this.inst = getInstrumentation();
        this.listView = this.mainActivity.findViewById(R.id.Repositorios_lista);
    }

    @UiThreadTest
    public void testaNomePrimeiroItem() {
        this.inst.waitForIdleSync();
        View item = this.listView.getChildAt(0);
        TextView nome = item.findViewById(R.id.ItemRepositorio_Nome);
        assertEquals("RxJava", nome.getText().toString());
    }

    @UiThreadTest
    public void testaTitulo() {
        assertEquals("GitHub Java", this.getActivity().getTitle().toString());
    }
}
