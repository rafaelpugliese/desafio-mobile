package com.mobile.desafio.repositoriosgithub.provedor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobile.desafio.repositoriosgithub.build.NameValuePairBuilder;
import com.mobile.desafio.repositoriosgithub.build.PaginaBuilder;
import com.mobile.desafio.repositoriosgithub.build.PullBuilder;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Pull;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.provedor.http.ClienteHttp;
import com.mobile.desafio.repositoriosgithub.provedor.http.LinkHeader;
import com.mobile.desafio.repositoriosgithub.provedor.json.PullJson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;

public class PullProvedorDados {

    private ClienteHttp clienteHttp;

    public PullProvedorDados() {
        this.clienteHttp = new ClienteHttp();
    }


    public Pagina getPulls(Repositorio repositorio, Integer numeroPagina) throws IOException, URISyntaxException {
        StringBuilder path = new StringBuilder();

        path.append("repos/")
                .append(repositorio.getDono().getNome())
                .append("/")
                .append(repositorio.getNome())
                .append("/pulls");

        NameValuePairBuilder nameValuePairBuilder = NameValuePairBuilder.getNewInstance()
                .addParmState("all")
                .addParmPage(numeroPagina.toString());

        HttpResponse resposta = clienteHttp.get(path.toString(), nameValuePairBuilder.build());

        LinkHeader linkHeader = LinkHeader.get(resposta);

        String json = EntityUtils.toString(resposta.getEntity(), StandardCharsets.UTF_8.name());

        List<PullJson> pullsJson = new Gson().fromJson(json, new TypeToken<ArrayList<PullJson>>() {
        }.getType());

        PullBuilder pullBuilder = PullBuilder.getNewInstance();

        List<Pull> pulls = new ArrayList<>();

        for (PullJson pullJson : pullsJson) {
            pulls.add(pullBuilder.addPullJson(pullJson).addRepositorio(repositorio).build());
        }

        PaginaBuilder<Pull> paginaBuilder = PaginaBuilder.getNewInstance()
                .addItens(pulls)
                .addNumero(numeroPagina)
                .addProxima(linkHeader.getProxima());

        return paginaBuilder.build();
    }

}