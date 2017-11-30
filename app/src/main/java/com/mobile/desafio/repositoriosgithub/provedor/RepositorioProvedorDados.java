package com.mobile.desafio.repositoriosgithub.provedor;

import com.google.gson.Gson;
import com.mobile.desafio.repositoriosgithub.build.NameValuePairBuilder;
import com.mobile.desafio.repositoriosgithub.build.PaginaBuilder;
import com.mobile.desafio.repositoriosgithub.build.RepositorioBuilder;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.provedor.http.ClienteHttp;
import com.mobile.desafio.repositoriosgithub.provedor.http.LinkHeader;
import com.mobile.desafio.repositoriosgithub.provedor.json.RepositoriosJson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.util.EntityUtils;

public class RepositorioProvedorDados {

    private ClienteHttp clienteHttp;

    public RepositorioProvedorDados() {
        this.clienteHttp = new ClienteHttp();
    }

    public Pagina getRepositorios(Integer numeroPagina) throws IOException, URISyntaxException {

        NameValuePairBuilder nameValuePairBuilder = NameValuePairBuilder.getNewInstance()
                .addParmQ("language:Java")
                .addParmSort("stars")
                .addParmPage(numeroPagina.toString());

        HttpResponse resposta = clienteHttp.get("search/repositories", nameValuePairBuilder.build());

        LinkHeader linkHeader = LinkHeader.get(resposta);

        String json = EntityUtils.toString(resposta.getEntity(), StandardCharsets.UTF_8.name());

        RepositoriosJson repositoriosJson = new Gson().fromJson(json, RepositoriosJson.class);

        RepositorioBuilder repositorioBuilder = RepositorioBuilder.getNewInstance();

        List<Repositorio> repositorios = new ArrayList<>();

        for (RepositoriosJson.Item item : repositoriosJson.getItems()) {
            repositorios.add(repositorioBuilder.addRepositorioJson(item).build());
        }

        PaginaBuilder<Repositorio> paginaBuilder = PaginaBuilder.getNewInstance()
                .addItens(repositorios)
                .addNumero(numeroPagina)
                .addProxima(linkHeader.getProxima());

        return paginaBuilder.build();
    }

}