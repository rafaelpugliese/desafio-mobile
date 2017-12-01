package com.mobile.desafio.repositoriosgithub.repositorios;

import android.app.Instrumentation;
import android.support.test.annotation.UiThreadTest;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.desafio.repositoriosgithub.R;
import com.mobile.desafio.repositoriosgithub.activity.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private Instrumentation inst;
    private RecyclerView recyclerView;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.mainActivity = getActivity();
        this.inst = getInstrumentation();
        this.recyclerView = this.mainActivity.findViewById(R.id.Repositorios_lista);
    }

    @UiThreadTest
    public void testNomePrimeiroItem() {
        this.inst.waitForIdleSync();
        View item = getItem(0);
        TextView nome = item.findViewById(R.id.ItemRepositorio_Nome);
        assertEquals("RxJava", nome.getText().toString());
    }

    @UiThreadTest
    public void testOrdemTresPrimeirosItens() {
        this.inst.waitForIdleSync();
        View itemUm = getItem(0);
        TextView starsUm = getStars(itemUm);

        View itemDois = getItem(1);
        TextView starsDois = getStars(itemDois);

        View itemTres = getItem(2);
        TextView starsTres = getStars(itemTres);

        assertTrue(Integer.valueOf(starsUm.getText().toString()) >= Integer.valueOf(starsDois.getText().toString()));
        assertTrue(Integer.valueOf(starsDois.getText().toString()) >= Integer.valueOf(starsTres.getText().toString()));
    }

    private View getItem(int index) {
        return this.recyclerView.getChildAt(index);
    }

    private TextView getStars(View view) {
        return view.findViewById(R.id.ItemRepositorio_Stars);
    }

    @UiThreadTest
    public void testTitulo() {
        assertEquals("GitHub Java", this.getActivity().getTitle().toString());
    }
}
