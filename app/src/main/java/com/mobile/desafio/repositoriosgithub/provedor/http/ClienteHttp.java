package com.mobile.desafio.repositoriosgithub.provedor.http;

import android.util.Log;

import com.mobile.desafio.repositoriosgithub.excecao.ExcecaoWebService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpHost;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.utils.URIBuilder;
import cz.msebera.android.httpclient.impl.client.HttpClients;
import cz.msebera.android.httpclient.message.BasicHeader;

public class ClienteHttp {

    private static final String LOG_TAG = ClienteHttp.class.getName();

    private HttpClient httpClient;

    public ClienteHttp() {
        this.httpClient = HttpClients.createDefault();
    }

    public HttpResponse get(String path, List<NameValuePair> parametros) throws URISyntaxException, IOException {

        Log.i(LOG_TAG, "GET: [" + getURI(path, parametros) + "] ");

        HttpGet requisicaoGet = new HttpGet();
        requisicaoGet.setURI(getURI(path, parametros));
        requisicaoGet.setHeaders(getHeadersApplicationJson());

        HttpResponse resposta = this.httpClient.execute(requisicaoGet);

        if (!isOK(resposta)) {
            throw new ExcecaoWebService("Erro na comunicação");
        }

        return resposta;
    }

    public boolean isOK(HttpResponse resposta) {
        return resposta.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
    }

    private Header[] getHeadersApplicationJson() {
        BasicHeader[] headers = new BasicHeader[2];
        headers[0] = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers[1] = new BasicHeader(HttpHeaders.ACCEPT, "application/json; charset=UTF-8");
        return headers;
    }

    public static URI getURI(String path, List<NameValuePair> parametros) throws URISyntaxException {
        return new URIBuilder()
                .setScheme(HttpHost.DEFAULT_SCHEME_NAME)
                .setHost("api.github.com/")
                .setPath(path)
                .setParameters(parametros)
                .build();
    }
}