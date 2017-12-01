package com.mobile.desafio.repositoriosgithub.pulls;

import android.app.Instrumentation;
import android.support.test.annotation.UiThreadTest;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.mobile.desafio.repositoriosgithub.R;
import com.mobile.desafio.repositoriosgithub.activity.MainActivity;
import com.mobile.desafio.repositoriosgithub.activity.PullsActivity;

public class PullActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mainActivity;
    private Instrumentation inst;

    public PullActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.mainActivity = getActivity();
        this.inst = getInstrumentation();
    }

    @UiThreadTest
    public void testTitulo() {

        String nomeRepositorio = clicarNoPrimeiroItemDaListaReoisitorio();

        Instrumentation.ActivityMonitor activityMonitor = inst.addMonitor(PullsActivity.class.getName(), null, false);
        PullsActivity activity = (PullsActivity) inst.waitForMonitor(activityMonitor);

        assertEquals(nomeRepositorio, activity.getTitle().toString());
    }

    private String clicarNoPrimeiroItemDaListaReoisitorio() {
        this.inst.waitForIdleSync();
        RecyclerView recyclerView = this.mainActivity.findViewById(R.id.Repositorios_lista);
        View item = recyclerView.getChildAt(0);
        TextView nome = item.findViewById(R.id.ItemRepositorio_Nome);
        item.callOnClick();
        return nome.getText().toString();
    }

}