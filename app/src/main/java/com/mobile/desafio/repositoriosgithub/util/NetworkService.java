package com.mobile.desafio.repositoriosgithub.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkService extends BroadcastReceiver {

    public static Boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String texto = isConnected(context) ? "Conectado" : "Desconectado";
        Toast.makeText(context, texto, Toast.LENGTH_SHORT).show();
    }
}